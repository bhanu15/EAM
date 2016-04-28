package com.rakuten.eam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.eam.model.Employee;
import com.rakuten.eam.service.EmployeeService;

@RestController  
@RequestMapping("/employee") 
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired(required=true)
	@Qualifier(value="employeeService")
	public void setEmployeeService(EmployeeService employeeService){
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)  
	public void createEmployee(@ModelAttribute("employee") Employee p) {   
		employeeService.createEmployee(p);  	  
	}  

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)  
	public @ResponseBody Employee getEmployee(@PathVariable("id") int id) {  
		Employee employee = null;  
		employee = employeeService.getEmployeeById(id);  
		return employee;  
	}  


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)  
	public @ResponseBody void deleteEmployee(@PathVariable("id") int id) {     
		employeeService.deleteEmployee(id);  	  
	}  


	@RequestMapping(value="edit/{id}", method = RequestMethod.PUT)
	public void updateEmployee(@PathVariable("id") int id,@ModelAttribute("employee") Employee employee){
		employee.setEmployeeId(id);
		employeeService.updateEmployee(employee);
	}
	
	@RequestMapping(value = "/search/{searchKeyword}", method = RequestMethod.GET)  
	public @ResponseBody List<Employee> getEmployeeByKeyword(@PathVariable("searchKeyword") String searchKeyword) {  
		List<Employee> employees = null;  
		employees = employeeService.getEmployeeByKeyword(searchKeyword);  
		return employees;  
	} 
}




