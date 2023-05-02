package com.sun.emp.controller;

import java.util.List;
import java.util.Map;

import com.sun.emp.model.Employee;
import com.sun.emp.model.FilterRequsetDto;
import com.sun.emp.service.serviceImpl.EmployeeServiceImpl;
import com.sun.emp.service.serviceImpl.FilterAndSearchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp/api")
public class EmployeeController {

	private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeServiceImpl serviceImpl;

	@Autowired
	private FilterAndSearchService filterAndSearchService;

	@PostMapping("/employees")
	public ResponseEntity<?> createEmployee(@RequestBody Employee emp) {

		logger.debug("hi this is suneel");

		return new ResponseEntity<Employee>(serviceImpl.saveEmployee(emp), HttpStatus.CREATED);
	}

	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployees() {

		List<Employee> empList = serviceImpl.getAllEmployees();
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);

	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployeeId(@PathVariable("id") Long id) {
		return serviceImpl.getEmployeeById(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody Employee emp) {
		return serviceImpl.getEmployeeById(employeeId).map(savedEmp -> {

			savedEmp.setName(emp.getName());
			savedEmp.setEmail(emp.getEmail());
			savedEmp.setSal(emp.getSal());

			Employee updatedEmp = serviceImpl.updateEmployee(savedEmp);
			return new ResponseEntity<>(updatedEmp, HttpStatus.OK);

		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/employees/{coulmn}/{value}")
	public ResponseEntity<?> getAllEmployessByName(@PathVariable String coulmn, @PathVariable String value) {

		List<Employee> empList = filterAndSearchService.getSpecificationSearch(coulmn, value);

		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);

	}

	@PostMapping("/employees/filterSearch")
	public ResponseEntity<?> getAllEmployess(@RequestBody FilterRequsetDto requestDto) {

		List<Employee> empList = filterAndSearchService.getSpecificationSearch(requestDto.getColumnName(),
				requestDto.getValue());

		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);

	}
	
	@PostMapping("/save")
	public ResponseEntity<?>saveEmployee(@RequestBody List<Employee> emp){
		
	List<Employee>empList=serviceImpl.savEmployees(emp);
		
		
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.CREATED);
		
	}
	

}
