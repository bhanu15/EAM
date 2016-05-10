package com.rakuten.eam.dao;

import java.util.List;

import com.rakuten.eam.model.Employee;

public interface EmployeeDAO {
	
	public boolean createEmployee(Employee employee);
	public boolean updateEmployee(Employee employee);
	public Employee getEmployeeById(int id);
	public boolean deleteEmployee(int id);
	public List<Employee> getEmployeeBySearchKeyword(String searchKeyword);
}
