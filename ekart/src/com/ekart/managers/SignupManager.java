package com.ekart.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ekart.entities.User;

public class SignupManager {
	private DataSource datasource;
	//JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	private final String DB_URL = "jdbc:mysql://localhost/online_shopping?serverTimezone=UTC";
		
	//  Database credentials
	private final String USER = "root";
	private final String PASS = "password";

	public SignupManager(DataSource datasource) {
		super();
		this.datasource = datasource;
	}
	
	public int addUser(String userId, String username, String emailId, String password){
		
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql = "Insert into users(user_id, user_name, password, email_id)\r\n" + 
					"values('"+userId+"','"+username+"','"+password+"', '"+emailId+"');";
			stmt.execute(sql);
		} catch (SQLIntegrityConstraintViolationException e) {
			return -1;
		}catch (SQLException e) {
			System.out.println("Error in Login manager");
			e.printStackTrace();
			return -1;
		}catch (Exception e) {
			System.out.println("Error in Login manager");
			e.printStackTrace();
			return 0;
		}finally{
			close(conn,stmt);
		}
		
		return 1;
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
