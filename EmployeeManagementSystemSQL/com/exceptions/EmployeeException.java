package com.exceptions;

public class EmployeeException extends Exception {
    public EmployeeException(String alert, Throwable e) {
        super(alert, e); 
    }
}