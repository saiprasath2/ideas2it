package com.patient.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.assister.ConnectionAssister;
import com.exceptions.PatientException;
import com.model.Diagnosis;
import com.model.Patient;
import com.patient.dao.PatientDao;

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
public class PatientDaoImpl implements PatientDao {
    @Override
    public boolean insertIntoDatabase(String firstName, String lastName
                                      String patientType) throws patientException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "insert into patient "
                           +"(`first_name`, `last_name`, `patient_type`)"
                           + "VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, patientType);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot add patient with Name : " + firstName + lastName); 
            throw new PatientException("Error at adding " + firstName, e);
        } finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
        return false;
    }

    @Override
    public boolean removePatient(int removableId) throws PatientException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "update patients set is_Deleted = 1 where patient_id = " + removableId;
            statement = connection.prepareStatement(query);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Cannot remove Patient with ID : " + removableId);
            throw new PatientException("Error at removing :" + removableId, e);
        } finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
        return false;   
    }   

    @Override
    public List<Patient> retrievePatients() throws PatientException {
        List<Patient> patientRecord = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "select patient_id, first_name, last_name, patient_type"
                           + " from patients where is_Deleted = 0";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  
            while (resultSet.next()) {         
                Patient patient = new Patient(resultSet.getInt("patient_id"),
                                                  resultSet.getString("first_name"),
                                                  resultSet.getString("last_name"),
                                                  resultSet.getString("patient_type"));
                patient.setDiagnosis(getDiagnosisOfPatient(resultSet.getInt("patient_id")));
                patientRecord.add(patient);             
            }
            return eatientRecord;
        } catch (Exception e) {  
            System.out.println(e.getMessage());        
            System.out.println("Cannot fetch patients data !");
            throw new PatientException("Error while fetching patients info", e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    }

    @Override
    public List<Diagnosis> getDiagnosisOfPatient(int patientId) throws PatientException {
        String query = "select d.diagnosis_id, d.diagnosis_name from association_patient_diagnosis a "
                       + "right join diagnosis d on a.diagnosis_id = d.diagnosis_id"
                       + " where a.patient_id = " + patientId;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
             connection = ConnectionAssister.getConnection();
             statement = connection.createStatement();
             resultSet = statement.executeQuery(query);
             List<Diagnosis> diagnosis = new ArrayList<>();
             while(resultSet.next()) {
                 diagnosis.add(new Diagnosis(resultSet.getInt("diagnosis_id"),
                                                  resultSet.getString("diagnosis_name")));
             }
             return diagnosis;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot get diagnosis of patient with Id : " + patientId);
            throw new PatientException("Diagnosis of patient failed of Id : " + patientId, e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    } 

    @Override
    public Patient retrievePatient(int searchableId) throws PatientException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "select * where is_Deleted = 0 and patient_id = " + searchableId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            if (resultSet.getInt("is_Deleted") == 0) {         
                Patient patient = new Patient(resultSet.getInt("patient_id"),
                                                  resultSet.getString("first_name"),
                                                  resultSet.getString("last_name"),
                                                  resultSet.getString("patient_type"));
                patient.setDiagnosis(getDiagnosisOfPatient(resultSet.getInt("patient_id")));
                return patient;
            } else {
                return null;  
            }
        } catch (Exception e) {
            System.out.println("Cannot find patient with Id : " + searchableId);
            throw new PatientException("Error at searching :" + searchableId, e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    }

    @Override
    public boolean updatePatient(Patient patient) throws PatientException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionAssister.getConnection();
	    String query = "update patients set first_name = ?, last_name = ?,"
                            + " patient_type = ? where patient_id = " 
                            + patient.getPatientId();
	    statement = connection.prepareStatement(query);
            statement.setString(1, patient.getFirstName());
	    statement.setString(2, patient.getLastName());
	    statement.setString(3, patient.getPatientType());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PatientException("Error at updating :" + patient.getPatientName(), e);
        } finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
        return false;                 
    }
}