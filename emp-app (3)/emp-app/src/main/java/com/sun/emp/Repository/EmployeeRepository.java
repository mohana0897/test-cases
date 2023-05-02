package com.sun.emp.Repository;

import java.util.Optional;

import com.sun.emp.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface EmployeeRepository extends JpaRepository<Employee, Long> ,JpaSpecificationExecutor<Employee>{

	Optional<Employee> findByEmail(String email);
}
