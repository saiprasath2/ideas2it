package com.exceptions;

public class PatientException extends Exception {
    public PatientException(String alert, Throwable e) {
        super(alert, e); 
    }
}