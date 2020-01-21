package com.axelrj.kenzan.employeerestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.axelrj.kenzan.employeerestapi.employee.EmployeeDaoService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

@Component
public class EmployeeDaoServiceCommandLineRunner implements CommandLineRunner {

	
	
	@Autowired
	private EmployeeDaoService employeeDaoService;
	
	@Override
	public void run(String... args) throws Exception {
		
	}

}
