package com.axelrj.kenzan.employeerestapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	
	
	/*@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void getEmployee_shouldReturnEmployes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
		.andExpect(status().isOk());
	}
	
	*/ 


}
