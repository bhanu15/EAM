package com.rakuten.eam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.eam.service.EmployeeTimeRecordService;

@RestController 
public class EmployeeTimeRecordController {
	
	@Autowired
	EmployeeTimeRecordService employeeTimeRecordService;
	
	public void setEmployeeTimeRecordService(EmployeeTimeRecordService employeeTimeRecordService){
		this.employeeTimeRecordService = employeeTimeRecordService;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)  
	public void createloginRecord(@ModelAttribute("employeeId") int employeeId) {
		employeeTimeRecordService.createLoginRecord(employeeId);	
	} 
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)  
	public void createlogoutRecord(@ModelAttribute("employeeId")int employeeId) { 

		employeeTimeRecordService.createLogoutRecord(employeeId);	
	} 
	
	

}
