package com.ekart.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ekart.entities.User;

public class LoginManager {
	private DataSource datasource;
	//JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	private final String DB_URL = "jdbc:mysql://localhost/online_shopping?serverTimezone=UTC";
		
	//  Database credentials
	private final String USER = "root";
	private final String PASS = "password";

	public LoginManager(DataSource datasource) {
		super();
		this.datasource = datasource;
	}
	
	public User checkUser(String username){
		
		Connection conn = null;
		Statement stmt = null;
		User user =null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "select user_id, user_name, password, email_id from users "
					+ "where email_id='"+username+"';";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String userId = rs.getString(1);
				String emailId =rs.getString(4);
				String name= rs.getString(2);
				String pass = rs.getString(3);
				
				user = new User(userId,name,pass,emailId);				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error in Login manager");
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("Error in Login manager");
			e.printStackTrace();
		}finally{
			close(conn,stmt);
		}
		System.out.println(user);
		if(user==null) return null;
		return user;
	}

	private void close(Connection conn, Statement stmt) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

}
