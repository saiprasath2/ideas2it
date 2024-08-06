package com.ideas2it.ems.exceptions;

public class EmployeeException extends Exception {
    private static final long serialVersionUID = 1L;
    public EmployeeException(String alert, Throwable e) {
        super(alert, e); 
    }
}