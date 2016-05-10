package com.rakuten.eam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.eam.service.EmployeeTimeRecordService;

@RestController 
public class EmployeeTimeRecordController {
	
	@Autowired
	EmployeeTimeRecordService employeeTimeRecordService;
	
	public void setEmployeeTimeRecordService(EmployeeTimeRecordService employeeTimeRecordService){
		this.employeeTimeRecordService = employeeTimeRecordService;
	}
	
	@RequestMapping(value = "/checkin", method = RequestMethod.POST)  
	public void createloginRecord(@ModelAttribute("employeeId") int employeeId) {
		employeeTimeRecordService.createLoginRecord(employeeId);	
	} 
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)  
	public void createlogoutRecord(@ModelAttribute("employeeId")int employeeId) { 

		employeeTimeRecordService.createLogoutRecord(employeeId);
	} 
	@RequestMapping(value = "/logout", method = RequestMethod.POST)  
	public void logout(HttpServletRequest request, HttpServletResponse response) { 
		request.getSession().invalidate();
	} 
	

}
