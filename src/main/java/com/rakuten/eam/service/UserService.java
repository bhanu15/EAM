package com.rakuten.eam.service;

import com.rakuten.eam.model.User;

public interface UserService {
	public User getUser(String userName);
	
	public String getRole(String userName);

	boolean authenticateUser(User user);
}
