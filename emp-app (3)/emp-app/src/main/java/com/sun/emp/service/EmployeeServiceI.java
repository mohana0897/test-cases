package com.sun.emp.service;
import java.util.List;
import java.util.Optional;

import com.sun.emp.model.Employee;

public interface EmployeeServiceI {
	
	
	public Employee saveEmployee(Employee emp);
	
	public List<Employee>getAllEmployees();
	
	public Optional<Employee> getEmployeeById(Long id);
	
	public Employee updateEmployee(Employee updateEmp);
	public void deleteEmployee(Long id);
	
	
	public List<Employee>savEmployees(List<Employee> emp);

}
