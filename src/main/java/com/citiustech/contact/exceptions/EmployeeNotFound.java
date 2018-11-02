package com.citiustech.contact.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such employee")
public class EmployeeNotFound extends RuntimeException {
    
    public EmployeeNotFound(String message) {
        
        super(message);
    }
}