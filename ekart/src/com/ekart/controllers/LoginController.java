package com.ekart.controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import com.ekart.entities.User;
import com.ekart.managers.AdminManager;
import com.ekart.managers.LoginManager;

/**
 * Servlet implementation class LoginController
 */
@WebServlet({ "/","/login", "/logout","/sign-in","/sign-out","/sign-in/admin" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/ekart")
	private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getServletPath().contains("sign-in")) {
			signIn(request, response);
		}else if(request.getServletPath().contains("sign-out")) {
			signOut(request, response);
		}else if(request.getServletPath().contains("login")) {			
			goToLoginPage(request, response);
		}else {
			response.sendRedirect("online_shopping/index");
		}
	}
	private void signOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		try {
			response.sendRedirect("/online_shopping/");
		} catch (IOException e) {
			System.out.println("Error in rediretct sign out method");
			e.printStackTrace();
		}
	}

	private void signIn(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String pass = request.getParameter("password");
		String emailId = request.getParameter("username");
		boolean isAdmin = ((String)request.getParameter("admin")!=null) ? true : false;
		System.out.println(emailId);
		User user = new LoginManager(dataSource).checkUser(emailId);
		if(user==null) {
			String loginMessge = "INVALID USERNAME OR PASSWORD";
			request.setAttribute("loginMessage", loginMessge);
			System.out.println(loginMessge);
			goToLoginPage(request,response);

			System.out.println("invalid user-------------------\n\n");
			
		}else if(isAdmin) {
			if(new AdminManager(dataSource).isAdmin(user.getUserId())){
				if (user.getPassword().equals(pass)) {
					
					System.out.println("creating sessions");
					List<Product> cart = new ArrayList<Product>();
					session.setAttribute("kart", cart);
					session.setAttribute("emailId", user.getEmailId());
					session.setAttribute("userId", user.getUserId());
					try {
						response.sendRedirect("/online_shopping/admin/login");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {
					String loginMessge = "Incorrect username password";
					request.setAttribute("loginMessage", loginMessge);
					System.out.println(loginMessge);
					goToLoginPage(request,response);

				}
				
			}
			else {
				String loginMessge = "You are not admin";
				request.setAttribute("loginMessage", loginMessge);
				System.out.println(loginMessge);
				goToLoginPage(request,response);

			}
			
		}else if (user.getPassword().equals(pass)) {
				
			System.out.println("creating sessions");
			List<Product> cart = new ArrayList<Product>();
			session.setAttribute("kart", cart);
			session.setAttribute("emailId", user.getEmailId());
			session.setAttribute("userId", user.getUserId());
			
			try {
				response.sendRedirect("/online_shopping/index");
			} catch (IOException e) {
				System.out.println("Error in rediretct signIn method");
				e.printStackTrace();
			}
		}
		else {
			String loginMessge = "INVALID USERNAME AND PASSWORD";
			request.setAttribute("loginMessage", loginMessge);
			System.out.println(loginMessge);
			goToLoginPage(request,response);

			System.out.println("invalid password-------------------\n\n");
		}
	}
	private void goToLoginPage(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
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
		if(request.getServletPath().contains("sign-in")) {
			signIn(request, response);
		}else if(request.getServletPath().contains("sign-out")) {
			signOut(request, response);
		}
	}

}
