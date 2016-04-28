package com.rakuten.eam.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.eam.dao.EmployeeTimeRecordDAO;
import com.rakuten.eam.model.EmployeeTimeRecordId;
import com.rakuten.eam.model.LoginTimeRecord;
import com.rakuten.eam.model.LogoutTimeRecord;
import com.rakuten.eam.service.EmployeeTimeRecordService;

public class EmployeeTimeRecordServiceImpl  implements EmployeeTimeRecordService {

	@Autowired
	private EmployeeTimeRecordDAO employeeTimeRecordDAO;

	public void setEmployeeTimeRecordDAO(EmployeeTimeRecordDAO employeeTimeRecordDAO) {
		this.employeeTimeRecordDAO = employeeTimeRecordDAO;
	}

	@Override
	@Transactional
	public void createLoginRecord(int employeeId) {
		//Testing
		if (!isEmployeeLoginTimeRecordedForToday(employeeId)){
			LoginTimeRecord loginTimeRecord = new LoginTimeRecord(new EmployeeTimeRecordId(employeeId, getCurrentTimestamp()));
			employeeTimeRecordDAO.createLoginTimeRecord(loginTimeRecord);
		}		
	}
	
	
	//Testing

	@Override
	@Transactional
	public void createLogoutRecord(int employeeId) {
		LogoutTimeRecord logoutTimeRecord = new LogoutTimeRecord(new EmployeeTimeRecordId(employeeId, getCurrentTimestamp()));
		if (!isEmployeeLogoutTimeRecordedForToday(employeeId)){
			employeeTimeRecordDAO.createLogoutTimeRecord(logoutTimeRecord);
		}
		else{
			employeeTimeRecordDAO.updateLogoutTimeRecord(logoutTimeRecord);
		}
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
