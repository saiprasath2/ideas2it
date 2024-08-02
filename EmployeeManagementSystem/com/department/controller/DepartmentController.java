package com.department.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.model.Department;
import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employee.controller.OperationController;
import com.model.Employee;
import com.employee.service.OperationService;
import com.employee.service.OperationServiceImpl;
import com.exceptions.EmployeeException;
import com.util.InputValidator;

/**
 * <p>
 * DepartmentController handles all the operations related to the management based on the user's request.
 * It provide endpoints for creating new department details, employee details.
 * </p>
 */
public class DepartmentController {
    DepartmentService departmentService = new DepartmentServiceImpl();
    Scanner scanner = new Scanner(System.in);
    OperationService operationService = new OperationServiceImpl();
    OperationController operationController = new OperationController();
    InputValidator validator = new InputValidator();
    
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
            System.out.println("4. Exit....");
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
                    boolean isValidInput = validator.checkDeptInput(userDepartment);
                    try {
                        if (isValidInput) {
                            departmentService
                                .addDepartment(new Department(userDepartment));
                            validInput = true;
                            System.out.println("Created Successfully!");
                        } else {
                            System.out.println("Enter the format correctly!!");
                        }
                    } catch (EmployeeException e) {
                        System.out.println(e.getMessage()); 
                    }
                }
                break;

            case 2:
                if (departmentService.getDepartments().size() == 0) {
                    System.out.println("No departments to delete! ");
                } else {
                    try {
                        int departmentId = validator.checkDepartment();
                        boolean containInput = departmentService
                                               .checkDeletionStatus(departmentId,
                                               operationService.displayEmployees());                          
                        if (!containInput) {
                            departmentService.deleteDepartment(departmentId);
                            System.out.println("Department deleted successfully!");
                        } else {
                            System.out.println("Cannot delete the linked data!");
                        }
                    } catch (EmployeeException e) {
                        System.out.println(e.getMessage()); 
                    }
                }
                break;

            case 3:
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
                break;
             
            case 4:
                isExit = true;
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}