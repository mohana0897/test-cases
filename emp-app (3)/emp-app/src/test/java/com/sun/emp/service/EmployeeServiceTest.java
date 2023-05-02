package com.sun.emp.service;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.willDoNothing;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sun.emp.Repository.EmployeeRepository;
import com.sun.emp.exception.EmployeeExistedExeptionHandling;
import com.sun.emp.model.Employee;
import com.sun.emp.service.serviceImpl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeServiceImpl empService;

	@Mock
	private EmployeeRepository empRepo;

	@Test
	@DisplayName("Junit test for save Employee")
	public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployee() {
		Employee emp = new Employee();
		emp.setId(5L);
		emp.setEmail("rohith123@gmail.com");
		emp.setName("Rohith");
		emp.setSal(54652.2);
		

		// given
		given(empRepo.findByEmail(emp.getEmail())).willReturn(Optional.empty());
		given(empService.saveEmployee(emp)).willReturn(emp);
		// when
		Employee emp1 = empService.saveEmployee(emp);
		// then
		Assertions.assertThat(emp1).isNotNull();
		Assertions.assertThat(emp1.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("Junit test for save EmployeeExisted throws exception")
	public void givenEmployeeEmail_whenSaveEmployee_thenReturnEmployee() {
		Employee emp = new Employee();
		emp.setId(5L);
		emp.setEmail("rohith123@gmail.com");
		emp.setName("Rohith");
		emp.setSal(54652.2);

		// given

		given(empRepo.findByEmail(emp.getEmail())).willReturn(Optional.of(emp));
		
		 

		// when

		assertThrows(EmployeeExistedExeptionHandling.class, () -> empService.saveEmployee(emp));
		// org.junit.jupiter.api.Assertions.assertThrows(EmployeeExistedExeptionHandling.class,
		// () -> {
		// empService.saveEmployee(emp);
		// });
		;
		// then
		verify(empRepo, never()).save(any(Employee.class));

	}

	@Test
	@DisplayName("Junit test for getAllEmployees")
	public void givenEmptyEmployee_whenGetAllEmployee_thenReturnListOfEmp() {

		Employee emp = new Employee();
		emp.setId(5L);
		emp.setEmail("rohith123@gmail.com");
		emp.setName("Rohith");
		emp.setSal(54652.2);

		Employee emp1 = new Employee();
		emp1.setId(2L);
		emp1.setEmail("kumar123@gmail.com");
		emp1.setName("kumar");
		emp1.setSal(54652.2);
		empRepo.save(emp);
		empRepo.save(emp1);
		// given
		given(empRepo.findAll()).willReturn(List.of(emp, emp1));

		// when
		List<Employee> emplist = empService.getAllEmployees();
		// then
		assertThat(emplist).isNotNull();
		assertThat(emplist.size()).isEqualTo(2);

	}

	@Test
	@DisplayName("Junit test for getAllEmployees for negative test case")
	public void givenEmptyEmployee_whenGetAllEmployee_thenReturnEmptyEmp() {

		Employee emp = new Employee();
		emp.setId(5L);
		emp.setEmail("rohith123@gmail.com");
		emp.setName("Rohith");
		emp.setSal(54652.2);

		Employee emp1 = new Employee();
		emp1.setId(2L);
		emp1.setEmail("kumar123@gmail.com");
		emp1.setName("kumar");
		emp1.setSal(54652.2);
		empRepo.save(emp);
		empRepo.save(emp1);
		// given
		given(empRepo.findAll()).willReturn(Collections.emptyList());

		// when
		List<Employee> emplist = empService.getAllEmployees();
		// then
		assertThat(emplist).isEmpty();
		
		assertThat(emplist.size()).isEqualTo(0);

	}

	@Test
	@DisplayName("Junit test for getEmployeeById")
	public void givenEmployeeId_whenGetEmpById_thenReturnEmployee() {

		Employee emp1 = new Employee();
		emp1.setId(2L);
		emp1.setEmail("kumar123@gmail.com");
		emp1.setName("kumar");
		emp1.setSal(54652.2);

		empRepo.save(emp1);
		Long id = 2L;
		// given
		given(empRepo.findById(id)).willReturn(Optional.of(emp1));

		// when
		Employee emp = empService.getEmployeeById(emp1.getId()).get();
		// then
		assertThat(emp).isNotNull();

	}

	@Test
	@DisplayName("Junit test for UpdateEmployee")
	public void givenEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() {
		Employee emp1 = new Employee();
		emp1.setId(2L);
		emp1.setEmail("kumar123@gmail.com");
		emp1.setName("kumar");
		emp1.setSal(54652.2);
		empRepo.save(emp1);
		// given
		given(empRepo.save(emp1)).willReturn(emp1);
		emp1.setEmail("kumar321@gmail.com");
		empRepo.save(emp1);
		// when
		Employee updatedEmp = empService.updateEmployee(emp1);

		// then
		assertThat(updatedEmp.getEmail()).isEqualTo("kumar321@gmail.com");

	}

	@Test
	@DisplayName("junit test for DeleteEmployee")
	public void givenEmployeeId_whenDeleteEmployee_thenReturnEmptyEmp() {
		Employee emp1 = new Employee();
		emp1.setId(2L);
		emp1.setEmail("kumar123@gmail.com");
		emp1.setName("kumar");
		emp1.setSal(54652.2);
		empRepo.save(emp1);
		
	//given
		willDoNothing().given(empRepo).deleteById(emp1.getId());
		//when
		
		empService.deleteEmployee(emp1.getId());
		
		//then
		
		verify(empRepo, times(1)).deleteById(emp1.getId());
	
	}

}
