package edu.edgewood.svc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.edgewood.dao.MainDao;
import edu.edgewood.model.Posting;
import edu.edgewood.model.Tag;
import edu.edgewood.model.User;

public class PostingManager {
	private MainDao dao;
	
	public PostingManager() {
		dao = new MainDao();
	}
	
//updates posting in database with new posting provided	
	public void update(Posting posting) {
		try {
			dao.update(posting);
						
		}catch(Exception ex) {
			ex.printStackTrace();
			
		}
	}
	
	//gets posting by ID
	public Posting get(String id) {
		Posting p = new Posting();
		try {
			p = dao.getPostingById(id);
			return p;
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return p;
	}
	
	//returns a list of all the postings associated with a tag name or an empty list if no matches are found
		public List<Posting> getByTag(String tagName) {
			List <Posting> postings = new ArrayList(); 
			try {
				postings = dao.getPostingByTag(tagName.toLowerCase());
				return postings;
			}catch (Exception ex) {
				ex.printStackTrace();
				
			}
			return Collections.emptyList();
		}
	

	//adds provided posting to the database
	public boolean addPosting(Posting posting) {
		try {
			dao.insertPosting(posting);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;		
			}
	}
		
	//returns a list of all postings in database
	public List<Posting> getAll() {
		try {
			return dao.getAll();
		}catch (Exception ex) {
			ex.printStackTrace();
			return Collections.emptyList();
		}		
			
	}

	//deletes posting from database
	public void remove(String id) {
		try {
			dao.delete(id);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Posting> getPostByUser(String name){
		name = name.toLowerCase();
		List<Posting> postByUser = new ArrayList<Posting>();
		List<Posting> allPostings = getAll();
		
		for(int i = 0; i < allPostings.size(); i++) {
			User createdBy = allPostings.get(i).getCreatedBy();
			if(createdBy.getUserId().toLowerCase().equals(name) 
					|| createdBy.getFirstName().toLowerCase().equals(name) 
					||createdBy.getLastName().toLowerCase().equals(name) ) {
				postByUser.add(allPostings.get(i));
			}
		}
		return postByUser;
	}
	
	
}
	
	

