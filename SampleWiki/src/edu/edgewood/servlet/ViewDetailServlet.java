package edu.edgewood.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.edgewood.svc.PostingManager;
import edu.edgewood.model.Posting;

/**
 * Servlet implementation class viewDetailServlet
 */
@WebServlet(name = "details", urlPatterns = { "/details" })
public class ViewDetailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PostingManager postingManager;
	
	public ViewDetailServlet() {
		postingManager = new PostingManager();
	}
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Posting posting = postingManager.get(id);
		request.setAttribute("posting", posting);
		request.getRequestDispatcher("WEB-INF/jsp/viewDetail.jsp")
				.forward(request,response);
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}

}
