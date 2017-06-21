package com.rcd.model;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MyDataModel {

	public static JDBCDataModel myDataModel() {
		MysqlDataSource dataSource = new MysqlDataSource();
		JDBCDataModel dataModel = null;
		try {
			dataSource.setServerName("127.0.0.1");
			dataSource.setUser("root");
			dataSource.setPassword("HUHUI");
			dataSource.setDatabaseName("movie");
			// use JNDI
			dataModel = new MySQLJDBCDataModel(DataBaseUtil.getDataSource(),"movie_preferences", "userID", "movieID","preference");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dataModel;
	}

}
