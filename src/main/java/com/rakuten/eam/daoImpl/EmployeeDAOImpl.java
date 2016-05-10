package com.rakuten.eam.daoImpl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.rakuten.eam.dao.EmployeeDAO;
import com.rakuten.eam.exception.EmployeeAlreadyExistException;
import com.rakuten.eam.exception.EmployeeNotFoundException;
import com.rakuten.eam.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public boolean createEmployee(Employee employee) {	
		Session session = this.sessionFactory.getCurrentSession();
		Employee e = (Employee) session.get(Employee.class, employee.getEmployeeId());		
		if (e!=null){		
			throw new EmployeeAlreadyExistException("Employee already exist");
		}
		session.persist(employee);
		return true;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(employee);
		return true;
	}


	@Override
	public Employee getEmployeeById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee employee = (Employee) session.get(Employee.class, id);
		if(null == employee){
			throw new EmployeeNotFoundException("Employee not found");
		}
		return employee;
	}

	@Override
	public boolean deleteEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee employee = (Employee) session.get(Employee.class, id);
		if(null == employee){
			throw new EmployeeNotFoundException("Employee not found");
		}
		session.delete(employee);
		return true;
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
