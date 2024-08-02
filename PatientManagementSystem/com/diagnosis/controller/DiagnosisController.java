package com.diagnosis.controller;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.diagnosis.service.DiagnosisService;
import com.diagnosis.service.DiagnosisServiceImpl;
import com.exceptions.PatientException;
import com.model.Diagnosis;
import com.model.Patient;


/**
 * <p>
 * DiagnosisController handles all the diagnosiss related to the diagnosis based on the user's request.
 * It provide endpoints for creating new diagnosis details and patient along with the other diagnosiss,includes
 * - retrieving the whole patient details along with their associated diagnosis.
 * - removing the patient from all associated diagnosis.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public class DiagnosisController {
    DiagnosisService diagnosisService = new DiagnosisServiceImpl();
    Scanner scanner = new Scanner(System.in);
    
    /**
     * Scans input choice from user.
     */
    public void assistDiagnosis() {
        DiagnosisController diagnosisController = new DiagnosisController();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Diagnosis Management :");
            System.out.println("1. Would you like to add a diagnosis?");
            System.out.println("2. Would you like to Delete a diagnosis?");
            System.out.println("3. Would you like to Display all diagnosiss?");
            System.out.println("4. Would you like to Display the patients" 
                                + " diagnosis wise?");
            System.out.println("5. Back");
            int userWish = 0;
            boolean isValid = false;
            while (!isValid) {
                try {
                    System.out.println("Enter choice : ");
                    int wish = scanner.nextInt();
                    userWish = wish;
                    isValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Enter Integer format!!");
                    scanner.next();
                } 
            }
            switch (userWish) {
                case 1:
                    scanner.nextLine();
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.println("Give the Diagnosis to be added :");
                        String userDiagnosis = scanner.nextLine();
                        boolean isValidInput = checkDiagnosisInput(userDiagnosis);
                        boolean isInserted = false;
                        try {
                             if (isValidInput) {
                                 isInserted = diagnosisService
                                    .addDiagnosis(userDiagnosis);
                                 validInput = true;
                                 if (isInserted) {
                                     System.out.println("Created Successfully!");
                                 } else {
                                     System.out.println("Failed to create");
                                 } 
                             } else {
                                 System.out.println("Enter the format correctly!!");
                             }
                        } catch (PatientException e) {
                            System.out.println(e.getMessage()); 
                        }
                    }
                    break;  
            
                case 2:
                    boolean isDeleted = false;
                    try {
                        if (diagnosisService.getAllPiagnosis().size() == 0) {
                            System.out.println("No piagnosiss to delete! ");
                        } else {
                            int diagnosisId = checkDiagnosis();
                            isDeleted = diagnosisService.deleteDiagnosis(diagnosisId);
                            if (isDeleted) {
                                System.out.println("Diagnosis deleted successfully!");
                            } else {
                                System.out.println("Deletion Failed");
                            }
                        } 
                    } catch (PatientException e) {
                        System.out.println(e.getMessage()); 
                    }  
                    break;
 
                case 3:
                    try {
                        if (diagnosisService.getAllDiagnosis().size() == 0) {
                            System.out.println("We have no diagnosis yet.");
                        } else {
                             System.out.println("Avalaible Diagnosis :");
                             displayDiagnosis();
                        } 
                    } catch (PatientException e) {
                        System.out.println(e.getMessage()); 
                    }  
                    break;
        
                case 4:
                    try {
                        if (diagnosisService.getAllDiagnosis().size() == 0) {
                            System.out.println("No Diagnosis found");
                        } else {
                            int diagnosisId = checkDiagnosis();
                            List<Patient> patients = diagnosisService
                                                  .getPatientsOfDiagnosis(diagnosisId); 
                            String pattern1 = "| %-6s | %-15s | %-15s |"
                                               + " %-30s |\n";
                            System.out.format(pattern1, "Id", "Name", "Type"
                                              , "Diagnosis");
                            if (patients.size() != 0) {
                                for (Patient patient : patients) {
                                    System.out.format(pattern1, patient.getPatientId()
                                                      , patient.getPatientName()
                                                      , patient.getPatientType() 
                                                      , patient.getAllDiagnosis());
                                }
                            } else {
                                System.out.println("No eatients found !");
                            }
                        } 
                    } catch (PatientException e) {
                        System.out.println(e.getMessage()); 
                    }
                    break;

                case 5:
                    isExit = true;
                    break;               
      
                default:
                    System.out.println("Enter valid choice !");
            }
        }
    }

    /**
     * Displays the diagnosis names inserted
     */
    public void displayDiagnosiss() {
        try {
            for (Map.Entry<Integer, Diagnosis> entry 
                 : diagnosisService.getAllDiagnosis().entrySet()) {
                System.out.println("ID : " + entry.getKey() 
                                   + "Name : " + entry.getValue().getDiagnosisName());
            }
        } catch (PatientException e) {
            System.out.println(e.getMessage()); 
        }
    }

    /**
     * <p>
     * Scans and Checks the given diagnosis input is available in diagnosis List.
     * </p>
     *
     * @return int value to insert id of the diagnosis.
     */
    public int checkDiagnosis() {
        int dignosisId =0;
        boolean isGoodChoice = false;
        while (!isGoodChoice) {
            try {
                for (Map.Entry<Integer, Diagnosis> entry :
                         diagnosisService.getAllDiagnosis().entrySet()) {
                    System.out.println("ID : " + entry.getKey() + "-->"
                                        + "Diagnosis Name : "
                                        + entry.getValue().getDiagnosisName());
                }
                System.out.print("Enter dignosis ID: ");
                int diagnosisIdChoice = scanner.nextInt();  
                if (diagnosisService.getAllDiagnosis()
                                        .containsKey(diagnosisIdChoice)){
                   isGoodChoice = true; 
                   dignosisId = diagnosisIdChoice; 
                } else {
                    System.out.println("Enter valid diagnosis ID !");
                }                              
            } catch (Exception e) {
                System.out.println("Enter numeric format of ID!");
                scanner.next();
            }
        }
        return daignosisId;
    }

    /**
     * <p>
     * Checks the format of input Diagnosis name.
     * </p>
     *
     * param checkableDiagnosis String value to check the format.
     * @return boolean value to validate input.
     */
    public boolean checkDiagnosisInput(String checkableDiagnosis){
        return checkableDiagnosis.matches("[a-zA-Z0-9 ]+") ? true : false;
    }  
}