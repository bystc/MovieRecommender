package com.rcd.model.recommender;

import java.util.List;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class RecommenderIntro {
	public static void main(String[] args) throws Exception {

		FastByIDMap<PreferenceArray> preferences = new FastByIDMap<PreferenceArray>();

		PreferenceArray prefsForUser1 = new GenericUserPreferenceArray(3);// 注意这里的数字
		// 这里是用来存储一个用户的元数据，这些元数据通常来自日志文件，比如浏览历史，等等，不同的业务场合，它的业务语义是不一样
		
		prefsForUser1.setUserID(0, 1);
		
		prefsForUser1.setItemID(0, 101);
		prefsForUser1.setValue(0, 5.0f);//<1, 101, 5.0f>         < 用户 ID, 物品 ID, 用户偏好 >

		prefsForUser1.setItemID(1, 102);
		prefsForUser1.setValue(1, 3.0f);//<1, 102, 3.0f>

		prefsForUser1.setItemID(2, 103);
		prefsForUser1.setValue(2, 2.5f);//<1, 103, 2.5f>

		preferences.put(1l, prefsForUser1);// 在这里添加数据，userID作为key

		PreferenceArray prefsForUser2 = new GenericUserPreferenceArray(4);

		prefsForUser2.setUserID(0, 2);

		prefsForUser2.setItemID(0, 101);//<2, 101, 2.0f>
		prefsForUser2.setValue(0, 2.0f);

		prefsForUser2.setItemID(1, 102);
		prefsForUser2.setValue(1, 2.5f);//<2, 102, 2.5f>

		prefsForUser2.setItemID(2, 103);
		prefsForUser2.setValue(2, 5.0f);//<2, 103, 5.0f>

		prefsForUser2.setItemID(3, 104);
		prefsForUser2.setValue(3, 2.0f);//<2, 104, 2.0f>

		preferences.put(2l, prefsForUser2);

		PreferenceArray prefsForUser3 = new GenericUserPreferenceArray(4);

		prefsForUser3.setUserID(0, 3);

		prefsForUser3.setItemID(0, 101);
		prefsForUser3.setValue(0, 2.5f);

		prefsForUser3.setItemID(1, 104);
		prefsForUser3.setValue(1, 4.0f);

		prefsForUser3.setItemID(2, 105);
		prefsForUser3.setValue(2, 4.5f);

		prefsForUser3.setItemID(3, 107);
		prefsForUser3.setValue(3, 5.0f);

		preferences.put(3l, prefsForUser3);

		PreferenceArray prefsForUser4 = new GenericUserPreferenceArray(4);

		prefsForUser4.setUserID(0, 4);

		prefsForUser4.setItemID(0, 101);
		prefsForUser4.setValue(0, 5.0f);

		prefsForUser4.setItemID(1, 103);
		prefsForUser4.setValue(1, 3.0f);

		prefsForUser4.setItemID(2, 104);
		prefsForUser4.setValue(2, 4.5f);

		prefsForUser4.setItemID(3, 106);
		prefsForUser4.setValue(3, 4.0f);

		preferences.put(4l, prefsForUser4);

		PreferenceArray prefsForUser5 = new GenericUserPreferenceArray(6);

		prefsForUser5.setUserID(0, 5);

		prefsForUser5.setItemID(0, 101);
		prefsForUser5.setValue(0, 4.0f);

		prefsForUser5.setItemID(1, 102);
		prefsForUser5.setValue(1, 3.0f);

		prefsForUser5.setItemID(2, 103);
		prefsForUser5.setValue(2, 2.0f);

		prefsForUser5.setItemID(3, 104);
		prefsForUser5.setValue(3, 4.0f);

		prefsForUser5.setItemID(4, 105);
		prefsForUser5.setValue(4, 3.5f);

		prefsForUser5.setItemID(5, 106);
		prefsForUser5.setValue(5, 4.0f);

		preferences.put(5l, prefsForUser5);

		DataModel model = new GenericDataModel(preferences);// DataModel的建立
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);//计算相似度
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(2,similarity, model);//计算邻居
		// 创建推荐引擎
		Recommender recommender = new GenericUserBasedRecommender(model,neighborhood, similarity);
		//为用户1推荐2个
		List<RecommendedItem> recommendations = recommender.recommend(1, 2);
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}

	}
}
