package edu.edgewood.svc;

import edu.edgewood.dao.MainDao;
import edu.edgewood.model.User;

public class UserManager {
private MainDao dao;
	
	public UserManager() {
		dao = new MainDao();
	}
	
	/**
	 * returns User object that matches provided username and password
	 * @param userName
	 * @param password
	 * @return
	 */
	public User getUserByLogin(String userName, String password) {
		User u = new User();
		try{
			u = dao.getUserByLogin(userName,password);
			return u;
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	/**
	 * returns User object that 
	 * @param userId
	 * @return
	 */
	public User getUser(String userId) {
		User u = new User();
		try {
			u = dao.getUserById(userId);
			return u;
		}catch (Exception ex) {
			ex.printStackTrace();
		}	
		return u;
	}
	
	/**
	 * adds user object to database
	 * @param user
	 */
	public void addUser(User user) {
		try{
			dao.insertUser(user);
	
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	/**
	 * checks if user is in database
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public boolean checkIfUserExists(String userId) {
		try {
			return dao.getUserById(userId)
					.getUserId()
					.equals(userId) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
	