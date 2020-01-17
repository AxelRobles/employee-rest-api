package com.axelrj.kenzan.employeerestapi.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDaoService {
	
	private static List<Employee> employeeList = new ArrayList<>();
	
	
	//aqu es donde voy a leer los datos externos iniciales
	static {
		employeeList.add(new Employee("1","Axel","Robles","01-01-01","01-01-01","Active"));
		employeeList.add(new Employee("2","Esmeralda","Sanchez","01-01-01","01-01-01","Active"));
		employeeList.add(new Employee("3","Javier","Robles","01-01-01","01-01-01","Active"));
		
	}
	
	// create new employee
	
	// Get one employee by id
	public Employee getEmployeeById(String id) {
		Employee emp = employeeList.stream()
				.filter(x -> id.equals(x.getId()))
				.findAny()
				.orElse(null);
		return emp;
	}
	// Delete One employee
	// update one employee
	
	//what happen if the list is size of 0
	// Get all employees
	public List<Employee> getAllEmployee(){
		return employeeList;
	}

}
