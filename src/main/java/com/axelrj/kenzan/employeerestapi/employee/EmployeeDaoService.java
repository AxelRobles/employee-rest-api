package com.axelrj.kenzan.employeerestapi.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDaoService {
	
	private static List<Employee> employeeList = new ArrayList<>();
	private static int idCount=0;
	
	//aqu es donde voy a leer los datos externos iniciales
	static {
		employeeList.add(new Employee(idCount++,"Axel","Omar","Robles",new Date(),new Date(),"Active"));
		employeeList.add(new Employee(idCount++,"Esmeralda","Laura","Sanchez",new Date(),new Date(),"Active"));
		employeeList.add(new Employee(idCount++,"Javier","Felipe","Robles",new Date(),new Date(),"Active"));
		
	}
	
	// Save new employee
	public long saveNewEmployee(Employee emp) {
		emp.setId(idCount++);
		employeeList.add(emp);
		return emp.getId();
	} 
	
	// Get one employee by id
	public Employee getEmployeeById(int id) {
		Employee emp = employeeList.stream()
				.filter(x -> id==x.getId())
				.findAny()
				.orElse(null);
		return emp;
	}
	
	// Delete One employee
		public Employee deleteEmployeeById(int id) {
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
	
	//what happen if the list is size of 0
	// Get all employees
	public List<Employee> getAllEmployee(){
		return employeeList;
	}

}
