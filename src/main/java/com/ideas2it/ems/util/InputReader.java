package com.ideas2it.ems.util;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.department.service.DepartmentServiceImpl;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.project.service.ProjectService;
import com.ideas2it.ems.project.service.ProjectServiceImpl;

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
    DepartmentService departmentService = new DepartmentServiceImpl();

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
            boolean isGoodName = Util.checkString(fName);
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
            boolean isGoodNumber = Util.checkNumber(fNumber);
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
                         departmentService.getDepartments().entrySet()) {
                    System.out.println("ID : " + emp.getKey() + "-->"
                                        + "Department Name : "
                                        + emp.getValue().getDepartmentName());
                }
                System.out.print("Enter Department ID: ");
                int departmentIdChoice = scanner.nextInt();  
                if (departmentService.getDepartments()
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
     * Reads and returns the validated account number to EmployeeController.
     * </p>
     *
     * @return String value of employee account number as input. 
     */    
    public String readAccount() {
        boolean isGoodChoice = false;
        String finalNumber = null;
        scanner.nextLine();
        while (!isGoodChoice) {
            System.out.println("Give Account number to be added :");
            String fNumber = scanner.nextLine();
            boolean isGoodNumber = Util.checkInput(fNumber);
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
     * Reads and returns the validated ifsc code to EmployeeController.
     * </p>
     *
     * @return String value of employee ifsc code as input. 
     */    
    public String readCode() {
        boolean isGoodChoice = false;
        String finalCode = null;
        scanner.nextLine();
        while (!isGoodChoice) {
            System.out.println("Give IFSC code to be added :");
            String fCode = scanner.nextLine();
            boolean isGoodCode = Util.checkInput(fCode);
            if (isGoodCode) {
                finalCode = fCode;
                isGoodChoice = true;
            } else {
                System.out.println("Improper format. Enter a Proper Code");
            }
        }
        return finalCode;
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
}