package com.rcd.model.recommender;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.GenericItemSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class MyItemBasedRecommender {
	
	public List<RecommendedItem> myItemBasedRecommender(long userID,int size){
		List<RecommendedItem> recommendations = null;
		try {
			DataModel model = new FileDataModel(new File("D:/ml-1m/movie_preferences.txt"));//鏋勯�犳暟鎹ā鍨嬶紝File-based
			ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);//璁＄畻鍐呭鐩镐技搴�
			Recommender recommender = new GenericItemBasedRecommender(model, similarity);//鏋勯�犳帹鑽愬紩鎿�
			recommendations = recommender.recommend(userID, size);//寰楀埌鎺ㄨ崘鎺ヨ繃
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return recommendations;
	}

}
