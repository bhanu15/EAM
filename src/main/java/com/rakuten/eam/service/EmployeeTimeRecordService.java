package com.rakuten.eam.service;

import java.util.List;

import com.rakuten.eam.pojo.CheckInCheckOutReport;

public interface EmployeeTimeRecordService {

	boolean createLoginRecord(int employeeId);

	boolean createLogoutRecord(int employeeId);
	
	List<CheckInCheckOutReport> generateCheckInCheckOutReport(int employeeId);

}

