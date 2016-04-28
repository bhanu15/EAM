package com.rakuten.eam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity  
@Table(name = "employee")    
public class Employee {
	@Id  
	@Column(name = "employee_id") 
	private int employeeId;
	
	@Column(name = "first_name")  
	private String firstName;
	
	@Column(name = "last_name")  
	private String lastName;
	
	@Column(name = "nick_name")  
	private String nickName;
	
	@Column(name = "email")  
	private String emailId;
	
	@Column(name = "department")  
	private String department;
	
	@Column(name = "phone")  
	private String phoneNumber;

	 
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

}
