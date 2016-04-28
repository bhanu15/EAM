package com.rakuten.eam.service;

import java.util.List;

import com.rakuten.eam.model.Employee;

public interface EmployeeService {
	public void createEmployee(Employee employee) ;  
	 public Employee getEmployeeById(int id) ;  
	 public void updateEmployee(Employee employee) ;  
	 public void deleteEmployee(int id);
	public List<Employee> getEmployeeByKeyword(String searchKeyword);  

}

