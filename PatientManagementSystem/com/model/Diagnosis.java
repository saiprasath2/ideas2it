package com.model;

import java.util.ArrayList;
import java.util.List;

import com.model.Patient;


/**
 * <p> Represents blueprint for the diagnosis datatype.
 * Contains details of diagnosi such as Id, name.
 * Includes collections of patients alotted to diagnosis.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public class Diagnosis {
    private int diagnosisId;
    private String diagnosisName;
    private boolean isRemoved;
    private List<Patient> patients;

    public Diagnosis(int diagnosisId, String diagnosisName) {
        this.diagnosisId = diagnosisId;
        this.diagnosisName = diagnosisName;
        this.isRemoved = isRemoved;
        patients = new ArrayList<>();
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosis(int diagnosis) {
        this.diagnosisId = diagnosisId;
    }

    public String diagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public List<Patient> getPatients() {
        return patients;
    }
  
    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}