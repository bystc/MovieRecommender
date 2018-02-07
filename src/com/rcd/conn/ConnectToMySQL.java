
package com.rcd.conn;

import java.sql.*;

public class ConnectToMySQL {
	
	private static Connection conn = null;
	
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/movie", "root", "hzb19961010");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}

}
