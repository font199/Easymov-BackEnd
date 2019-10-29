package com.example.demo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class RestExceptionHandler  {

   
   @ExceptionHandler(TaskManagerBussinessException.class)
   @ResponseBody
   protected ResponseEntity<Object> handleTaskManagerBussinessException(
           TaskManagerBussinessException ex) {
       return buildResponseEntity(new ApiError(ex.getStatus(), ex.getBussinessCode(),
    		   ex.getMessage()));
   }

   
   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
       return new ResponseEntity<>(apiError, apiError.getStatus());
   }


}