package com.rakuten.eam.dao;

import java.util.List;

import com.rakuten.eam.model.LoginTimeRecord;
import com.rakuten.eam.model.LogoutTimeRecord;

public interface EmployeeTimeRecordDAO {

	public void createLoginTimeRecord(LoginTimeRecord loginTimeRecord);

	public void createLogoutTimeRecord(LogoutTimeRecord logoutTimeRecord);
	
	public List<LoginTimeRecord> getLoginTimeRecordForToday(int employeeId);
	
	public List<LogoutTimeRecord> getLogoutTimeRecordForToday(int employeeId);

	public void updateLogoutTimeRecord(LogoutTimeRecord logoutTimeRecord);

}

