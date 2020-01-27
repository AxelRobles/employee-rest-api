package com.axelrj.kenzan.employeerestapi;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
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
public class EmployeeController {
	
	@PostConstruct
	public void init() {
	 
	}
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@Autowired
	private EmployeeDaoService service;
	
	//GET all employees
	//URI - /employees
	@GetMapping(path = "/public/employees")
	public List<Employee> getAllCacheEmployees() {
		if(service.getEmployeeList().size()==0) {
			//enter to update cache employee list
			service.setEmployeeList(empRepository.findAll());
		}
		//return only active Employees
		return empRepository.findAll().stream()
				.filter(x->x.getStatus().equals("Active"))
				.collect(Collectors.toList());
	}
	
	// Get one employee by id
	// URI /employee/{id}
	@GetMapping(path =  "/public/employee/{id}")
	public Employee getEmployee(@PathVariable Long id  ) {
		Employee emp = service.getEmployeeById(id);
		if(emp == null) {
			//enter here due to it couldn't find employee in cache list
			//now will search in DB
			Optional<Employee> empDB = empRepository.findById(id);
			if(!empDB.isPresent())
				throw new EmployeeNotFoundException("employee not found id-" + id);
			else {
				if(empDB.get().getStatus().equals("Inactive")) {
					throw new EmployeeNotFoundException("employee not found id-" + id);
				}
				//add it to cache list
				service.getEmployeeList().add(empDB.get());
				return empDB.get();
			}
		}
		if (emp.getStatus().equals("Inactive"))
			throw new EmployeeNotFoundException("employee not found id-" + id);
		return emp; 
	}
	
	
	//get employee id if exist, did the search with fullname
	@PostMapping(path = "/public/getEmployeeId")
	public long getEmployeeId(@Valid @RequestBody Employee emp) {
		long idEmployee = service.getEmployeeId(emp);
		if(idEmployee < 0) {
			throw new EmployeeNotFoundException("employee with name: "+ emp.getFirstName()+" "+ emp.getMiddleInitial()+" "+emp.getLastName()+" not found");
		} else {
			return idEmployee;
		}
	}
		
		// create new employee
		// URI /saveEmployee
		@PostMapping(path = "/public/saveEmployee")
		public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee emp) throws URISyntaxException {
			empRepository.save(emp);
			service.saveNewEmployee(emp);
			ServletUriComponentsBuilder.fromCurrentRequest();
			return ResponseEntity.created(new URI("/employee/"+emp.getId())).build();
		}
		 
		// update one employee
		@PostMapping(path = "/updateEmployeeById")
		public ResponseEntity<Object> updateEmployee(@Valid @RequestBody Employee emp) throws URISyntaxException, ParseException{
			updateEmployeeProcess(emp);
			return ResponseEntity.created(new URI("/employee/"+emp.getId())).build();
		}
		
		private void updateEmployeeProcess(Employee emp) throws ParseException {
			Optional<Employee> savedEmp = empRepository.findById(emp.getId());
			Employee savedEmpObj=null;
			if(savedEmp.isPresent()) 
				savedEmpObj = savedEmp.get();
			else
				new EmployeeNotFoundException("employee not found:");
			updateOnlyNotNullFields(Employee.class,emp,savedEmpObj);
			empRepository.save(emp);
			ServletUriComponentsBuilder.fromCurrentRequest();
		}
		
		private void updateOnlyNotNullFields(Class<Employee> empClass,Employee emp,Employee savedEmp) throws ParseException {
			Field[] fields = empClass.getDeclaredFields();
			for (Field field : fields) {
				switch(field.getName()) {
					case "firstName":
						if(emp.getFirstName()== null) 
							emp.setFirstName(savedEmp.getFirstName());
						else
							service.updateEmployeeById(emp.getId(), "firstName", emp.getFirstName());
						break;
					case "middleInitial":
						if(emp.getMiddleInitial()==null)
							emp.setMiddleInitial(savedEmp.getMiddleInitial());
						else
							service.updateEmployeeById(emp.getId(), "middleInitial", emp.getMiddleInitial());
						break;
					case "lastName":
						if(emp.getLastName()== null)
							emp.setLastName(savedEmp.getLastName());
						else
							service.updateEmployeeById(emp.getId(), "lastName", emp.getLastName());
						break;
					case "dateOfBirth":
						if(emp.getDateOfBirth()== null)
							emp.setDateOfBirth(savedEmp.getDateOfBirth());
						else
							service.updateEmployeeById(emp.getId(), "dateOfBirth", emp.getDateOfBirth().toString());
						break;
					case "dateOfEmployment":
						if(emp.getDateOfEmployment()== null)
							emp.setDateOfEmployment(savedEmp.getDateOfEmployment());
						else
							service.updateEmployeeById(emp.getId(), "dateOfEmployment", emp.getDateOfEmployment().toString());
						break;
					case "status":
						if(emp.getStatus()== null)
							emp.setStatus(savedEmp.getStatus());
						else
							service.updateEmployeeById(emp.getId(), "status", emp.getStatus());
						break;
				}
	        }
			
		}
		
		
		//getEmployeeByExample
		@PostMapping(path = "/public/getEmployeeModelWithFullName")
		public Employee getEmployeeModel(@Valid @RequestBody Employee emp) {
			Employee empFound = empRepository.findByFirstNameAndMiddleInitialAndLastName(emp.getFirstName(),emp.getMiddleInitial(),emp.getLastName());
			if(empFound !=null && empFound.getStatus().equals("Active")) {
				return empFound;
			} else 
				throw new EmployeeNotFoundException("employee not found:");
		}
		
		
		// delete one employee
		@DeleteMapping(path= "/api/deleteEmployee/{id}")
		public void deleteEmployeeById(@PathVariable long id) throws ParseException {
			Employee toDeleteEmployee = new Employee();
			toDeleteEmployee.setId(id);
			toDeleteEmployee.setStatus("Inactive");
			updateEmployeeProcess(toDeleteEmployee);
		}
}
