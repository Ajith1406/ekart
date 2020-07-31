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
import com.ekart.entities.Product;

public class AdminManager {

	private DataSource datasource;
	//JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	private final String DB_URL = "jdbc:mysql://localhost/online_shopping?serverTimezone=UTC";
		
	//  Database credentials
	private final String USER = "root";
	private final String PASS = "password";

	public AdminManager(DataSource datasource) {
		super();
		this.datasource = datasource;
	}
	
	public boolean addAdmin(String userId, String adminId){
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "Insert into admin(admin_id, user_id)\r\n" + 
					"value('"+adminId+"', '"+userId+"');";
			stmt.execute(query);
		} catch (SQLException e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return false;
		}finally{
			close(conn,stmt);
		}
		
		return true;
	}
	
	public boolean addProduct(Product product) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "INSERT INTO products(product_id,product_name, img_url, rating, price)\r\n" + 
					"VALUES('"+product.getId()+"','"+product.getName()+"','"+product.getImgUrl()+"','"+product.getRating()+"','"+product.getPrice()+"');";
			stmt.execute(query);
		} catch (SQLException e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return false;
		}finally{
			close(conn,stmt);
		}
		
		return true;
	}

	public boolean isAdmin(String userId) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "select admin_id, user_id from admin where user_id='"+userId+"' limit 1;";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String user_id = rs.getNString(2);
				if(user_id!=null)return true;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return false;
		}finally{
			close(conn,stmt);
		}
		
		return false;
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

	public List<User> getUsers() {
		Connection conn = null;
		Statement stmt = null;
		List<User> users = new ArrayList<User>();
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "select user_id, user_name, password, email_id from users;";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String userId = rs.getString(1);
				String emailId =rs.getString(4);
				String name= rs.getString(2);
				String pass = rs.getString(3);
				
				users.add(new User(userId,name,pass,emailId));				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return null;
		}finally{
			close(conn,stmt);
		}
		if(users.isEmpty()) {System.out.println("admin manager failed to retirve users"); return null;}
		return users;
	}
	
	public User getUser(String user_id) {
		Connection conn = null;
		Statement stmt = null;
		List<User> users = new ArrayList<User>();
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "select user_id, user_name, password, email_id from users where user_id='"+user_id+"';";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String userId = rs.getString(1);
				String emailId =rs.getString(4);
				String name= rs.getString(2);
				String pass = rs.getString(3);
				
				return new User(userId,name,pass,emailId);				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return null;
		}finally{
			close(conn,stmt);
		}
		if(users.isEmpty()) {System.out.println("admin manager failed to retirve user"); return null;}
		return null;
	}
	
	public boolean getAdmin(String adminId) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "select user_id,admin_id from admin where admin_id='"+adminId+"';";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String admin = rs.getString(2);
				if(admin != null) return true;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			System.out.println("Error in Admin manager");
			e.printStackTrace();
			return false;
		}finally{
			close(conn,stmt);
		}
		return false;
	}
}
