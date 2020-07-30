package com.ekart.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.ekart.entities.Product;

public class OrdersManager {
	private DataSource datasource;
	//JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	private final String DB_URL = "jdbc:mysql://localhost/online_shopping?serverTimezone=UTC";
		
	//  Database credentials
	private final String USER = "root";
	private final String PASS = "password";

	public OrdersManager(DataSource datasource) {
		super();
		this.datasource = datasource;
	}
	
	public List<Product> getOrders(String userId){
		List<Product> listOfProducts = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "select p.product_id,p.product_name, p.img_url, p.rating, p.price from products p\r\n" + 
					"join orders o on o.product_id = p.product_id where o.user_id='"+userId+"';";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String imgUrl = rs.getString(3);
				double rating = rs.getDouble(4);
				double price = rs.getDouble(5);
				
				listOfProducts.add(new Product(name,id,imgUrl,rating,price));
				
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
		if(listOfProducts.isEmpty()) System.out.println("not working.....\n\n");
		else System.out.println(listOfProducts);
		return listOfProducts;
	}

	public boolean addToOrder(int product_id, String userId){
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
//			LocalDate date = LocalDate.now();
			String sql = "insert into orders(product_id, user_id)\r\n" + 
					"value('"+product_id+"','"+userId+"');";
			stmt.execute(sql);
			
		} catch (SQLException e) {
			System.out.println("Error in Cart manager");
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			System.out.println("Error in Cart manager");
			e.printStackTrace();
			return false;
		}finally{
			close(conn,stmt);
		}
		return true;
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
