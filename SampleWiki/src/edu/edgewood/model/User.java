package edu.edgewood.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class User {
	String userId;
	String password;
	String firstName;
	String lastName;
	LocalDateTime createdDate;
	

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setCreatedDate(LocalDateTime createDate) {
		this.createdDate = createDate;
	}
	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	
	public String getName() {
		return this.getFirstName() + " " + this.getLastName();
	}
	
	
}
