package com.ekart.controllers;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.ekart.managers.SignupManager;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet("/signup")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/ekart")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
		dispatcher.forward(request, response);
		
	}

	private void signup(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session  = request.
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String emailId = request.getParameter("email_id");
		String userId = UUID.randomUUID().toString();
		
		int isSignUp = new SignupManager(dataSource).addUser(userId, name, emailId, password);
		if(isSignUp== -1) {
			String message = "Email Id Already in use";
			request.setAttribute("loginMessage", message);
			
			try {
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
//				dispatcher.forward(request, response);
				response.sendRedirect("/online_shopping/login?message="+message);				
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
			System.out.println("Erron in SQL or already present");
		}else if(isSignUp == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("emailId", emailId);
			try {
				response.sendRedirect("/online_shopping/index");
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Signed up successfully");
		}else {
			String message = "Error";
			request.setAttribute("loginMessage", message);
			System.out.println("Error in signup manager class");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		signup(request,response);
	}

}
