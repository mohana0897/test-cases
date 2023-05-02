package com.sun.emp.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sun.emp.Repository.EmployeeRepository;
import com.sun.emp.model.Employee;

@SpringBootTest
public class EmployeeRepositoryTest {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//@Test
	public void givenEmployeeObject_whensave_thenReturnEmployee() {
		//given
		Employee emp=new Employee("sunil","sunilmumbai@gmail.com",50000.0);
		 //when
		Employee newEmp = employeeRepository.save(emp);
		//then
		 Assertions.assertThat(newEmp).isNotNull();
		 Assertions.assertThat(newEmp.getId()).isGreaterThan(0);
	}
	//@Test
	@DisplayName("Junit test for get all employees")
	public void givenEmployees_whenGetAllEmployee_thenReturnEmployees() {
		
		//Given
		Employee emp=new Employee();
		emp.setName("siva");
		emp.setEmail("siva123@gmail.com");
		emp.setSal(354653.22);
		
		Employee emp1=new Employee();
		emp1.setName("koti");
		emp1.setEmail("koti123@gmail.com");
		emp1.setSal(7864812.22);
		employeeRepository.save(emp);
		employeeRepository.save(emp1);
		
		//When
	List<Employee>empList=employeeRepository.findAll();
		//then
	Assertions.assertThat(empList.size()).isEqualTo(3);
		
	}
	@Test
	@DisplayName("Junit test for get Employee by Id")
	public void givenEmployeeId_WhenFindById_thenReturnEmployee() {
		
		//given
		Long id=1L;
		
		//When
	Optional<Employee>newEmp=employeeRepository.findById(id);
		
		//then
	Assertions.assertThat(newEmp).isNotNull();
		
		
	}
	
	//@Test
	@DisplayName("Junit test for delete employee")
	public  void givenEmpId_whenDeleteEmp_thenReturnEmpty() {
		
		//given
		Long id=1L;
	//Employee emp=employeeRepository.findById(1L).get();
	//when
	employeeRepository.deleteById(id);
	Optional<Employee> emp2=employeeRepository.findById(id) ;
	
	//then
	 Assertions.assertThat(emp2).isEmpty();
		
	}
	
	//@Test
	@DisplayName("Junit test for update Employee")
	public void givenFindEmpById_whenUpdateEm_thenReturnUpdateEmp() {
		//Given
		Long id=1L;
	Employee emp=employeeRepository.findById(id).get();
		
		//when
	emp.setEmail("suneel123@gmail.com");
	emp.setSal(100000.345);
	Employee newEmp=employeeRepository.save(emp);
		
		//then
	Assertions.assertThat(newEmp.getEmail()).isEqualTo("suneel123@gmail.com");
	Assertions.assertThat(newEmp.getSal()).isEqualTo(100000.345);
	}
	
	
	
}
