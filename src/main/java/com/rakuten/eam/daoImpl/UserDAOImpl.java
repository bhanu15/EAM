package com.rakuten.eam.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rakuten.eam.dao.UserDAO;
import com.rakuten.eam.model.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public User getUser(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, userName);		
		return user;
	}

}
