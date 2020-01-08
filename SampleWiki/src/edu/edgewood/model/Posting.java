package edu.edgewood.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Posting {
	String id;
	LocalDate createdDate;
	User createdBy;
	String title;
	String body;
	LocalDateTime lastModified;
	User lastModifiedBy;
	
	List<Tag> tags;
	
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public LocalDateTime getLastModified() {
		return lastModified;
	}
	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}
	public User getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	public String stringifyTags() {
		List<Tag> tags = this.tags;
		String result = "";
		for(int i = 0; i < tags.size() - 1; i++) {
			result += tags.get(i).getName() + ", ";
		}
		
		result += tags.get(tags.size()-1);
		
		return result;
	}
	
	
	
}
