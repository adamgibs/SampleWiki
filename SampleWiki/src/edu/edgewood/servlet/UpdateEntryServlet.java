package edu.edgewood.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.edgewood.svc.PostingManager;
import edu.edgewood.svc.TagManager;
import edu.edgewood.svc.UserManager;
import edu.edgewood.model.Posting;
import edu.edgewood.model.Tag;
import edu.edgewood.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Servlet implementation class updateEntryServlet
 */
@WebServlet(name = "update", urlPatterns = { "/update" })
public class UpdateEntryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PostingManager postingManager;
	private TagManager tagManager;
	private UserManager userManager;
	
	public UpdateEntryServlet() {
		postingManager = new PostingManager();
		tagManager = new TagManager();
		userManager = new UserManager();
	}
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Posting posting = postingManager.get(id);
		request.setAttribute("posting", posting);
		request.getRequestDispatcher("WEB-INF/jsp/update.jsp")
				.forward(request,response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		LocalDateTime lastModified = LocalDateTime.parse (request.getParameter("lastModified"));
		String userId = request.getParameter("userId");
		User lastModifiedBy = userManager.getUser(userId);
		String tagsString = request.getParameter("tags");
		List<String> tagsList =  Arrays.asList(tagsString.split("\\s*,\\s*"));
		List<Tag> tags = new ArrayList<Tag>();
        
		Posting posting = new Posting();		
		posting.setId(id);
		posting.setTitle(title);
	
		posting.setBody(body);
		posting.setLastModified(lastModified);
		posting.setLastModifiedBy(lastModifiedBy);		
	
		
		
		for(String s : tagsList) {
			Tag t = new Tag();
			t.setName(s.toLowerCase());
			t.setPostingId(posting.getId());
			tags.add(t);	
		}
		
		
		posting.setTags(tags);
		postingManager.update(posting);
		
		for(Tag t : posting.getTags()) {
			if(tagManager.getTagByName(t.getName()) == null) {
				tagManager.addTag(t);
			}
			
		}
		
		List<Posting> postings = postingManager.getAll();
		request.setAttribute("postings", postings);
		request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);
	
	}

}
