package edu.edgewood.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.edgewood.model.User;
import edu.edgewood.svc.UserManager;

/**
 * Servlet implementation class Register
 */
@WebServlet(name = "register", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserManager userManager;
	
    public RegisterServlet() {
    	userManager = new UserManager();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		LocalDateTime createDate = LocalDateTime.parse (request.getParameter("dateTime"));
		
		Boolean userExists = userManager.checkIfUserExists(userName);
			
		User user = new User();
		
		//No username or password included in request
		if(userName == null || password == null || firstName == null || lastName == null) {
			String message = "Please input information";
			request.setAttribute("errorMessage", message);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
		}
		
		//Message is nothing but white space or left blank
		if(userName.trim().length() == 0 || password.trim().length() == 0) {				

			String message = "Please input information";
			request.setAttribute("errorMessage", message);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			
		}		
		
		
		
		if (userExists == false) {
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUserId(userName);
			user.setPassword(password);
			user.setCreatedDate(createDate);
			userManager.addUser(user);
			Cookie cookie = new Cookie("userId",userName);
			response.addCookie(cookie);
			
			
			
			response.sendRedirect("/y-enterprises/main");
			
		}
		else { 			
 
			 String message = "Invalid username and/or password";						
				request.setAttribute("errorMessage", message);
				request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			
		}
		
	}

}
