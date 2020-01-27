package com.axelrj.kenzan.employeerestapi.employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axelrj.kenzan.employeerestapi.entities.EmployeeRepository;

@Component
public class EmployeeDaoService {
	
	private static List<Employee> employeeList = new ArrayList<>();
	private static long idCount=0;
	
	
	public void setEmployeeList(List<Employee> employeeList ) {
		this.employeeList= employeeList;
	}
	
	public List<Employee> getEmployeeList(){
		return employeeList;
	}
	
	@Autowired
	private EmployeeRepository empRepository;
	
	// Save new employee
	public long saveNewEmployee(Employee emp) {
		
		emp.setId(idCount++);
		employeeList.add(emp);
		return emp.getId();
	} 
	
	// Get one employee by id
	public Employee getEmployeeById(long id) {
		Employee emp = employeeList.stream()
				.filter(x -> id==x.getId())
				.findAny()
				.orElse(null);
		return emp;
	}
	
	// Delete One employee
		public Employee deleteEmployeeById(long id) {
			Iterator empIterator = employeeList.iterator();
			while(empIterator.hasNext()) {
				Employee empAux = (Employee)empIterator.next();
				if( empAux.getId() == id) {
					employeeList.remove(empAux);
					return empAux;
				}
			}
			return null;
		}
	
	// update one employee
		public boolean updateEmployeeById(long id,String property,String value) throws ParseException {
			switch(property) {
			case "firstName":
				for(Employee emp:employeeList) {
					if(emp.getId()==id) 
						emp.setFirstName(value);
				}
				break;
			case "middleInitial":
				for(Employee emp:employeeList) {
					if(emp.getId()==id) 
						emp.setMiddleInitial(value);
				}
				break;
			case "lastName":
				for(Employee emp:employeeList) {
					if(emp.getId()==id) 
						emp.setLastName(value);
				}
				break;
			case "dateOfBirth":
				for(Employee emp:employeeList) {
					if(emp.getId()==id) 
						emp.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse(value));
				}
				break;
			case "dateOfEmployment":
				for(Employee emp:employeeList) {
					if(emp.getId()==id) 
						emp.setDateOfEmployment(new SimpleDateFormat("dd/MM/yyyy").parse(value));
				}
				break;
			case "status":
				for(Employee emp:employeeList) {
					if(emp.getId()==id) 
						emp.setStatus(value);
				}
				break;
		}
			return false;
		}
	
	//what happen if the list is size of 0
	// Get all employees
	public List<Employee> getAllEmployee(){
		return employeeList;
	}
	
	
	//get emp id from a combination of the full name and birthday date
	public long getEmployeeId(Employee employee) {
		Predicate<Employee>firstNameFilter = emp -> emp.getFirstName().equals(employee.getFirstName());
		Predicate<Employee>middleInitialNameFilter = emp -> emp.getMiddleInitial().equals(employee.getMiddleInitial());
		Predicate<Employee>lastNameFilter = emp -> emp.getLastName().equals(employee.getLastName());
		
		Employee emp = employeeList.stream()
				.filter(firstNameFilter.and(middleInitialNameFilter.and(lastNameFilter)))
				.findAny()
				.orElse(null);
		if(emp==null) {
			return -1;
		} else {
			return emp.getId();
		}
	}

}
