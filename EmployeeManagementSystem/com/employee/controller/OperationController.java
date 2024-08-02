package com.employee.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.model.Department;
import com.model.Employee;
import com.employee.service.OperationService;
import com.employee.service.OperationServiceImpl;
import com.exceptions.EmployeeException;
import com.model.Project;
import com.project.service.ProjectService;
import com.project.service.ProjectServiceImpl;
import com.util.InputValidator;

/**
 * <p>
 * OperationController handles all the operations related to the management based on the user's request.
 * It provide endpoints for creating new employee details, project details and department along with the other operations,includes
 * - retrieving the whole employee details along with their associated department and project.
 * - removing the student from all associated departments, project.
 * </p>
 */
public class OperationController {
    OperationService operationService = new OperationServiceImpl();
    ProjectService projectService = new ProjectServiceImpl();
    InputValidator validator = new InputValidator();
    public static List<Employee> employeeList = new ArrayList<>();
    Scanner scanner = validator.scanner;

    /**
     * Displays available options to user.
     *
     * Passes the parameters to methods at OperationService 
     */
    public void assistEmployee() {
        OperationController operationController = new OperationController();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Employee Management System: ");
            System.out.println("1. Would you like to Add an Employee?");
            System.out.println("2. Would you like to Remove an Employee?");
            System.out.println("3. Would you like to Display the Employees" 
                                + " List?");
            System.out.println("4. Would you like to Search an Employee?");
            System.out.println("5. Would you like to update an Employee?");
            System.out.println("6. Would you like to view by Department?");
            System.out.println("7. Would you like to add assign project to "
                                + "an Employee?");
            System.out.println("8. Do you want to Exit?");
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
                String employeeName = validator.validateName();
                LocalDate employeeDob = validator.validateDob();
                String contactNumber = validator.validateNumber();
                int department = validator.checkDepartment();
                try {
                    boolean isAdded = operationService.addEmployee(employeeName,
                                                 employeeDob, 
                                                 contactNumber, department);
                    if (isAdded) {
                        System.out.println("Added Successfully!!");
                    } else {
                        System.out.println("Failed to add!!");
                    }
                } catch (EmployeeException e) {
                    System.out.println(e.getMessage()); 
                }
                break;

            case 2:
                System.out.println("Enter the Employee Id to remove : ");
                int removeId = scanner.nextInt();
                try {
                    boolean isRemoved = operationService
                                           .removeEmployee(removeId);
                    if (isRemoved) {
                        System.out.println("\n----Employee data has "
                                            + "been removed.----");
                    } else {
                        System.out.println("Given data cannot be found.");
                    }
                } catch (EmployeeException e) {
                    System.out.println(e.getMessage()); 
                }
                break;

            case 3:
                List<Employee> employeeList = operationService
                                                          .displayEmployees();
                System.out.println("We have " + employeeList.size() 
                                              + " employees.");
                String format = "| %-6s | %-15s | %-15s | %-15s | %-30s | %-15s |\n";
                System.out.format(format, "Id", "Name", "Contact", "Department", 
                                  "Projects", "Age");
                for (Employee employee : employeeList) {
                    if (employee.isRemoved == false) {
                        System.out.format(format, employee.getId()
                                          , employee.getName()
                                          , employee.getNumber() 
                                          , employee.getDepartmentName().getDepartmentName()
                                          , employee.getAllProjects()
                                          , employee.getAge());
                    }
                } 
                System.out.println("----All of the employers have been"
                                    + " displayed.----"); 
                break;

            case 4:
                System.out.println("Enter the Id of the employee : ");
                int searchId = scanner.nextInt();
                try {
                    Employee employerList = operationService
                                               .searchEmployee(searchId);
                    if (employerList != null && employerList.isRemoved == false) {
                        String pattern = "| %-6s | %-15s | %-15s | %-15s | %-30s | %-15s |\n";
                        System.out.format(pattern, "Id", "Name", "Contact"
                                          , "Department", "project", "Age");
                        System.out.format(pattern, employerList.getId()
                                          , employerList.getName()
                                          , employerList.getNumber() 
                                          , employerList.getDepartmentName().getDepartmentName()
                                          , employerList.getAllProjects()
                                          , employerList.getAge());

                    } else {
                        System.out.println("Data cannot found.");
                    }  
                } catch (EmployeeException e) {
                    System.out.println(e.getMessage()); 
                }      
                break;

            case 5:
                boolean isFound = false;
                int newDepartment = 0;
                String newContactNumber = "";
                String newName = "";
                LocalDate updatedDateOfBirth = LocalDate.now();
             	System.out.println("Enter Employee Id to update :");
                int updateId = scanner.nextInt();
                try {
                    Employee employer = operationService
                                               .searchEmployee(updateId);    
                    if (employer != null && employer.isRemoved == false) {
                        System.out.println("Your Given Id is found");
                        System.out.println("Name : " + employer.getName()
                                            + "\nDOB : " + employer.getAge()
                                            + "\nContactNumber : " + employer
                                                                    .getNumber()
                                            + "\nDepartment : " + employer
                                                                  .getDepartmentName()
                                                                  .getDepartmentName()); 
                        isFound = true;
                        System.out.println("What data do you like to update ? ");
                        System.out.println("1. Name\n2. DOB \n3. ContactNumber"
                                            + "\n4. Department");
                        System.out.print("\nEnter choice to update : ");
                        int userChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch(userChoice) {
                        case 1:
                            newName = validator.validateName();
                            break;

                        case 2:
                            updatedDateOfBirth = validator.validateDob();
                            break;
                                
                        case 3:
                            newContactNumber = validator.validateNumber();
                            break;

                        case 4:
                            newDepartment = validator.checkDepartment();
                            break;

                        default:
                            System.out.println("Invalid choice. "
                                                + "Please try again.");                                     
                        }
                    
                        boolean isUpdated = operationService.updateEmployee(updateId, 
                                                              userChoice, newName,
                                                              updatedDateOfBirth,
                                                              newContactNumber,
                                                              newDepartment);
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

            case 6:
                if (!operationService.checkEmptyList()) {
                    scanner.nextLine();
                    boolean isPresent = false;
                    int departmentChoice = validator.checkDepartment();
                    try {
                        List<Employee> employeeDeptWise = operationService
                                            .viewByDepartment(departmentChoice);        
                        String pattern1 = "| %-6s | %-15s | %-15s | %-15s | %-30s | %-15s |\n";
                        System.out.format(pattern1, "Id", "Name", "Contact"
                                              , "Department", "Project", "Age");
                        for (Employee employee : employeeDeptWise) {
                            isPresent = true;
                            System.out.format(pattern1, employee.getId()
                                              , employee.getName()
                                              , employee.getNumber() 
                                              , employee.getDepartmentName().getDepartmentName()
                                              , employee.getAllProjects()
                                              , employee.getAge());   
                        }
                        if (!isPresent) {
                            System.out.println("No Employees found");
                        }
                    } catch (EmployeeException e) {
                        System.out.println(e.getMessage()); 
                    }
                } else {
                    System.out.println("No data found!");
                }
                break; 

            case 7:
                if (operationService.displayEmployees().isEmpty()) {
                    System.out.println("No data found !");    
                } else if (projectService.getProjects().size() == 0){
                    System.out.println("No project found !");
                    System.out.println("Add a project please...");
                } else {
                    System.out.println("Enter Employee id to assign :");
                    int assignId = scanner.nextInt();
                    try {
                        Employee employee = operationService
                                               .searchEmployee(assignId);
                        if (employee != null && employee.isRemoved == false) {
                            int projectId = validator.checkProjectId();
                            if (projectService.getProjects().containsKey(projectId)) {
                                projectService.addEmployee(projectId, employee);
                                System.out.println("Assigned Successfully!");
                            } else {
                               System.out.println("Enter valid input.");
                            }
                        } else {
                            System.out.println("Employee not found");
                        }
                    } catch (EmployeeException e) {
                    System.out.println(e.getMessage()); 
                    }   
                }
                break;

            case 8:
                isExit = true;
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}