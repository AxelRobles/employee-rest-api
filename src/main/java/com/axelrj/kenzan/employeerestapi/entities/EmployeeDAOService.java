package com.axelrj.kenzan.employeerestapi.entities;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.axelrj.kenzan.employeerestapi.employee.Employee;

@Repository
@Transactional
public class EmployeeDAOService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(Employee emp) {
		entityManager.persist(emp);
		return emp.getId();
	}
}
