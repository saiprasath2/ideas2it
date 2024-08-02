package com.util;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.Scanner;

import com.employee.service.EmployeeService;
import com.employee.service.EmployeeServiceImpl;
import com.model.Department;
import com.model.Project;
import com.project.service.ProjectService;
import com.project.service.ProjectServiceImpl;

/**
 * <p>
 * Reads and checks the input from user and displays the validity.
 *
 * Returns the value back to EmployeeController.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public class InputReader {
    public Scanner scanner = new Scanner(System.in);
    ProjectService projectService = new ProjectServiceImpl();
    EmployeeService operationService = new EmployeeServiceImpl();

    /**
     * <p>
     * Reads and returns the validated name to EmployeeController.
     * </p>
     *
     * @return String value of employee name as input. 
     */    
    public String readName() {
        boolean isGoodChoice = false;
        String finalName = null;
        scanner.nextLine();
        while (!isGoodChoice) {
            System.out.println("Give name to be added :");
            String fName = scanner.nextLine();
            boolean isGoodName = checkString(fName);
            if (isGoodName) {
                finalName = fName;
                isGoodChoice = true;
            } else {
                System.out.println("Improper format. Enter a Proper name");
            }
        }
        return finalName;
    }

    /**
     * <p>
     * Reads and returns the formatted dateOfBirth to EmployeeController.
     * </p>
     *
     * @return LocalDate value for age calculation.
     */  
    public LocalDate readDob() {
        scanner.nextLine();
        boolean isGoodChoice = false;
        while (!isGoodChoice) {
            try{
                System.out.println("Give the Date of birth to be "
                                   + "added (yyyy-mm-dd) :"); 
                String dobString = scanner.nextLine();
                LocalDate formatDOB = LocalDate.parse(dobString);
                isGoodChoice = true;
                return formatDOB;      
            } catch (DateTimeParseException e) {
                System.out.println("Enter a valid Date of Birth.");
            } 
        }
        return null;
    }

    /**
     * <p>
     * Checks and returns the validated number to EmployeeController.
     * </p>
     *
     * @return String value of phone number as input.
     */  
    public String readNumber() {
        boolean isGoodChoice = false;
        String finalNumber = null;
        while (!isGoodChoice) {
            System.out.println("Give number to be added :");
            String fNumber = scanner.nextLine();
            boolean isGoodNumber = checkNumber(fNumber);
            if (isGoodNumber) {
                finalNumber = fNumber;
                isGoodChoice = true;
            } else {
                System.out.println("Improper format. Enter a Proper number");
            }
        }
        return finalNumber;
    }

    /**
     * <p>
     * Checks the department availability in map.
     * </p>
     *
     * @return int value of departmentId to be added.
     */
    public int readDepartment() {
        int departmentId =0;
        boolean isGoodChoice = false;
        while (!isGoodChoice) {
            try {
                for (Map.Entry<Integer, Department> emp :
                         operationService.getDepartments().entrySet()) {
                    System.out.println("ID : " + emp.getKey() + "-->"
                                        + "Department Name : "
                                        + emp.getValue().getDepartmentName());
                }
                System.out.print("Enter Department ID: ");
                int departmentIdChoice = scanner.nextInt();  
                if (operationService.getDepartments()
                                        .containsKey(departmentIdChoice)){
                   isGoodChoice = true; 
                   departmentId = departmentIdChoice; 
                } else {
                    System.out.println("Enter valid Department ID !");
                }                              
            } catch (Exception e) {
                System.out.println("Enter numeric format of ID!");
                scanner.next();
            }
        }
        return departmentId;
    }

    /**
     * <p>
     * Checks the project availability in map.
     * </p>
     *
     * @return int value of projectId to be added.
     */
    public int readProjectId() {
        int projectId =0;
        boolean isGoodChoice = false;
        while (!isGoodChoice) {
            try {
                System.out.println("Available Projects");
                for (Map.Entry<Integer, Project> project :
                         projectService.getProjects().entrySet()) {     
                    System.out.println("ID : " + project.getKey() + "-->"
                                        + "Project Name : "
                                        + project.getValue().getProjectName());
                }
                System.out.print("Enter Project ID: ");
                int projectIdChoice = scanner.nextInt();  
                if (projectService.getProjects()
                                        .containsKey(projectIdChoice)){
                   isGoodChoice = true; 
                   projectId = projectIdChoice; 
                } else {
                    System.out.println("Enter valid project ID !");
                }                              
            } catch (Exception e) {
                System.out.println("Enter numeric format of ID!");
                scanner.next();
            }
        }
        return projectId;
    }

    /**
     * <p>
     * Scans and Checks the given project input is available in project List.
     * </p>
     *
     * @return int value to insert id of the project.
     */
    public int checkProject() {
        int projectId =0;
        boolean isGoodChoice = false;
        while (!isGoodChoice) {
            try {
                for (Map.Entry<Integer, Project> entry :
                         projectService.getProjects().entrySet()) {
                    System.out.println("ID : " + entry.getKey() + "-->"
                                        + "Project Name : "
                                        + entry.getValue().getProjectName());
                }
                System.out.print("Enter project ID: ");
                int projectIdChoice = scanner.nextInt();  
                if (projectService.getProjects()
                                        .containsKey(projectIdChoice)){
                   isGoodChoice = true; 
                   projectId = projectIdChoice; 
                } else {
                    System.out.println("Enter valid project ID !");
                }                              
            } catch (Exception e) {
                System.out.println("Enter numeric format of ID!");
                scanner.next();
            }
        }
        return projectId;
    }

    /**
     * </p>
     * Checks the validation of the name.
     * </p>
     *
     * @param checkableName - String value to check the name.
     * @return boolean value to decide validity of input.
     */
    public boolean checkString(String checkableName){
        return checkableName.matches("[a-zA-Z\\s]+") ? true : false;
    }

    /**
     * <p>
     * Checks and returns the validation of the number.
     * </p>
     *
     * @param checkableNumber - String value to check the number.
     * @return boolean value to check validity.
     */
    public boolean checkNumber(String checkableNumber){
        return checkableNumber.matches("\\d{10}") ? true : false;
    } 
 
    /**
     * <p>
     * Checks the validation of the department.
     * </p>
     *
     * @param checkableDept - String value to check the department.
     * @return boolean value of input to decide the input format.
     */
    public boolean checkDeptInput(String checkableDept){
        return checkableDept.matches("[a-zA-Z\\s]+") ? true : false;
    }

    /**
     * <p>
     * Checks the format of input project name.
     * </p>
     *
     * param checkableProject String value to check the format.
     * @return boolean value to validate input.
     */
    public boolean checkProjectInput(String checkableProject){
        return checkableProject.matches("[a-zA-Z0-9 ]+") ? true : false;
    }  
}