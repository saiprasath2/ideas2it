package com.department.controller;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.model.Department;
import com.model.Employee;
import com.exceptions.EmployeeException;
import com.util.InputReader;

/**
 * <p>
 * DepartmentController handles all the operations related to the management based on the user's request.
 * It provide endpoints for creating new department details, employee details.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public class DepartmentController {
    DepartmentService departmentService = new DepartmentServiceImpl();
    Scanner scanner = new Scanner(System.in);
    InputReader reader = new InputReader();
    
    /**
     * Displays available options to user.
     *
     * Passes the parameters to methods at OperationService 
     */
    public void assistDepartment() {
        DepartmentController departmentController = new DepartmentController();
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
                    System.out.println("Give the department to be added :");
                    String userDepartment = scanner.nextLine();
                    boolean isValidInput = reader.checkDeptInput(userDepartment);
                    boolean isInserted = false;
                    try {
                        if (isValidInput) {
                            isInserted = departmentService
                                .addDepartment(userDepartment);
                            validInput = true;
                            if (isInserted) {
                                System.out.println("Created Successfully!");
                            } else {
                                System.out.println("Failed to create");
                            }    
                        } else {
                            System.out.println("Enter the format correctly!!");
                        }
                    } catch (EmployeeException e) {
                        System.out.println(e.getMessage()); 
                    }
                }
                break;

            case 2:
                boolean isDeleted = false;
                try {
                    if (departmentService.getDepartments().size() == 0) {
                        System.out.println("No departments to delete! ");
                    } else {
                        int departmentId = reader.readDepartment();
                        Set<Employee> employeeRecord = departmentService.getEmployeesOfDepartment(departmentId);                        
                        if (employeeRecord.size() == 0) {
                            isDeleted = departmentService.deleteDepartment(departmentId);
                            if (isDeleted) {
                                System.out.println("Department deleted successfully!");
                            } else {
                                System.out.println("Deletion failed");
                            }
                        } else {
                            System.out.println("Cannot delete the linked data!");
                        }
                    } 
                } catch (EmployeeException e) {
                    System.out.println(e.getMessage()); 
                }
                break;

            case 3:
                try {
                    if (departmentService.getDepartments().size() == 0) {
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
                    System.out.println(e.getMessage()); 
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
                    if (employees.size() != 0) {
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
                        System.out.println("No employees found !");
                    }
                } catch (EmployeeException e) {
                    System.out.println(e.getMessage()); 
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