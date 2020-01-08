
package edu.edgewood.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.edgewood.model.Posting;
import edu.edgewood.svc.PostingManager;

/**
 * Servlet implementation class DeletePostingServlet
 */
@WebServlet(name = "delete", urlPatterns = { "/delete" })
public class DeletePostingServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private PostingManager postingManager;

	
	public DeletePostingServlet() {
		postingManager = new PostingManager();
	}
       
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		//get cookie  
	     String cookieName = "userId";
		 
		 boolean cookieExists = false;
			  
			  //looking for cookie
			  Cookie[] cookies = request.getCookies(); 
			  if (cookies != null) { 
				  for(int i=0; i<cookies.length; i++) 
				  { Cookie cookie = cookies[i]; 
				       if(cookieName.equals(cookie.getName())) 
				         { 
				    	   cookieExists = true;}//end if (userId found)
				  }//end for 
			  }//end if 
		
		if(cookieExists) {	
		
			postingManager.remove(id);
		};
		
		List<Posting> postings = postingManager.getAll();
		request.setAttribute("postings", postings);
		request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);	
		
		}
		
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
