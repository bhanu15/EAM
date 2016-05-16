package com.rakuten.eam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.eam.exception.EmployeeAlreadyExistException;
import com.rakuten.eam.exception.EmployeeNotFoundException;
import com.rakuten.eam.model.Employee;
import com.rakuten.eam.model.Status;
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
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)  
	public ModelAndView createEmployee(HttpServletRequest request, HttpServletResponse response, Employee employee) {   
		return new ModelAndView("/employee/create");
		 	  
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Status createEmployee(@ModelAttribute("employee") Employee employee) {  
		boolean isSuccess = employeeService.createEmployee(employee); 	
		if (isSuccess){
			return(new Status(true,"Employee created successfully"));
		}
		return(new Status(false,"Employee creation failed"));
	}  

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)  
	public @ResponseBody ModelAndView getEmployee(@PathVariable("id") int id) {    
		Employee employee = employeeService.getEmployeeById(id);
		ModelAndView model = new ModelAndView("employee/displayEmployee", "employee", employee);	
		return model;  
	}  


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)  
	public @ResponseBody Status deleteEmployee(@PathVariable("id") int id) {     
		boolean isSuccess = employeeService.deleteEmployee(id);  
		if (isSuccess){
			return(new Status(true,"Employee deleted successfully"));
		}
		return(new Status(false,"Employee deletion failed"));
	}  

	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editEmployee(@PathVariable("id") int id){
		Employee employee = employeeService.getEmployeeById(id);
		ModelAndView model = new ModelAndView("/employee/editEmployee", "employee", employee);
		return model; 	
	}

	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public Status updateEmployee(@ModelAttribute("employee") Employee employee){
		boolean isSuccess = employeeService.updateEmployee(employee);
		if (isSuccess){
			return(new Status(true,"Employee updated successfully"));
		}
		return(new Status(false,"Employee updation failed"));
	}
	
	@RequestMapping(value = "/searchbox", method = RequestMethod.GET)  
	public ModelAndView searchEmployee() {   
		return new ModelAndView("/employee/search");
			
	} 
	
	@RequestMapping(value = "/search/{searchKeyword}", method = RequestMethod.GET)  
	public @ResponseBody List<Employee> getEmployeeByKeyword(@PathVariable("searchKeyword") String searchKeyword) {  
		List<Employee> employees = null;  
		employees = employeeService.getEmployeeByKeyword(searchKeyword);  
		return employees;  
	} 
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	  public Status employeeNotFoundException(RuntimeException exception) {
	    return new Status(false, exception.getMessage());
	  }
	@ExceptionHandler(EmployeeAlreadyExistException.class)
	  public Status employeeAlreadyExistException(RuntimeException employeeAlreadyExistException) {
	    return new Status(false, employeeAlreadyExistException.getMessage());
	  }
}




