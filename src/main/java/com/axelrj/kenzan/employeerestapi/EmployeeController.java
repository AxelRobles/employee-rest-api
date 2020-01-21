package com.axelrj.kenzan.employeerestapi;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.axelrj.kenzan.employeerestapi.employee.Employee;
import com.axelrj.kenzan.employeerestapi.employee.EmployeeDaoService;
import com.axelrj.kenzan.employeerestapi.exceptions.EmployeeNotFoundException;

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
		Employee emp = service.getEmployeeById(Integer.valueOf(id));
		if(emp == null) {
			throw new EmployeeNotFoundException("id-" + id);
		}
		return emp; 
	}
	
	// create new employee
	// URI /saveEmployee
	@PostMapping(path = "/saveEmployee")
	public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee emp) throws URISyntaxException {
		service.saveNewEmployee(emp);
		ServletUriComponentsBuilder.fromCurrentRequest();
		
		
		return ResponseEntity.created(new URI("/employee/"+emp.getId())).build();
	}
	 
	// update one employee
	
	// delete one employee
	@DeleteMapping(path= "/deleteEmployee/{id}")
	public void deleteEmployeeById(@PathVariable int id) {
		Employee emp = service.deleteEmployeeById(id);
		if(emp == null)
			throw new EmployeeNotFoundException("id-" + id);
	}
	
	
}
