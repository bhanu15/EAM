package com.rakuten.eam.dao;

import java.util.List;

import com.rakuten.eam.model.Employee;

public interface EmployeeDAO {
	
	public void createEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public Employee getEmployeeById(int id);
	public void deleteEmployee(int id);
	public List<Employee> getEmployeeBySearchKeyword(String searchKeyword);
}
