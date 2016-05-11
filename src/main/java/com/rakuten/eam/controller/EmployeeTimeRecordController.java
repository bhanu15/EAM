package com.rakuten.eam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.eam.exception.EmployeeAlreadyExistException;
import com.rakuten.eam.exception.EmployeeNotFoundException;
import com.rakuten.eam.exception.LoginRecordAlreadyExistException;
import com.rakuten.eam.model.Status;
import com.rakuten.eam.service.EmployeeTimeRecordService;

@RestController 
public class EmployeeTimeRecordController {
	
	@Autowired
	EmployeeTimeRecordService employeeTimeRecordService;
	
	public void setEmployeeTimeRecordService(EmployeeTimeRecordService employeeTimeRecordService){
		this.employeeTimeRecordService = employeeTimeRecordService;
	}
	
	@RequestMapping(value = "/checkin", method = RequestMethod.POST)  
	public Status createloginRecord(@ModelAttribute("employeeId") int employeeId) {
		boolean isSuccess = employeeTimeRecordService.createLoginRecord(employeeId);
		if (isSuccess){
			return(new Status(true,"Checkin time updated successfully"));
		}
		return(new Status(false,"Checkin time updation failed"));
	} 
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)  
	public Status createlogoutRecord(@ModelAttribute("employeeId")int employeeId) { 
		boolean isSuccess = employeeTimeRecordService.createLogoutRecord(employeeId);
		if (isSuccess){
			return(new Status(true,"Checkout time updated successfully"));
		}
		return(new Status(false,"Checkout time updation failed"));
	} 
	@RequestMapping(value = "/logout", method = RequestMethod.POST)  
	public void logout(HttpServletRequest request, HttpServletResponse response) { 
		request.getSession().invalidate();
	}
	
	@ExceptionHandler(LoginRecordAlreadyExistException.class)
	  public Status loginRecordAlreadyExistException(RuntimeException exception) {
	    return new Status(false, "Employee is already checked in for today");
	  }
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	  public Status employeeNotFoundException(RuntimeException exception) {
	    return new Status(false, "Employee not found");
	  }

}
