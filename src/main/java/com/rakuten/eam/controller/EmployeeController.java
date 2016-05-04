package com.rakuten.eam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)  
	public ModelAndView createEmployee(HttpServletRequest request, HttpServletResponse response, Employee employee) {   
		ModelAndView model = new ModelAndView("/employee/create");
		return model; 	  
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)  
	public boolean createEmployee(@ModelAttribute("employee") Employee p) {   
		employeeService.createEmployee(p);  
		return true;
	}  

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)  
	public @ResponseBody ModelAndView getEmployee(@PathVariable("id") int id) {    
		Employee employee = employeeService.getEmployeeById(id);
		ModelAndView model = new ModelAndView("employee/displayEmployee", "employee", employee);	
		return model;  
	}  


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)  
	public @ResponseBody void deleteEmployee(@PathVariable("id") int id) {     
		employeeService.deleteEmployee(id);  	  
	}  

	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editEmployee(@PathVariable("id") int id){
		Employee employee = employeeService.getEmployeeById(id);
		ModelAndView model = new ModelAndView("/employee/editEmployee", "employee", employee);
		return model; 	
	}

	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public void updateEmployee(@ModelAttribute("employee") Employee employee){
		employeeService.updateEmployee(employee);
	}
	
	@RequestMapping(value = "/searchbox", method = RequestMethod.GET)  
	public ModelAndView searchEmployee() {   
		ModelAndView model = new ModelAndView("/employee/search");
		return model; 	
	} 
	
	@RequestMapping(value = "/search/{searchKeyword}", method = RequestMethod.GET)  
	public @ResponseBody List<Employee> getEmployeeByKeyword(@PathVariable("searchKeyword") String searchKeyword) {  
		List<Employee> employees = null;  
		employees = employeeService.getEmployeeByKeyword(searchKeyword);  
		return employees;  
	} 
}




