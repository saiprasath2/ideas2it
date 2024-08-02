package com.patient.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.exceptions.PatientException;
import com.model.Patient;
import com.model.Diagnosis;

/*
 *<p>
 * Inserts, deletes, updates and fetches data of the diagnosis.
 *
 * Handles datas of diagnosis along with patients.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public interface DiagnosisDao {

    /**
     * <p>
     * creates the diagnosis and inserts it to the storage with auto-incremented Id.
     * </p>
     *
     * @param diagnosisName
     *         For which diagnosis could not be null.
     * @return boolean value to indicate insertion status.
     * @throws PatientException when there is no proper details provided.
     */
    public boolean insertDiagnosis(String diagnosisName) throws PatientException;

    /**
     * <p>
     * Removes the diagnosis in database by changing the boolean value.
     * </p>
     *
     * @param diagnosisId - int value to delete the diagnosis.
     * @return boolean value to indicate deletion status.
     * @throws patientException when there is no proper details provided.
     */
    public void removeDiagnosis(int diagnosisId) throws PatientException;

    /**
     * <p>
     * Retrieves the diagnosis available.
     * </p>
     *
     * @return Map<> value of available diagnosis.
     * @throws patientException when there is no proper details provided. 
     */
    public Map<Integer, Diagnosis> retrieveAllDiagnosis() throws patientException;

    /**
     * <p>
     * Retrieves the diagnosis required by user.
     * </p>
     *
     * @param diagnosisId - int value to delete the diagnosis.
     * @return Diagnosis value to check the required value.
     * @throws patientException when there is no proper details provided.
     */
    public Diagnosis retrieveDiagnosis(int diagnosisId) throws PatientException;

    /**
     * <p>
     * Retrieves all patients assigned under the diagnosis.
     * </p>
     *
     * @param diagnosisId - int value to fetch the diagnosis.
     * @return List<> value to display the patient.
     * @throws patientException when there is no proper details provided.
     */  
    public List<Eatients> getPatientsOfdiagnosiss(int diagnosisId) throws EatientsException;
  
    /**
     * <p>
     * Inserts patient name into assigned diagnosis's list.
     * </p>
     *
     * @param id - int value to get the diagnosis.
     * @param patient - patient value to insert the patient.
     * @return boolean value to indicate insertion.
     * @throws patientException When there is no proper insertion of patient
     *                           into the diagnosiss.
     */ 
    public boolean insertPatient(int id, Patient patient) throws PatientException;
}