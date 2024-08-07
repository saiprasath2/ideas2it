package com.ideas2it.ems.exceptions;

/**
 * <p>
 * Defines the custom exception for the employeemanagementsystem.
 *
 * Extends Exception class to display the errors at runtime.
 * </p>
 *
 * @author Saiprasath
 * @version 1.4
 */
public class EmployeeException extends Exception {
    private static final long serialVersionUID = 1L;
    public EmployeeException(String alert, Throwable e) {
        super(alert, e); 
    }
}