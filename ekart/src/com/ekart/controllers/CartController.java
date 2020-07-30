package com.ekart.controllers;

import java.io.IOException;
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
import com.ekart.managers.SearchProduct;

/**
 * Servlet implementation class CartController
 */
@WebServlet(urlPatterns = { "/viewcart" ,"/viewcart/remove"})
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataSource datasource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("id")!=null) {
			addToCart(request,response);
		}else if(request.getServletPath().contains("remove")) {
			removeItem(request,response);
		}
		else {
			goToCart(request,response);
		}
	}

	private void removeItem(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();		
		int product_id = Integer.parseInt((String)request.getParameter("removeItemId"));
		String userId = (String)session.getAttribute("userId");
		boolean isRemoved = new CartManager(datasource).removeOneItem(product_id, userId);
		if(isRemoved) {
			String message = "Item removed successfully";
			try {
				response.sendRedirect("/online_shopping/viewcart?message="+message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			String message = "Item removed unsuccessfully";
			try {
				response.sendRedirect("/online_shopping/viewcart?message="+message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void goToCart(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("emailId")+".....\n\n");
		String userId = (String)session.getAttribute("userId");
		if(session.getAttribute("emailId") != null) {
			List<Product> products = new CartManager(datasource).getCart(userId);
			double totalAmount = 0;
			for (Product product : products) {
				totalAmount += product.getPrice();
			}
			request.setAttribute("total", totalAmount);
			System.out.println(products);
			request.setAttribute("products", products);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}catch (Exception e) {
				System.out.println("error in cart controller");
				e.printStackTrace();
			}
		}
		else {
			try {
				response.sendRedirect("/online_shopping/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		Product product = (Product)new SearchProduct(datasource).getProduct(id);
		System.out.println(product);
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		System.out.println(session.isNew()+" and "+session.getAttribute("emailId")+":-"+userId);
		try {
			System.out.println("going to add to cart table...");
			boolean isAdded = new CartManager(datasource).addToCart(id, userId);
			System.out.println(isAdded?"added successfully\n\n":"Error");
		}catch (Exception e) {
			System.out.println("Error in adding to cart");
			e.printStackTrace();
		}
		
		goToCart(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
