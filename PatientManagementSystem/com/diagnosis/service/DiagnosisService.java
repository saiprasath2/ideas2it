package com.diagnosis.service;

import java.util.Map;
import java.util.List;

import com.exceptions.PatientException;
import com.model.Diagnosis;
import com.model.Patient;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the diagnosis.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public interface DiagnosisService {

    /**
     * <p>
     * Passes diagnosis name to be inserted into records.
     * </p>
     *
     * @param diagnosis
     *         For which diagnosis could not be null.
     * @return boolean value to indicate insertion status.
     * @throws PatientException when there is no proper details provided.
     */
    public boolean adddiagnosis(String diagnosis) throws PatientException;

    /**
     * <p>
     * Retrieves the diagnosis available in the records else returns null.
     * </p>
     *
     * @return Map<> value of available diagnosis.
     * @throws patientException when there is no proper details provided. 
     */
    public Map<Integer, Diagnosis> getAllDiagnosis() throws PatientException;

    /**
     * <p>
     * Retrieves the diagnosis required by user.
     * </p>
     *
     * @param diagnosisId - int value to delete the diagnosis.
     * @return Diagnosis value to check the required value.
     * @throws patientException when there is no proper details provided.
     */
    public Diagnosis getDiagnosis(int id) throws PatientException;

    /**
     * <p>
     * passes the diagnosis for changing the boolean value.
     * </p>
     *
     * @param diagnosisId - int value to delete the diagnosis.
     * @return boolean value to indicate deletion status.
     * @throws patientException when there is no proper details provided.
     */
    public void deleteDiagnosis(int diagnosisId) throws PatientException;

    /**
     * <p>
     * passes patient name into assigned diagnosis's list.
     * </p>
     *
     * @param id - int value to get the diagnosis.
     * @param patient - patient value to insert the patient.
     * @return boolean to indicate status of insertion.
     * @throws patientException When there is no proper insertion of patient
     *                           into the diagnosiss.
     */ 
    public void addPatient(int id, Patient patient) throws PatientException;


    /**
     * <p>
     * Retrieves all patients assigned under the diagnosis.
     * </p>
     *
     * @param diagnosisId - int value to fetch the diagnosis.
     * @return List<> value to display the patient.
     * @throws patientException when there is no proper details provided.
     */  
    public List<Eatient> getEatientsOfDiagnosiss(int diagnosisId) throws PatientException;     
}