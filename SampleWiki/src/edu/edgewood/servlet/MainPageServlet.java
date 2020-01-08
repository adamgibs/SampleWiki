package edu.edgewood.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.edgewood.model.Posting;
import edu.edgewood.svc.PostingManager;


/**
 * Servlet implementation class MainPageServlet
 */
@WebServlet(name = "main", urlPatterns = { "/main" })
public class MainPageServlet extends HttpServlet {

	private PostingManager postingManager; 
	
	public MainPageServlet() {

		postingManager = new PostingManager();
	}
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Posting> postings = postingManager.getAll();

		request.setAttribute("postings", postings);
		

        request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);
		
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("search");
		List<Posting> postings = postingManager.getByTag(searchString);
		for(Posting p : postingManager.getPostByUser(searchString)) {
			postings.add(p);
		}
		
		request.setAttribute("postings", postings);
		request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);
	}

}
