package com.axelrj.kenzan.employeerestapi.employee;

import org.springframework.stereotype.Component;

@Component
public class Employee {
	String id;
	String middleInitial;
	String lastName;
	String dateOfBirth;
	String dateOfEmployment;
	String status;
	
	
	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", middleInitial=" + middleInitial + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", dateOfEmployment=" + dateOfEmployment + ", status=" + status + "]";
	}
	
	public Employee() {
		
	}
	
	public Employee(String id, String middleInitial, String lastName, String dateOfBirth, String dateOfEmployment,
			String status) {
		super();
		this.id = id;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfEmployment = dateOfEmployment;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateOfEmployment() {
		return dateOfEmployment;
	}
	public void setDateOfEmployment(String dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
