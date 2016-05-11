package com.rakuten.eam.service;


public interface EmployeeTimeRecordService {

	boolean createLoginRecord(int employeeId);

	boolean createLogoutRecord(int employeeId);
	
	
	

}

