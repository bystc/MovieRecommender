package com.rcd.im.info;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rcd.javabean.Movie;

public class ImportMovies {
	public final static String TABLE_NAME = "movies";
	public final static String ID_COLUMN = "id";
	public final static String NAME_COLUMN = "name";
	public final static String PUBLISHED_YEAR_COLUMN = "published_year";
	public final static String TYPE_COLUMN = "type";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LineNumberReader lineReader = new LineNumberReader(new FileReader(
					"movielens/movies.dat"));
			String line = "";
			List<Movie> movieList = new ArrayList<Movie>();
			while ((line = lineReader.readLine()) != null) {
				movieList.add(fillMovie(line));
			}
			persist(movieList);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void persist(List<Movie> movies) {
		Connection conn = DBUtil.getJDBCConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " + ID_COLUMN + ", "
				+ NAME_COLUMN + ", " + PUBLISHED_YEAR_COLUMN + ", "
				+ TYPE_COLUMN + ") values (?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			for (Movie movie : movies) {
				ps.setInt(1, movie.getId());
				ps.setString(2, movie.getName());
				ps.setString(3, movie.getYear());
				ps.setString(4, StringUtil.connectString(movie.getType(), ", "));
				ps.addBatch();
			}

			ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static Movie fillMovie(String line) {
		Movie movie = new Movie();
		String[] mo = line.split("::");
		movie.setId(Integer.parseInt(mo[0]));
		movie.setName(mo[1].substring(0, mo[1].lastIndexOf("(") - 1));
		movie.setYear(mo[1].substring(mo[1].lastIndexOf("(") + 1,
				mo[1].lastIndexOf(")")));
		List<String> type = new ArrayList<String>();
		String[] t = mo[2].split("\\|");
		for (int i = 0; i < t.length; i++) {
			type.add(t[i]);
		}
		movie.setType(type);
		return movie;
	}
}
