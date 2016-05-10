package com.rakuten.eam.service;

import java.util.List;

import com.rakuten.eam.model.Employee;

public interface EmployeeService {
	public boolean createEmployee(Employee employee) ;  
	public Employee getEmployeeById(int id) ;  
	public boolean updateEmployee(Employee employee) ;  
	public boolean deleteEmployee(int id);
	public List<Employee> getEmployeeByKeyword(String searchKeyword);  

}

