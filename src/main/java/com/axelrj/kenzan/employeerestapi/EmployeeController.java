package com.axelrj.kenzan.employeerestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.axelrj.kenzan.employeerestapi.employee.Employee;
import com.axelrj.kenzan.employeerestapi.employee.EmployeeDaoService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDaoService service;
	
	//GET all employees
	//URI - /employees
	@GetMapping(path = "/employees")
	public String getAllEmployees() {
		return "hello employees";
	}
	
	// GET employees beans
	// URI /employeesBean
	@GetMapping(path = "/employeesBean")
	public Employee[] getAllEmployeesBean() {
		return new Employee[] {new Employee("1", "axel", "robles", "01/01/01", "01/01/01","inactive"), 
				new Employee("2", "josefina", "arias", "01/01/01", "01/01/01","active")};
		
		}
	
	// Get one employee by id
	// URI /employee/{id}
	@GetMapping(path =  "/employee/{id}")
	public Employee getEmployee(@PathVariable String id  ) {
		return new Employee("3", "leslie", "robles", "15/15/15", "15/15/15", "active");
	}
	
	// create new employee
	// Delete One employee
	// update one employee
	
}
