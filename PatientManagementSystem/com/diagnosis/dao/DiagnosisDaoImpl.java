package com.diagnosis.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.assister.ConnectionAssister;
import com.exceptions.PatientException;
import com.model.Diagnosis;
import com.model.Patient;

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
public class DiagnosisDaoImpl implements DiagnosisDao {
    @Override
    public boolean insertDiagnosis(String diagnosisName) throws PatientException {
        Connection connection = null;
        PreparedStatement statement = null;             
        try {
             connection = ConnectionAssister.getConnection();
             String query = "insert into diagnosis (`diagnosis_name`) values (?)";      
             statement = connection.prepareStatement(query);
             statement.setString(1, diagnosisName);
             int rowsAffected = statement.executeUpdate();
             if (rowsAffected == 1) {
                 return true;
             }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PatientException("Error at adding " + diagnosisName, e);
        }  finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
        return false;
    }

    
    @Override
    public booelan removeDiagnosis(int diagnosisId) throws PatientException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "update diagnosis set is_Deleted = 1 where diagnosis_id = " + DiagnosisId;
            statement = connection.prepareStatement(query);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PatientException("Error at removing " + diagnosisId, e);
        }  finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
        return false;
    }
    
    @Override
    public Map<Integer, Diagnosis> retrieveAllDiagnosis() throws patientException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "select * from diagnosis where is_Deleted = 0";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Map<Integer, diagnosis> diagnosisAlotter = new HashMap<>();
            while (resultSet.next()) {
                Diagnosis diagnosis = new diagnosis(resultSet.getInt("diagnosis_id"), 
                                             resultSet.getString("diagnosis_name"));
                diagnosisAlotter.put(resultSet.getInt("diagnosis_id"), diagnosis);
            } 
            return diagnosisAlotter;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PatientException("Error at Retreving diagnosis " ,e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    }  

    @Override
    public Diagnosis retrieveDiagnosis(int diagnosisId) throws PatientException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "select diagnosis_id, diagnosis_name from diagnosis where "
                            + "diagnosis_id = " + diagnosisId + " and is_Deleted = 0";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            Diagnosis diagnosis = new Diagnosis(resultSet.getInt("diagnosis_id"),
                                          resultSet.getString("diagnosisName"));
            diagnosis.setEatients(getPatientOfDiagnosis(resultSet.getInt("diagnosis_id")));
            return diagnosis;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Couldn't retrieve the diagnosis of Id : " + diagnosisId);
            throw new PatientException("Error at searching " + diagnosisId, e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    }

    @Override
    public List<Eatients> getPatientsOfdiagnosiss(int diagnosisId) throws EatientsException {
        List<Eatients> eatientsRecords = new ArrayList<>();        
        Connection connection = null;
        Statement statement = null;
        ResultSet patientsResultSet = null;
        ResultSet diagnosisResultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String eatientsQuery = "select * from patients p inner join association_patient_diagnosis a "
                           + " on p.patients_id = a.patients_id  where a.diagnosis_id = " + diagnosisId;
            String diagnosisQuery = "select diagnosis_name from diagnosis where diagnosis_id = " + diagnosisId ;
            statement = connection.createStatement();
            patientsResultSet = statement.executeQuery(eatientsQuery);
            statement = connection.createStatement();
            diagnosisResultSet = statement.executeQuery(diagnosisQuery);
            diagnosisResultSet.next();
            String diagnosisName = diagnosisResultSet.getString("diagnosis_name");
            while(patientsResultSet.next()) {
                Patients patients = new Patients(patientsResultSet.getInt("patients_id"),
                                                  patientsResultSet.getString("first_name"),
                                                  patientsResultSet.getString("last_number"),
                                                  patientsResultSet.getString("patient_type"));
		patientsRecords.add(patients);
                patients.setdiagnosis(new ArrayList<>(Arrays.asList(new diagnosis(diagnosisId, diagnosisName))));
            }
            return patientsRecords;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PatientException("Error at searching : " + diagnosisId, e)
        } finally {
            ConnectionAssister.closeAllResources(patientsResultSet, diagnosisResultSet, statement, connection);
        }
        return new ArrayList<>();
    } 

    @Override
    public boolean insertPatient(int id, Patient patient) throws PatientException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "insert into association_patient_diagnosis values (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, patient.getPatientId());
            statement.setInt(2, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Failed to add Patient with Id : " + patient.getPatientId()
                                + "under Diagnosis Id : " + id);
            throw new EatientException("Error at inserting Eatient" + patient.getId()
                                         + "under diagnosis" 
                                         + retrieveDiagnosis(id).getDiagnosisName(), e);
        }  finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
        return false;
    }   
}