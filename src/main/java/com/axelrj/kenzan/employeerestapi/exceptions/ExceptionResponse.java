package com.axelrj.kenzan.employeerestapi.exceptions;

import java.util.Date;

public class ExceptionResponse {

	private Date timestamps;
	private String message;
	private String detailString;
	public ExceptionResponse(Date timestamps, String message, String detailString) {
		super();
		this.timestamps = timestamps;
		this.message = message;
		this.detailString = detailString;
	}
	public Date getTimestamps() {
		return timestamps;
	}
	public void setTimestamps(Date timestamps) {
		this.timestamps = timestamps;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetailString() {
		return detailString;
	}
	public void setDetailString(String detailString) {
		this.detailString = detailString;
	}
	
	

}
