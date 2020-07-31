package com.ekart.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.ekart.entities.Product;
import com.ekart.entities.User;
import com.ekart.managers.AdminManager;
import com.ekart.managers.LoginManager;
import com.ekart.managers.SearchProduct;
import com.ekart.utilities.SendMail;

/**
 * Servlet implementation class AdminController
 */
@WebServlet(urlPatterns= {"/admin/login", "/admin","/admin/addproduct", "/admin/makeadmin"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/ekart")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(new AdminManager(dataSource).isAdmin((String) session.getAttribute("userId"))) {
			if(request.getServletPath().contains("addproduct")) {
				addProduct(request, response);
			}else if(request.getServletPath().contains("makeadmin")) {
				makeAdmin(request, response);
			}else if(request.getServletPath().contains("login")){
				response.sendRedirect("/online_shopping/admin");			
			}else {
				gotoAdminPage(request, response);
			}
		}else {
			String message = "You are not authorized to access";
			response.sendRedirect("/online_shopping/index?message="+message);
		}
		
	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("product_name");
		String  imgUrl = request.getParameter("imgurl");
		double rating = Double.parseDouble(request.getParameter("product_rating"));
		double price = Double.parseDouble(request.getParameter("product_price"));
		int id = new Random().nextInt();
		if(new SearchProduct(dataSource).getProduct(id)!=null) {
			addProduct(request, response);
		}
		if(new AdminManager(dataSource).addProduct(new Product(name, id, imgUrl, rating, price))) {
			HttpSession session = request.getSession();
			String mailId = ((User)new AdminManager(dataSource).getUser((String)session.getAttribute("userId"))).getEmailId();
			sendMail(mailId, new Product(name, id, imgUrl, rating, price));
			String message = "Item Added successfully";
			try {
				response.sendRedirect("/online_shopping/admin?message="+message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			String message = "Something wrong";
			try {
				response.sendRedirect("/online_shopping/admin?message="+message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendMail(String mailId, Product product) {
		String sub ="Product Added successfully";
		String message = "ITEM ADDED SUCCESSFULLY\n"
				+ "Product Name:"+product.getName()+"\n"
						+ "Product Img Url:"+product.getImgUrl()+"\n"
								+ "product Price:"+product.getPrice();
		
		try {
			SendMail.send(mailId, sub, message);
		} catch (AddressException e) {
			System.out.println("Wrong Mail Address");
			e.printStackTrace();
		} catch (MessagingException e) {
			System.out.println("Something wrong while sending mail");
			e.printStackTrace();
		}
	}

	private void makeAdmin(HttpServletRequest request, HttpServletResponse response) {
		
		String userId = request.getParameter("userid");
		String adminId ="admin"+ ThreadLocalRandom.current().nextInt(0, 1000 + 1);
		if(new AdminManager(dataSource).getAdmin(adminId)) {
			makeAdmin(request, response);
		}
		if(new AdminManager(dataSource).addAdmin(userId, adminId)) {
			if(new AdminManager(dataSource).isAdmin(userId)){
				try {
					response.sendRedirect("/online_shopping/admin?message=user Already as admin");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				try {
					response.sendRedirect("/online_shopping/admin?message=user added as admin");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}else {
			try {
				response.sendRedirect("/online_shopping/admin?message=Something wrong please try later");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	private void gotoAdminPage(HttpServletRequest request, HttpServletResponse response) {
		List<User> users = new AdminManager(dataSource).getUsers();
		for (User user : users) {
			if(new AdminManager(dataSource).isAdmin(user.getUserId())) {
				user.setAdmin(true);
			}
		}
		request.setAttribute("users", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
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
