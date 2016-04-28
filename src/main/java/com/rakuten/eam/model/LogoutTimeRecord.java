package com.rakuten.eam.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee_logout")
public class LogoutTimeRecord extends EmployeeTimeRecord {
	
	public LogoutTimeRecord() {
		super();
	}

	public LogoutTimeRecord(EmployeeTimeRecordId employeeTimeRecordId) {
		super(employeeTimeRecordId);
	}

}
