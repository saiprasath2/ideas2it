package com.ideas2it.ems.department.controller;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.department.service.DepartmentServiceImpl;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.util.InputReader;

/**
 * <p>
 * DepartmentController handles all the operations related to the management based on the user's request.
 * It provide endpoints for creating new department details, employee details.
 * </p>
 *
 * @author Saiprasath
 * @version 1.4
 */
public class DepartmentController {
    DepartmentService departmentService = new DepartmentServiceImpl();
    private static Logger logger = LogManager.getLogger();
    Scanner scanner = new Scanner(System.in);
    InputReader reader = new InputReader();
    
    /**
     * Displays available options to user.
     *
     * Passes the parameters to methods at OperationService 
     */
    public void assistDepartment() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Department Services : ");
            System.out.println("1. Would you like to Add a Department?");
            System.out.println("2. Would you like to Remove a department?");
            System.out.println("3. Would you like to Display the department" 
                                + " list?"); 
            System.out.println("4. Would you like to view Employees department"
                                + " wise?" );
            System.out.println("5. Exit....");
            int userWish = 0;
            boolean isValid = false;
            while (!isValid) {
                try {
                    System.out.println("Enter choice : ");
                    userWish = scanner.nextInt();
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
                    logger.debug("Checks the entered input");
                    System.out.println("Give the department to be added :");
                    String userDepartment = scanner.nextLine();
                    boolean isValidInput = reader.checkDeptInput(userDepartment);
                    try {
                        if (isValidInput) {
                            Department isInserted = departmentService
                                .addDepartment(userDepartment);
                            validInput = true;
                            if (null != isInserted) {
                                logger.info(isInserted.getDepartmentName() + "Created Successfully!");
                            } else {
                                logger.info(userDepartment + "Failed to create");
                            }    
                        } else {
                            System.out.println("Enter the format correctly!!");
                        }
                    } catch (EmployeeException e) {
                        logger.error(e.getMessage()); 
                    }
                }
                break;

            case 2:
                try {
                    if (departmentService.getDepartments().isEmpty()) {
                        System.out.println("No departments to delete! ");
                    } else {
                        int departmentId = reader.readDepartment();
                        Set<Employee> employeeRecord = departmentService.getEmployeesOfDepartment(departmentId);                        
                        if (employeeRecord.isEmpty()) {
                            Department isDeleted = departmentService.deleteDepartment(departmentId);
                            if (null != isDeleted) {
                                logger.info("Id : " + departmentId + " deleted successfully!");
                            } else {
                                logger.info("Id : " + departmentId + "Deletion failed");
                            }
                        } else {
                            logger.warn("Cannot delete the linked data -->" + departmentId);
                        }
                    } 
                } catch (EmployeeException e) {
                    logger.error(e.getMessage()); 
                }
                break;

            case 3:
                try {
                    if (departmentService.getDepartments().isEmpty()) {
                        System.out.println("We have no departments yet.");
                    } else {
                        for (Map.Entry<Integer, Department> dept :
                            departmentService.getDepartments().entrySet()) {
                            System.out.println("ID : " + dept.getKey() + " --> " 
                                               + "Department Name : "
                                               + dept.getValue().getDepartmentName());
                        }
                    }
                } catch (EmployeeException e) {
                    logger.error(e.getMessage()); 
                }
                break;

            case 4:
                try {
                    int departmentId = reader.readDepartment();
                    String pattern1 = "| %-6s | %-15s | %-15s | %-15s |"
                                               + " %-30s | %-6s |\n";
                    System.out.format(pattern1, "Id", "Name", "Contact"
                                      , "Department", "Project", "Age");
                    Set<Employee> employees = departmentService
                                              .getEmployeesOfDepartment(departmentId);
                    if (!employees.isEmpty()) {
                        for (Employee employee : employees) {
                              System.out.format(pattern1, employee.getEmployeeId()
                                                , employee.getEmployeeName()
                                                , employee.getContactNumber() 
                                                , employee.getDepartment()
                                                          .getDepartmentName()
                                                , employee.getAllProjects()
                                                , employee.getAge());
                        }
                    } else {
                        logger.info("No employees found in {}", departmentId);
                    }
                } catch (EmployeeException e) {
                    logger.error(e.getMessage()); 
                }
                break;
             
            case 5:
                isExit = true;
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}