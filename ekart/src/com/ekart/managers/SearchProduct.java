package com.ekart.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.ekart.entities.Product;

public class SearchProduct {
	private DataSource datasource;
	//JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	private final String DB_URL = "jdbc:mysql://localhost/online_shopping?serverTimezone=UTC";
		
	//  Database credentials
	private final String USER = "root";
	private final String PASS = "password";

	public SearchProduct(DataSource datasource) {
		super();
		this.datasource = datasource;
	}
	
	public Product getProduct(int product_id){
		Connection conn = null;
		Statement stmt = null;
		Product product=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "select product_id,product_name, img_url, rating, price from products where product_id='"+product_id+"';";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String imgUrl = rs.getString(3);
				double rating = rs.getDouble(4);
				double price = rs.getDouble(5);
				
				product = new Product(name,id,imgUrl,rating,price);
				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error in products manager");
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("Error in products manager");
			e.printStackTrace();
		}finally{
			close(conn,stmt);
		}
		if(product == null) {
			System.out.println("not working.....\n\n");
			return null;
		}
		return product;
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
