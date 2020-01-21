package com.axelrj.kenzan.employeerestapi;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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
import com.axelrj.kenzan.employeerestapi.entities.EmployeeRepository;
import com.axelrj.kenzan.employeerestapi.exceptions.EmployeeNotFoundException;


@RestController
public class EmployeeJPAController {
	
	@Autowired
	private EmployeeDaoService service;
	
	@Autowired
	private EmployeeRepository empRepository;
	
	
	//GET all employees
	//URI - /employees
	@GetMapping(path = "/jpa/employees")
	public List<Employee> getAllEmployees() {
		return empRepository.findAll();
	}
	
	
	//GET id of employee of specific name 
	
	// Get one employee by id
	// URI /employee/{id}
	@GetMapping(path =  "/jpa/employee/{id}")
	public EntityModel<Employee> getEmployee(@PathVariable long id  ) {
		Optional<Employee> emp = empRepository.findById(id);
		if(!emp.isPresent())
			throw new EmployeeNotFoundException("id-" + id);
		EntityModel<Employee> entityModel = new EntityModel<Employee>(emp.get());
		return entityModel; 
	}
	
	// create new employee
	// URI /saveEmployee
	@PostMapping(path = "/jpa/saveEmployee")
	public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee emp) throws URISyntaxException {
		empRepository.save(emp);
		ServletUriComponentsBuilder.fromCurrentRequest();
		return ResponseEntity.created(new URI("/employee/"+emp.getId())).build();
	}
	 
	// update one employee
	
	// delete one employee
	@DeleteMapping(path= "/jpa/deleteEmployee/{id}")
	public void deleteEmployeeById(@PathVariable long id) {
		empRepository.deleteById(id);
	}

}
