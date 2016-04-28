package com.rakuten.eam.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee_login")
public class LoginTimeRecord extends EmployeeTimeRecord {

	public LoginTimeRecord(EmployeeTimeRecordId employeeTimeRecordId) {
		super(employeeTimeRecordId);
	}


}

