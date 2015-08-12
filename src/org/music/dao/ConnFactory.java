package org.music.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnFactory {
	public static Connection getConnection() throws Exception{
	    String url = "jdbc:mysql://localhost:3306/music";
	    Class.forName("com.mysql.jdbc.Driver");
	    String userName = "root";
	    String password = "root";
	    Connection con = DriverManager.getConnection(url,userName,password);
	    return con;
		 }
		 public static void closeps(PreparedStatement ps){
				
			 	if(ps!=null){
			 		try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 	}
		 }
		 public static void closers(ResultSet rs){
				
			 	if(rs!=null){
			 		try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 	}
		 }
		 public static void closeConn(Connection conn){
				
			 	if(conn!=null){
			 		try {
			 			conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 	}
		 }

}
