package com.axelrj.kenzan.employeerestapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.axelrj.kenzan.employeerestapi.employee.Employee;
import com.axelrj.kenzan.employeerestapi.employee.EmployeeDaoService;
import com.axelrj.kenzan.employeerestapi.entities.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeRestApiApplication.class)
public class EmployeeServiceTest {

	Employee alex = new Employee(1, "Alex", "Dan", "Smith", new Date(), new Date(), "Active");
	Employee jonh = new Employee(2, "jonh", "Rob", "Abbe", new Date(), new Date(), "Active");
	Employee irving = new Employee(3, "irving", "Lopez", "Perez", new Date(), new Date(), "Active");

	@Autowired
	private EmployeeDaoService service;

	@Autowired
	EmployeeRepository employeeRepository;

	@Before
	void init() {
		service.getEmployeeList().add(alex);
		service.getEmployeeList().add(jonh);
		service.getEmployeeList().add(irving);
	}

	@Test
	public void getAllEmployees_JPA() {
		List<Employee> employeelist = employeeRepository.findAll();
		assertNotNull(employeelist);
	}

	@Test
	public void findById_thenReturnEmployee() {
		Employee axel = new Employee(4, "axel", "Omar", "Robles", new Date(), new Date(), "Active");
		employeeRepository.save(axel);
		Optional<Employee> found = employeeRepository.findById(axel.getId());
		assertNotNull(found);
		// assertEquals(found.get().getId(), axel.getId());
	}

}
