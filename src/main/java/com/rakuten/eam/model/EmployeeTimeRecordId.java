package com.rakuten.eam.model;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeTimeRecordId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	
	public EmployeeTimeRecordId() {
		super();
	}

	public EmployeeTimeRecordId(int employeeId, Timestamp timeCaptured) {
		super();
		this.employeeId = employeeId;
		this.timeCaptured = timeCaptured;
	}

	@Column(name = "employee_id")  
	private  int employeeId;
	
	@Column(name = "time_captured") 
	private Timestamp timeCaptured;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
		
	}

	public Timestamp getTimeCaptured() {
		return timeCaptured;
	}

	public void setTimeCaptured(Timestamp timestamp) {
		this.timeCaptured = timestamp;
	}
}