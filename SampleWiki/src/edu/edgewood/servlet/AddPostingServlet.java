
package edu.edgewood.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.edgewood.model.Posting;
import edu.edgewood.model.Tag;
import edu.edgewood.model.User;
import edu.edgewood.svc.PostingManager;
import edu.edgewood.svc.TagManager;
import edu.edgewood.svc.UserManager;

/**
 * Servlet implementation class AddPostingServlet
 */
@WebServlet(name = "add", urlPatterns = { "/add" })

public class AddPostingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PostingManager postingManager;
	private TagManager tagManager;
	private UserManager userManager;
	
	public AddPostingServlet() {
		postingManager = new PostingManager();
		tagManager = new TagManager();
		userManager = new UserManager();
	}
           
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/add.jsp")
		.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("Id");		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		LocalDateTime ModifiedTime = LocalDateTime.parse (request.getParameter("dateTime"));		
		String userId = request.getParameter("user");
		User createdBy = userManager.getUser(userId);
		User lastModifiedBy = userManager.getUser(userId);
		String tagsString = request.getParameter("tags");
		
		List<String> tagsList =  Arrays.asList(tagsString.split("\\s*,\\s*"));
		
		List<Tag> tags = new ArrayList<Tag>();
		
		
		Posting posting = new Posting();
		
		posting.setId(id);
		posting.setTitle(title);

		posting.setBody(body);
		posting.setCreatedBy(createdBy);
		
		posting.setLastModified(ModifiedTime);
		posting.setLastModifiedBy(lastModifiedBy);	
		
		for(String s : tagsList) {
			Tag t = new Tag();
			t.setName(s.toLowerCase());
			t.setPostingId(posting.getId());
			tags.add(t);	
		}
		
		posting.setTags(tags);
		
		postingManager.addPosting(posting);
		tagManager.addTags(posting.getTags());
		
		List<Posting> postings = postingManager.getAll();
		request.setAttribute("postings", postings);
		request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);
	}

}
