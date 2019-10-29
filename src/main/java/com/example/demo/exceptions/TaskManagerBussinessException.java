package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class TaskManagerBussinessException extends RuntimeException {
	
	private static final long serialVersionUID = 7317093355592633348L;
	private final String bussinessCode;
	private final HttpStatus status;
	private final String message;
    
    public TaskManagerBussinessException(String bussinessCode, HttpStatus status, String message) {
        this.message = message;
    	this.bussinessCode = bussinessCode;      
    	this.status = status;
    }

	public String getBussinessCode() {
		return this.bussinessCode;
	}

	public HttpStatus getStatus() {
		return this.status;
	}
	
	public String getMessage() {
		return this.message;
	}

	
	
    
     


}
