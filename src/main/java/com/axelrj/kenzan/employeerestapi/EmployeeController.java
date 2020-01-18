package com.axelrj.kenzan.employeerestapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<Employee> getAllEmployees() {
		return service.getAllEmployee();
	}
	
	
	// Get one employee by id
	// URI /employee/{id}
	@GetMapping(path =  "/employee/{id}")
	public Employee getEmployee(@PathVariable String id  ) {
		return service.getEmployeeById(id);
	}
	
	// create new employee
	// URI /saveEmployee
	@PostMapping(path = "/saveEmployee")
	public String saveEmployee(@RequestBody Employee emp) {
		service.saveNewEmployee(emp);
		return "/employee/"+emp.getId();
	}
	
	// Delete One employee
	// update one employee
	
}
