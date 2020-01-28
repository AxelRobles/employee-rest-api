package com.axelrj.kenzan.employeerestapi.employee;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Component
@DynamicUpdate(value=true)
@SelectBeforeUpdate
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long id;
	@Size(min=2, message = "Name should have at least two characters")
	String firstName;
	@Size(min=2, message = "Name should have at least two characters")
	String middleInitial;
	@Size(min=2,message = "Name should have at least two characters")
	String lastName;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	Date dateOfBirth;
	@Past(message = "")
	@JsonFormat(pattern="dd/MM/yyyy")
	Date dateOfEmployment;
	String status;

	
	public Employee() {
		
	}
	
	
	public Employee(long id, @Size(min = 2, message = "Name should have at least two characters") String firstName,
			@Size(min = 2, message = "Name should have at least two characters") String middleInitial,
			@Size(min = 2, message = "Name should have at least two characters") String lastName,
			@Past(message = "") Date dateOfBirth, @Past(message = "") Date dateOfEmployment, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfEmployment = dateOfEmployment;
		this.status = status;
	}

	

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", dateOfEmployment=" + dateOfEmployment + ", status="
				+ status + "]";
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getId() {
		return id;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfEmployment() {
		return dateOfEmployment;
	}
	public void setDateOfEmployment(Date dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
