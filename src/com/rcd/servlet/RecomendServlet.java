package com.rcd.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import com.rcd.javabean.MovieInfo;
import com.rcd.model.GetMovieInfo;
import com.rcd.model.recommender.MyItemBasedRecommender;
import com.rcd.model.recommender.MySlopeOneRecommender;
import com.rcd.model.recommender.MyUserBasedRecommender;

public class RecomendServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收用户id
		long userID = Long.parseLong(request.getParameter("userID"));
		//接收size
		int size = Integer.parseInt(request.getParameter("size"));
		//接收推荐类型参数
		String recommendType = request.getParameter("recommendType");
		GetMovieInfo getMovieInfo = new GetMovieInfo();
		//用户的所有电影
		ArrayList<MovieInfo> ownMovieInfo = getMovieInfo.getMovieByUserId(userID);
		//推荐电影的List
		List<RecommendedItem> recommendation = null;
		if(recommendType.equals("userBased")){//基于用户
			MyUserBasedRecommender mubr = new MyUserBasedRecommender();
			//拿到推荐的电影
			recommendation = mubr.userBasedRecommender(userID,size);
		}else if(recommendType.equals("itemBased")){//基于内容
			MyItemBasedRecommender mibr = new MyItemBasedRecommender();
			//拿到推荐的电影
			recommendation = mibr.myItemBasedRecommender(userID,size);
		}else if(recommendType.equals("slopeOne")){//slope one
			MySlopeOneRecommender msor = new MySlopeOneRecommender();
			//拿到推荐的电影
			recommendation = msor.mySlopeOneRecommender(userID,size);
		}
		
		//拿到推荐的电影的详细信息
		ArrayList<MovieInfo> recommendMovieInfo = getMovieInfo.getMovieByMovieId(recommendation);
		//页面跳转
		request.setAttribute("ownMovieInfo", ownMovieInfo);
		request.setAttribute("recommendMovieInfo", recommendMovieInfo);
		request.getRequestDispatcher("recommendResult.jsp").forward(request, response);
	}

}
