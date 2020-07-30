package com.ekart.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.ekart.entities.Product;
import com.ekart.managers.ProductsManager;

/**
 * Servlet implementation class ProductsController
 */
@WebServlet(urlPatterns= {"/index"})
public class ProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/ekart")
	private DataSource dataSource;
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		showProducts(request, response);

	}

	

	private void showProducts(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession();
		if(session.getAttribute("userId")!=null) {
			ProductsManager manager= new ProductsManager(dataSource);
			System.out.println(manager.getProducts());
			List<Product> products = manager.getProducts();
			request.setAttribute("products", products);
			System.out.println(products);
			
			System.out.println(session.isNew()+" and "+session.getAttribute("emailId"));
			
			try {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				System.out.println("Error in Products COntroller");
				e.printStackTrace();
			}catch (Exception e) {
				System.out.println("Error occured");
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
		doGet(request, response);
	}

}
