package com.patient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.diagnosis.service.DiagnosisService;
import com.diagnosis.service.DiagnosisServiceImpl;
import com.exceptions.PatientException;
import com.patient.service.PatientService;
import com.patient.service.PatientServiceImpl;
import com.model.Diagnosis;
import com.model.Patient;

/**
 * <p>
 * OperationController handles all the operations related to the management based on the user's request.
 * It provide endpoints for creating new patient details, diagnosis details along with the other operations,includes
 * - retrieving the whole patient details along with their associated diagnosis.
 * - removing the student from all associated , diagnosis.
 * </p>
 */
public class PatientController {
    private DiagnosisService diagnosisService = new DiagnosisServiceImpl();
    private PatientService patientService = new PatientServiceImpl();
    private InputReader reader = new InputReader();
    private Scanner scanner = new Scanner(System.in);
 
    /**
     * Displays available options to user.
     *
     * Passes the parameters to methods at OperationService 
     */
    public void assistPatient() {
        PatientController patientController = new PatientController();
        boolean isExit = false;
        while (!isExit) {
            System.out.println(" Patient Management System: ");
            System.out.println("1. Would you like to Add an  Patient?");
            System.out.println("2. Would you like to Removpn  Patient?");
            System.out.println("3. Would you like to Display the  Patients" 
                                + " List?");
            System.out.println("4. Would you like to Search an  Patient?");
            System.out.println("5. Would you like to updatpn  Patient?");
            System.out.println("6. Would you like to add assign diagnosis to "
                                + "an  Patient?");
            System.out.println("7. Do you want to Exit?");
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
                String firstName = reader.readName();
                String lastName = reader.readName();
                String patientType = reader.readType();
                try {
                    boolean isAdded = patientService.addPatient(firstName,
                                                     lastName, patientType);
                    if (isAdded) {
                        System.out.println("Added Successfully!!");
                    } else {
                        System.out.println("Failed to add!!");
                    }
                } catch (PatientException e) {
                    System.out.println(e.getMessage()); 
                }
                break;

            case 2:
                System.out.println("Enter the Patient Id to remove : ");
                int removeId = scanner.nextInt();
                try {
                    boolean isRemoved = patientService
                                           .removePatient(removeId);
                    if (isRemoved) {
                        System.out.println("\n----Patient data has "
                                            + "been removed.----");
                    } else {
                        System.out.println("Given data cannot be found.");
                    }
                } catch (PatientException e) {
                    System.out.println(e.getMessage()); 
                }
                break;

            case 3:
                try {
                    List<Patient> patientDetails = patientService
                                                          .displayPatients();
                    System.out.println("We have " + patientDetails.size() 
                                              + " patients.");
                    String format = "| %-6s | %-15s | %-15s | %-30s |\n";
                    System.out.format(format, "Id", "Name", "Type", "Diagnosis");
                    for (Patient patient : patientDetails) {
                        if (patient.getIsRemoved() == false) {
                            System.out.format(format, patient.getPatientId()
                                              , patient.getPatientName()
                                              , patient.getPatientType() 
                                              , patient.getAllDaignosis());
                        }
                    } 
                    System.out.println("----All of the patients have been"
                                        + " displayed.----"); 
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 4:
                System.out.println("Enter the Id of the patient : ");
                int searchId = scanner.nextInt();
                try {
                    Patient patient = patientService
                                               .searchPatient(searchId);
                    if (patient != null && patient.getIsRemoved() == false) {
                    String pattern = "| %-6s | %-15s | %-15s | %-30s |\n";
                    System.out.pattern(pattern, "Id", "Name", "Type", "Diagnosis");
                    System.out.pattern(pattern, patient.getPatientId()
                                              , patient.getPatientName()
                                              , patient.getPatientType() 
                                              , patient.getAllDaignosis());

                    } else {
                        System.out.println("Data cannot found.");
                    }  
                } catch (Exception e) {
                    System.out.println(e.getMessage()); 
                }      
                break;   

            case 5:
                boolean isFound = false;
                String newFirstName = "";
                String newLastName = "";
                String newType = "";
                System.out.println("Enter patient Id to update :");
                int updatableId = scanner.nextInt();  
                try {
                    Patient newPatient = patientService
                                               .searchPatient(updatableId);    
                    if (newPatient != null && newPatient.isRemoved == false) {
                        System.out.println("Your Given Id is found");
                        System.out.println("Name : " + newPatient.getPatientName()
                                            + "\nType : " + newPatient.getPatientType()
                                            + "\nDiagnosis : " + newPatient
                                                                     .getAllDiagnosis()); 
                        isFound = true;
                        System.out.println("What data do you like to update ? ");
                        System.out.println("1. FirstName \n2. LastName \n3.Type "
                                            + "\n4. Diagnosis");
                        System.out.print("\nEnter choice to update : ");
                        int userChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch(userChoice) {
                        case 1:
                            newFirstName = reader.readName();
                            newPatient.setFirstName(newFirstName);
                            break;

                        case 2:
                            newLastName= reader.read();
                            newPatient.setDateOfBirth(updatedDateOfBirth);
                            break;
                                
                        case 3:
                            newContactNumber = reader.readNumber();
                            newPatient.setNumber(newContactNumber);
                            break;

                        default:
                            System.out.println("Invalid choice. "
                                                + "Please try again.");                                     
                        }
                    
                        boolean isUpdated = operationService.updateEmployee(newPatient);
                        if (isUpdated) {
                            System.out.println("\n----Employee data has "
                                                + "been updated.----");
                        } else {
                            System.out.println("Given data cannot be updated.");
                        }
                    }
                    if (!isFound) {
                        System.out.println("\nSorry cannot find the data.");
                    }
                } catch (EmployeeException e) {
                    System.out.println(e.getMessage()); 
                }
                break;          
            }
        }
    }
}