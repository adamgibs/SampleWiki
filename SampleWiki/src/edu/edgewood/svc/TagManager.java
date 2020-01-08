package edu.edgewood.svc;

import java.util.List;

import edu.edgewood.dao.MainDao;
import edu.edgewood.model.Tag;

public class TagManager {
private MainDao dao;
	
	public TagManager() {
		dao = new MainDao();
	}
	
	//get tag by name
	public Tag getTagByName(String tag) {
		try {
			return dao.getTagByName(tag);
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	//adds a list of tags to the database
	public void addTags(List<Tag> tags) {
		try {
			
			if (tags != null){
				for(Tag t : tags) {
					dao.insertTag(t);
				}				
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
					
			}
	}
	
	//adds tag to the database
	public void addTag(Tag tag) {
		try {
			
			if (tag != null){
			 dao.insertTag(tag);
			}
		
		}catch(Exception ex) {
			ex.printStackTrace();
					
		}
	}

}
