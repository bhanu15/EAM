package com.rakuten.eam.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.rakuten.eam.dao.EmployeeTimeRecordDAO;
import com.rakuten.eam.model.LoginTimeRecord;
import com.rakuten.eam.model.LogoutTimeRecord;

public class EmployeeTimeRecordDAOImpl implements  EmployeeTimeRecordDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	@Override
	public boolean createLoginTimeRecord(LoginTimeRecord loginTimeRecord) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(loginTimeRecord);
		return true;
	}
	@Override
	public boolean createLogoutTimeRecord(LogoutTimeRecord logoutTimeRecord) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(logoutTimeRecord);
		return true;
	}
	@Override
	public List<LoginTimeRecord> getLoginTimeRecordForToday(int employeeId){
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "select * FROM employee_login WHERE employee_id = "+employeeId + " and date(time_captured) = date(CURDATE())";
		Query query = session.createSQLQuery(sql);
		List<LoginTimeRecord> results = query.list();
		return results;
		
	}

	@Override
	public List<LogoutTimeRecord> getLogoutTimeRecordForToday(int employeeId) {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "select * FROM employee_logout WHERE employee_id = "+employeeId + " and date(time_captured) = date(CURDATE())";
		SQLQuery query = session.createSQLQuery(sql);
		List<LogoutTimeRecord> results = query.list();
		return results;
	}

	@Override
	public boolean updateLogoutTimeRecord(LogoutTimeRecord logoutTimeRecord) {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "update employee_logout set time_captured = '"+ logoutTimeRecord.getEmployeeTimeRecordId().getTimeCaptured()+ "' WHERE employee_id = "+ logoutTimeRecord.getEmployeeTimeRecordId().getEmployeeId() + " and date(time_captured) = date(CURDATE())";
		SQLQuery query = session.createSQLQuery(sql);	
		query.executeUpdate();
		return true;
	}

}
