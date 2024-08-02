package com.patient.service;

import java.util.List;

import import com.exceptions.PatientException;
import com.model.Diagnosis;
import com.model.Patient;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the patients.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public interface PatientService {

    /**
     * <p>
     * Passes the value from controller to OperationDao 
     * for insertion.
     * </p>
     *
     * @param firstName  String value to set firstName.
     * @param lastName String value to set lastname of patient.
     * @param patienType  String value to set patient type.
     * @return boolean value to display status of addition.
     * @throws PatientException when insertion fails.
     */
    public boolean addPatient(String firstName, String lastName,
                            String PatientType) throws PatientException; 

    /**
     * <p>
     * Passes the value to remove patient.
     * </p>
     *
     * @param removeId  int value to remove the employeeId.
     * @return boolean value to send message.
     * @throws PatientException when removal fails.
     */
    public boolean removePatient(int removeId) throws PatientException; 

    /**
     * <p>
     * Fetches record of patients.
     * </p>
     *
     * @return List<> to print the list.
     * @throws PatientException when retrieval fails.
     */
    public List<Patient> displayPatients() throws PatientException; 

    /**
     * <p>
     * Fetches required patient.
     * </p>
     *
     * @param searchId  int value to search the patient.
     * @return Patient value to display an patient.
     * @throws PatientException when search fails.
     */
    public Patient searchPatient(int searchId) throws PatientException;

    /**
     * <p>
     * Passes value to update the patient at database.
     *</p>
     *
     * @param patient Patient value to update the patient
     * @return boolean value to indicate updation of an patient.
     * @throws PatientException when updation fails.
     */
    public boolean updatePatient(Patient patient) throws PatientException;
}