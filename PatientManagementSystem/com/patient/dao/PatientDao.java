package com.patient.dao;

import java.sql.SQLException;
import java.util.List;

import com.exceptions.PatientException;
import com.model.Diagnosis;
import com.model.Patient;

/*
 * <p>
 * Inserts, deletes, updates and fetches data of the patient.
 *
 * Handles datas of patient along with diagnosis entity to display 
 * the patients diagosis wise.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public interface PatientDao {
    /**
     * <p>
     * insertIntoDatabase will insert datas from user into the database.
     * </p>
     *
     * @param firstName  String value to set first name of patient
     * @param lastName  String value to set last Name.
     * @param patientType  String value to set (IP/OP).
     * @return boolean value to display status.
     * @throws PatientException when insertion is failed.
     */  
    public boolean insertIntoDatabase(String firstName, String lastName
                                      String patientType) throws patientException;

    /** 
     * <p>
     * Removes patient by changing their boolean value.
     * </p>
     *
     * @param removableId - int value to remove the patient.
     * @return boolean value to send message.
     * @throws PatientException when removal is failed.
     */
    public boolean removePatient(int removableId) throws PatientException;

    /**
     * <p>
     * Returns the record of patients created.
     * </p>
     *
     * @return List<> value for all the patients in the database.
     * @throw PatientException when retrieving fails.
     */ 
    public List<Patient> retrievePatients() throws PatientException;

    /** 
     * <p>
     * This method return the patient List by patient Id 
     * </p>
     *   
     * @param patientId to search for patient
     * @return List<> patients by patient Id
     * @throws PatientException when retrival of patient fails.
     */
    public List<Diagnosis> getDiagnosisOfPatient(int patientId) throws PatientException;

    /** 
     * <p>
     * This method return the patient record by Patient Id 
     * </p>
     *   
     * @param patientId to search for patient
     * @return Patient by patient Id
     * @throws EmployeeException when retrival of project fails.
     */
    public Patient retrievePatient(int searchableId) throws PatientException;  

    /**
     * Updates the Patient details..
     *
     * @param patient Patient value to update the Patient
     * @return boolean value to display the updated status.
     * @throws PatientException when updation fails.
     */
    public boolean updatePatient(Patient patient) throws PatientException;     
}