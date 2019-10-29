package com.example.demo.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

class ApiError {

	   private HttpStatus status;
	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;
	   private String message;
	   private String code;

	   private ApiError() {
	       timestamp = LocalDateTime.now();
	   }

	   ApiError(HttpStatus status) {
	       this();
	       this.status = status;
	   }

	   ApiError(HttpStatus status, String code, String message) {
	       this();
	       this.status = status;
	       this.message = message;
	       this.code = code;
	   }

	
		public HttpStatus getStatus() {
			return this.status;
		}
	
		
		public void setMessage(String message) {
			this.message = message;		
		}
	
		public LocalDateTime getTimestamp() {
			return timestamp;
		}
	
		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
	
		public String getCode() {
			return this.code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
	
	
		public String getMessage() {
			return message;
		}
	
		public void setStatus(HttpStatus status) {
			this.status = status;
		}
		
		

	}