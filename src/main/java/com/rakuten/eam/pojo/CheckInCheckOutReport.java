package com.rakuten.eam.pojo;

public class CheckInCheckOutReport {

	private int employeeId ;
	private String checkInTime;
	private String checkOutTime;
	private String timeClocked;
	
	
	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getCheckInTime() {
		return checkInTime;
	}


	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}


	public String getCheckOutTime() {
		return checkOutTime;
	}


	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}


	public String getTimeClocked() {
		return timeClocked;
	}


	public void setTimeClocked(String timeClocked) {
		this.timeClocked = timeClocked;
	}


	@Override
	public String toString() {
		return "CheckInCheckOutReport [employeeId=" + employeeId + ", checkInTime=" + checkInTime + ", checkOutTime="
				+ checkOutTime + ", timeClocked=" + timeClocked + "]";
	}
	
	
	
}
