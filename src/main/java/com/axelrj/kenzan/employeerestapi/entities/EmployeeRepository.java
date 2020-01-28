package com.axelrj.kenzan.employeerestapi.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axelrj.kenzan.employeerestapi.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

	Employee findByFirstNameAndMiddleInitialAndLastName(String firstName, String middleInitial,String lastName);

}
