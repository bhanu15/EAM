package com.rakuten.eam.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.eam.dao.EmployeeDAO;
import com.rakuten.eam.dao.UserDAO;
import com.rakuten.eam.model.User;
import com.rakuten.eam.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	//
	}
	
	@Override
	@Transactional
	public User getUser(String userName) {
		return userDAO.getUser(userName);
	}
	
	@Override
	@Transactional
	public String getRole(String userName){
		User user = userDAO.getUser(userName);		
		return user.getRole();		
	}
	
	@Override
	@Transactional
	public boolean authenticateUser(User user){
		User userDB = userDAO.getUser(user.getUserName());
		if (userDB!=null){
			if (user.getPassword().equals(userDB.getPassword())){
				return true;
			}
		}
		return false;		
	}
}
