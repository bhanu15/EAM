package com.rakuten.eam.daoImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rakuten.eam.dao.EmployeeTimeRecordDAO;
import com.rakuten.eam.exception.CheckInCheckOutReportException;
import com.rakuten.eam.exception.EmployeeNotFoundException;
import com.rakuten.eam.model.LoginTimeRecord;
import com.rakuten.eam.model.LogoutTimeRecord;
import com.rakuten.eam.pojo.CheckInCheckOutReport;

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
	
	@Override
	public List<CheckInCheckOutReport> getLoginLogoutTimeRecordForEmployee(int employeeId) {
		Session session = this.sessionFactory.getCurrentSession();

		List<Object[]> checkInCheckOutRecords = (List<Object[]>) session
				.createSQLQuery("select logout.employee_id , login.time_captured as loginTime ,logout.time_captured as "
						+ "logoutTime FROM employee_logout logout , "
						+ "employee_login login where login.employee_id =logout.employee_id and "
						+ "date(login.time_captured) = date(logout.time_captured)" + "  and  logout.employee_id = "
						+ employeeId)
				.list();
		
		List<CheckInCheckOutReport> checkInCheckOutReports = new ArrayList<CheckInCheckOutReport>();
		
		for (Object checkInCheckOutEntry[] : checkInCheckOutRecords) {

			CheckInCheckOutReport checkInCheckOutReport = new CheckInCheckOutReport();
			checkInCheckOutReport.setEmployeeId((Integer) checkInCheckOutEntry[0]);
			System.out.println("Bhanu ##########getLoginLogoutTimeRecordForEmployee ###### checkInCheckOutEntry"+checkInCheckOutEntry[0]);
			if(checkInCheckOutEntry[0] != null){
			Timestamp checkInTime = (Timestamp) checkInCheckOutEntry[1];
			Timestamp checkOutTime = (Timestamp) checkInCheckOutEntry[2];

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");

			String checkIn = dateFormat.format(checkInTime);
			String checkOut = dateFormat.format(checkOutTime);

			long timeClocked = Math.abs(checkOutTime.getTime() - checkInTime.getTime());

			String timeClockedFormate = getTimeInHoursMinsSecFormate(timeClocked);

			checkInCheckOutReport.setCheckInTime(checkIn);
			checkInCheckOutReport.setCheckOutTime(checkOut);
			checkInCheckOutReport.setTimeClocked(timeClockedFormate);

			checkInCheckOutReports.add(checkInCheckOutReport);
			}else{
				throw new CheckInCheckOutReportException("records not found ");
			}

		}
		return checkInCheckOutReports;
	}

	String getTimeInHoursMinsSecFormate(long timeClocked) {
		String timeClockedFormate = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeClocked),
				TimeUnit.MILLISECONDS.toMinutes(timeClocked)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeClocked)),
				TimeUnit.MILLISECONDS.toSeconds(timeClocked)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeClocked)));
		return timeClockedFormate;
	}

}
