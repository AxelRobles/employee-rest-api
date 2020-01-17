package com.axelrj.kenzan.employeerestapi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class EmployeeRestApiApplicationTests {
	
	/*@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		//arrange
		
		//act
		ResponseEntity<Employee> response = restTemplate.getForEntity("/employees", Employee.class);
		//assert
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}*/

}
