package com.model;

import java.util.ArrayList;
import java.util.List;


/**
 * <p> Represents blueprint for the patient datatype.
 * Contains details of patient such as Id, name, type.
 * Includes collections of patient and diagnosis alotted to patients.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private String patientType;
    private boolean isRemoved;
    private List<Diagnosis> diagnosis; 

    public Patient(int employeeId, String firstName, String lastName,
                   String patientType) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isRemoved = isRemoved;
        this.patientType = patientType;
        diagnosis = new ArrayList<>();
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public List<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    /**
     * <p>
     * Combines given names of patient.
     * </p>
     *
     * @return String value to get name.
     */
    public String getPatientName() {
        String patientName = firstName + LastName;
        return patientName;
    }

    /**
     * <p>
     * Combines all alotted names of diagnosis.
     * </p>
     *
     * @return String value to get diagnosis names.
     */
    public String getAllDiagnosis() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Diagnosis diagnosis : getDiagnosis()) {
            stringBuilder.append(diagnosis.getDiagnosisName() + ",");
        }
        return stringBuilder.toString();
    }
}