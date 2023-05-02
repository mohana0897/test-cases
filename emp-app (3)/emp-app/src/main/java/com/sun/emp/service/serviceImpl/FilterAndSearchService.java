package com.sun.emp.service.serviceImpl;

import java.util.List;

import com.sun.emp.Repository.EmployeeRepository;
import com.sun.emp.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Service
public class FilterAndSearchService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getSpecificationSearch(String column,String value){
		
		Specification<Employee> spec = new Specification<Employee>() {

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return criteriaBuilder.equal(root.get(column), value) ;
			}
		};
		
		return employeeRepository.findAll(spec);
	}

}
