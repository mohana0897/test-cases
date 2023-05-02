package com.sun.emp.service.serviceImpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.emp.model.Employee;
import com.sun.emp.service.*;
import com.sun.emp.Repository.*;
import com.sun.emp.exception.*;
@Service
public class EmployeeServiceImpl implements EmployeeServiceI  {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public Employee saveEmployee(Employee emp) {
    Optional<Employee> emp1=empRepo.findByEmail(emp.getEmail());
		
		if(emp1.isPresent()) {
			throw new EmployeeExistedExeptionHandling("Entered employee Already existed");		}
	
		return empRepo.save(emp);
	}

	@Override
	public List<Employee> getAllEmployees() {
	
		return empRepo.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long id) {
    Optional<Employee> emp=empRepo.findById(id);
		return emp;
	}

	@Override
	public Employee updateEmployee(Employee updateEmp) {
	Employee emp=empRepo.save(updateEmp);
		return emp;
	}

	@Override
	public void deleteEmployee(Long id) {
	
		empRepo.deleteById(id);
	}

	@Override
	public List<Employee> savEmployees(List<Employee> emp) {
		
	List<Employee>empList=empRepo.saveAll(emp);
		
		return empList;
	}

}
