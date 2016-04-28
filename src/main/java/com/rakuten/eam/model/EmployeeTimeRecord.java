package com.rakuten.eam.model;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EmployeeTimeRecord {
	
	@EmbeddedId
    private EmployeeTimeRecordId employeeTimeRecordId;
		
	public EmployeeTimeRecord(){
		super();
	}

	public EmployeeTimeRecord(EmployeeTimeRecordId employeeTimeRecordId) {
		super();
		this.employeeTimeRecordId = employeeTimeRecordId;
	}

	public EmployeeTimeRecordId getEmployeeTimeRecordId() {
		return employeeTimeRecordId;
	}

	public void setEmployeeTimeRecordId(EmployeeTimeRecordId employeeTimeRecordId) {
		this.employeeTimeRecordId = employeeTimeRecordId;
	}

}

