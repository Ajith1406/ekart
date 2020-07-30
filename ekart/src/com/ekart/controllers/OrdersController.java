package com.ekart.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.ekart.entities.Product;
import com.ekart.managers.CartManager;
import com.ekart.managers.OrdersManager;
import com.ekart.managers.SearchProduct;

/**
 * Servlet implementation class OrdersController
 */
@WebServlet({ "/checkout", "/myorders", "/confirmation" })
public class OrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource datasource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().contains("checkout")) {
			if(request.getParameter("orderId")!=null) checkOneItem(request, response, request.getParameter("orderId"));
			else checkout(request, response);
		}else if(request.getServletPath().contains("confirmation")){
			String confirm = (String)request.getParameter("confirm");
			System.out.println(confirm+"=yes");
			confirmation(request, response, confirm);
		}else {
			myOrders(request, response);
		}
	}

	private void confirmation(HttpServletRequest request, HttpServletResponse response, String confirm) {
		HttpSession session= request.getSession();
		List<Product> items = (List<Product>) session.getAttribute("items");
		if(confirm.equals("yes")) {
			System.out.println(items);
			try {
				for (Product product : items) {
					new OrdersManager(datasource).addToOrder(product.getId(), ((String)session.getAttribute("userId")));
					new CartManager(datasource).removeOneItem(product.getId(), ((String)session.getAttribute("userId")));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			String message= "your order Confirmed... continue shopping :)";
			try {
				response.sendRedirect("/online_shopping/index?message="+message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			String message= "your order is still waiting....";
			try {
				response.sendRedirect("/online_shopping/viewcart?message="+message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void checkOneItem(HttpServletRequest request, HttpServletResponse response, String product_id) {

		Product product = (Product)new SearchProduct(datasource).getProduct(Integer.parseInt(product_id));
		List<Product> orders = new ArrayList<Product>();
		orders.add(product);double totalAmount = 0;
		for (Product productTemp : orders) {
			totalAmount += productTemp.getPrice();
		}
		request.setAttribute("total", totalAmount);
		
		request.setAttribute("products", orders);
		RequestDispatcher dispatcher= request.getRequestDispatcher("checkout.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		System.out.println(orders);
		
		HttpSession session = request.getSession();
		session.setAttribute("items", orders);
	}

	private void checkout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		List<Product> orders = new CartManager(datasource).getCart(userId);
		request.setAttribute("products", orders);
		
		double totalAmount = 0;
		for (Product product : orders) {
			totalAmount += product.getPrice();
		}
		request.setAttribute("total", totalAmount);
		
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("checkout.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		System.out.println(orders);
		session.setAttribute("items", orders);
	}

	private void myOrders(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if((String) session.getAttribute("userId") !=null) {
			List<Product> myOrders = new OrdersManager(datasource).getOrders((String) session.getAttribute("userId"));
			request.setAttribute("products", myOrders);
			RequestDispatcher dispatcher = request.getRequestDispatcher("myorders.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				response.sendRedirect("/online_shopping/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
