package edu.edgewood.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.edgewood.model.Posting;
import edu.edgewood.model.User;
import edu.edgewood.svc.UserManager;


@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserManager userManager;
	
	public LoginServlet() {
		userManager = new UserManager();
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean userExists = userManager.checkIfUserExists(userName);
		
		User user = new User();
		
		//No username or password included in request
		if(userName == null || password == null) {
			String message = "Please input a message";
			request.setAttribute("errorMessage", message);
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
		
		//Message is nothing but white space or left blank
		if(userName.trim().length() == 0 || password.trim().length() == 0) {				

			String message = "Please input a message";
			request.setAttribute("errorMessage", message);
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			
		}		
		
		user = userManager.getUserByLogin(userName, password);
		
		if (userExists == false) {
			String message = "Invalid username and/or password";						
				request.setAttribute("errorMessage", message);
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			
		}
		else { 			
			
			Cookie cookie = new Cookie("userId",userName);
			response.addCookie(cookie);
			
			 response.sendRedirect("/y-enterprises/main");
			
		}
		
		
		
		
			
			
		
		
			
			
		
	}

}
