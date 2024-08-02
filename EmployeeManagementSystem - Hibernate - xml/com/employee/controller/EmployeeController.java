package com.employee.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employee.service.EmployeeService;
import com.employee.service.EmployeeServiceImpl;
import com.exceptions.EmployeeException;
import com.model.Department;
import com.model.Employee;
import com.model.Project;
import com.project.service.ProjectService;
import com.project.service.ProjectServiceImpl;
import com.util.InputReader;

/**
 * <p>
 * OperationController handles all the operations related to the management based on the user's request.
 * It provide endpoints for creating new employee details, project details and department along with the other operations,includes
 * - retrieving the whole employee details along with their associated department and project.
 * - removing the student from all associated departments, project.
 * </p>
 *
 * @author Saiprasath 
 * @version 1.0
 */
public class EmployeeController {
    EmployeeService operationService = new EmployeeServiceImpl();
    ProjectService projectService = new ProjectServiceImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();
    InputReader reader = new InputReader();
    Scanner scanner = reader.scanner;

    /**
     * Displays available options to user.
     *
     * Passes the parameters to methods at OperationService 
     */
    public void assistEmployee() {
        EmployeeController operationController = new EmployeeController();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Employee Management System: ");
            System.out.println("1. Would you like to Add an Employee?");
            System.out.println("2. Would you like to Remove an Employee?");
            System.out.println("3. Would you like to Display the Employees" 
                                + " List?");
            System.out.println("4. Would you like to Search an Employee?");
            System.out.println("5. Would you like to update an Employee?");
            System.out.println("6. Would you like to add assign project to "
                                + "an Employee?");
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
                String employeeName = reader.readName();
                LocalDate employeeDob = reader.readDob();
                String contactNumber = reader.readNumber();
                int department = reader.readDepartment();
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
                int removableId = scanner.nextInt();
                try {
                    boolean isRemoved = operationService
                                           .removeEmployee(removableId);
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
                try {
                    List<Employee> employeeDetails = operationService
                                                          .displayEmployees();
                    System.out.println("We have " + employeeDetails.size() 
                                              + " employees.");
                    String format = "| %-6s | %-15s | %-15s | %-15s | %-30s | %-15s |\n";
                    System.out.format(format, "Id", "Name", "Contact", "Department", 
                                      "Projects", "Age");
                    for (Employee employee : employeeDetails) {
                        if (employee.getIsRemoved() == false) {
                            System.out.format(format, employee.getEmployeeId()
                                              , employee.getEmployeeName()
                                              , employee.getContactNumber() 
                                              , employee.getDepartment().getDepartmentName()
                                              , employee.getAllProjects()
                                              , employee.getAge());
                        }
                    } 
                    System.out.println("----All of the employers have been"
                                        + " displayed.----"); 
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 4:
                System.out.println("Enter the Id of the employee : ");
                int searchableId = scanner.nextInt();
                try {
                    Employee employerDetail = operationService
                                               .searchEmployee(searchableId);
                    if (employerDetail != null && employerDetail.getIsRemoved() == false) {
                        String pattern = "| %-6s | %-15s | %-15s | %-15s | %-30s | %-15s |\n";
                        System.out.format(pattern, "Id", "Name", "Contact"
                                          , "Department", "project", "Age");
                        System.out.format(pattern, employerDetail.getEmployeeId()
                                          , employerDetail.getEmployeeName()
                                          , employerDetail.getContactNumber() 
                                          , employerDetail.getDepartment().getDepartmentName()
                                          , employerDetail.getAllProjects()
                                          , employerDetail.getAge());

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
                    if (employer != null && employer.getIsRemoved() == false) {
                        System.out.println("Your Given Id is found");
                        System.out.println("Name : " + employer.getEmployeeName()
                                            + "\nDOB : " + employer.getAge()
                                            + "\nContactNumber : " + employer
                                                                     .getContactNumber()
                                            + "\nDepartment : " + employer
                                                                  .getDepartment()
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
                            newName = reader.readName();
                            employer.setEmployeeName(newName);
                            break;

                        case 2:
                            updatedDateOfBirth = reader.readDob();
                            employer.setDateOfBirth(updatedDateOfBirth);
                            break;
                                
                        case 3:
                            newContactNumber = reader.readNumber();
                            employer.setContactNumber(newContactNumber);
                            break;

                        case 4:
                            newDepartment = reader.readDepartment(); 
                            Department departmentName = departmentService
                                               .getDepartment(newDepartment);                            
                            employer.setDepartment(departmentName);
                            break;

                        default:
                            System.out.println("Invalid choice. "
                                                + "Please try again.");                                     
                        }
                    
                        boolean isUpdated = operationService.updateEmployee(employer);
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
                boolean isAssigned = false;
                try {
                    if (operationService.displayEmployees().isEmpty()) {
                        System.out.println("No data found !");    
                    } else if (projectService.getProjects().size() == 0){
                        System.out.println("No project found !");
                        System.out.println("Add a project please...");
                    } else {
                        System.out.println("Enter Employee id to assign :");
                        int assignId = scanner.nextInt();
                        Employee employee = operationService
                                               .searchEmployee(assignId);
                        if (employee != null && employee.getIsRemoved() == false) {
                            int projectId = reader.readProjectId();
                            Set<Employee> employeeRecord = projectService
                                                            .getEmployeesOfProjects(projectId);
                            if (projectService.getProjects().containsKey(projectId)) {
                                for (Employee employees : employeeRecord) {
                                    if (employees.getEmployeeId() == assignId) {
                                        isAssigned = true;
                                    }    
                                } 
                                if (isAssigned) {
                                    System.out.println("Enter valid input. "
                                                        + "Employee already assigned"
                                                        + " with this project.");
                                } else {
                                    projectService.addEmployee(projectId, employee);
                                    System.out.println("Assigned Successfully!");
                                }    
                            } else {
                                System.out.println("Enter valid input. "
                                                    + "No such Project!");
                            }
                        } else {
                            System.out.println("Employee not found");
                        }
                    }   
                } catch (EmployeeException e) {
                    System.out.println(e.getMessage()); 
                } 
                break;

            case 7:
                isExit = true;
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}