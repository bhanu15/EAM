package com.rakuten.eam.daoImpl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.rakuten.eam.dao.EmployeeDAO;
import com.rakuten.eam.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void createEmployee(Employee p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Employee saved successfully, Employee Details="+p);
	}

	@Override
	public void updateEmployee(Employee p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Employee updated successfully, Employee Details="+p);
	}


	@Override
	public Employee getEmployeeById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee p = (Employee) session.get(Employee.class, id);		
		logger.info("Employee loaded successfully, Employee details="+p);
		return p;
	}

	@Override
	public void deleteEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee p = (Employee) session.load(Employee.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Employee deleted successfully, employee details="+p);
	}

	@Override
	public List<Employee> getEmployeeBySearchKeyword(String searchKeyword) {
		searchKeyword = searchKeyword.toLowerCase();
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM Employee WHERE LOWER(firstName) LIKE '%" +searchKeyword +"%'" 
				   + " or  LOWER(lastName) LIKE '%" +searchKeyword+"%'"
				   + " or  LOWER(nickName) LIKE '%" +searchKeyword+"%'"
				   + " or  LOWER(emailId) LIKE '%" +searchKeyword+"%'";
		Query query = session.createQuery(hql);
		List<Employee> results = query.list();
		return results;
	}

}
