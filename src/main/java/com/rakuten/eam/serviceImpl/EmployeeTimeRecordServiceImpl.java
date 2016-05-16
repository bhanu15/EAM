package com.rakuten.eam.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.eam.dao.EmployeeDAO;
import com.rakuten.eam.dao.EmployeeTimeRecordDAO;
import com.rakuten.eam.exception.CheckInCheckOutReportException;
import com.rakuten.eam.exception.EmployeeNotFoundException;
import com.rakuten.eam.exception.LoginRecordAlreadyExistException;
import com.rakuten.eam.model.EmployeeTimeRecordId;
import com.rakuten.eam.model.LoginTimeRecord;
import com.rakuten.eam.model.LogoutTimeRecord;
import com.rakuten.eam.pojo.CheckInCheckOutReport;
import com.rakuten.eam.service.EmployeeTimeRecordService;

public class EmployeeTimeRecordServiceImpl  implements EmployeeTimeRecordService {

	@Autowired
	private EmployeeTimeRecordDAO employeeTimeRecordDAO;
	
	@Autowired
	private EmployeeDAO employeeDAO;

	public void setEmployeeTimeRecordDAO(EmployeeTimeRecordDAO employeeTimeRecordDAO) {
		this.employeeTimeRecordDAO = employeeTimeRecordDAO;
	}

	@Override
	@Transactional
	public boolean createLoginRecord(int employeeId) {
		if (employeeDAO.getEmployeeById(employeeId)==null){
			throw new EmployeeNotFoundException("Employee Not Exist");
		}		
		if (isEmployeeLoginTimeRecordedForToday(employeeId)){
			throw new LoginRecordAlreadyExistException("Employee is already checked in for today");
		}		
		LoginTimeRecord loginTimeRecord = new LoginTimeRecord(new EmployeeTimeRecordId(employeeId, getCurrentTimestamp()));
		return employeeTimeRecordDAO.createLoginTimeRecord(loginTimeRecord);
	}

	@Override
	@Transactional
	public boolean createLogoutRecord(int employeeId) {
		if (employeeDAO.getEmployeeById(employeeId)==null){
			throw new EmployeeNotFoundException("Employee Not Exist");
		}
		LogoutTimeRecord logoutTimeRecord = new LogoutTimeRecord(new EmployeeTimeRecordId(employeeId, getCurrentTimestamp()));
		if (!isEmployeeLogoutTimeRecordedForToday(employeeId)){
			return employeeTimeRecordDAO.createLogoutTimeRecord(logoutTimeRecord);
		}
		else{
			return employeeTimeRecordDAO.updateLogoutTimeRecord(logoutTimeRecord);
		}
	}
	
	@Override
	@Transactional
	public List<CheckInCheckOutReport> generateCheckInCheckOutReport(int employeeId){
			
		if (employeeDAO.getEmployeeById(employeeId)==null){
			throw new EmployeeNotFoundException("Employee Not Exist");
		}
		List<CheckInCheckOutReport> checkInCheckOutReports = employeeTimeRecordDAO.getLoginLogoutTimeRecordForEmployee(employeeId);
		return checkInCheckOutReports;
	}
	
	public boolean isEmployeeLoginTimeRecordedForToday(int employeeId){
		List<LoginTimeRecord> list = employeeTimeRecordDAO.getLoginTimeRecordForToday(employeeId);
		if (!list.isEmpty()){
			return true;	
		}
		return false;
	}
	
	public boolean isEmployeeLogoutTimeRecordedForToday(int employeeId){
		List<LogoutTimeRecord> list = employeeTimeRecordDAO.getLogoutTimeRecordForToday(employeeId);
		if (!list.isEmpty()){
			return true;	
		}
		return false;
			
	}
	
	public Timestamp getCurrentTimestamp(){
		return  new Timestamp(new Date().getTime());
	}

}
