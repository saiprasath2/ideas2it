package com.patient.service;

import java.util.ArrayList;
import java.util.List;

import com.exceptions.PatientException;
import com.model.Diagnosis;
import com.model.Patient;
import com.patient.service.PatientService;
import com.patient.dao.PatientDao;
import com.patient.dao.PatientDaoImpl;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the patients.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public class PatientServiceImpl implements PatientService {
    PatientDao patientDao = new PatientDaoImpl();
    
    @Override
    public boolean addPatient(String firstName, String lastName,
                            String PatientType) throws PatientException {  
        return patientDao.insertIntoDatabase(firstName, lastName, PatientType);
    }   

    @Override   
    public boolean removePatient(int removeId) throws PatientException {
        return patientDao.removePatient(removeId);
    }

    @Override
    public List<Patient> displayPatients() throws PatientException {
        List<Patient> serviceStorage = patientDao.retrievePatients();
        return serviceStorage;  
    } 

    @Override
    public Patient searchPatient(int searchId) throws PatientException {
        return patientDao.retrievePatient(searchId);
    } 

    @Override
    public boolean updatePatient(Patient patient) throws PatientException {
        return patientDao.updatePatient(patient);               
    }       
}