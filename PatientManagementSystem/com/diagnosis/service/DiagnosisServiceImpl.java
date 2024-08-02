package com.diagnosis.service;

import java.util.Map;
import java.util.List;

import com.diagnosis.dao.DiagnosisDao;
import com.diagnosis.dao.DiagnosisDaoImpl;
import com.exceptions.PatientException;
import com.model.Diagnosis;
import com.model.diagnosis;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the diagnosis.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public class DiagnosisServiceImpl implements DiagnosisService {
    DiagnosisDao diagnosisDao = new DiagnosisDaoImpl();
    
    @Override
    public boolean adddiagnosis(String diagnosis) throws PatientException {
        return diagnosisDao.insertDiagnosis(diagnosis);
    }

    @Override
    public Map<Integer, Diagnosis> getAllDiagnosis() throws PatientException {
        return diagnosisDao.retrieveAllDiagnosis();
    }

    @Override
    public Diagnosis getDiagnosis(int id) throws PatientException {
        return diagnosisDao.retrieveDiagnosis(id);        
    }
   
    @Override   
    public boolean deleteDiagnosis(int diagnosisId) throws PatientException {
        return diagnosisDao.removeDiagnosis(diagnosisId);
    }

    @Override
    public boolean addPatient(int id, Patient patient) throws PatientException {
        return diagnosisDao.insertPatient(id, patient);
    } 

    @Override
    public List<Eatient> getEatientsOfDiagnosiss(int diagnosisId) throws PatientException {
        return diagnosisDao.getPatientsOfDiagnosiss(diagnosisId);
    }       
}