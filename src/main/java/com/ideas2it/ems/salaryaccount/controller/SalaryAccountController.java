package com.ideas2it.ems.salaryaccount.controller;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.SalaryAccount;
import com.ideas2it.ems.employee.service.EmployeeService;
import com.ideas2it.ems.employee.service.EmployeeServiceImpl;
import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.salaryaccount.service.SalaryAccountService;
import com.ideas2it.ems.salaryaccount.service.SalaryAccountServiceImpl;
import com.ideas2it.ems.util.InputReader;

/**
 * <p>
 * This Controller handles all the operations related to the account based on the user's request.
 * It provide endpoints for creating new account details and employee along with the other operations,includes
 * - retrieving the whole employee detail along with their associated account.
 * - updating the account of employee.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public class SalaryAccountController {
    SalaryAccountService salaryAccountService = new SalaryAccountServiceImpl();
    EmployeeService operationService = new EmployeeServiceImpl();
    private static Logger logger = LogManager.getLogger();
    Scanner scanner = new Scanner(System.in);
    InputReader reader = new InputReader();

    /**
     * Scans input choice from user.
     */
    public void assistAccount() {
        SalaryAccountController salaryAccountController = new SalaryAccountController();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Account Management :");
            System.out.println("1. Would you like to display a account?");
            System.out.println("2. Would you like to search a account?");
            System.out.println("3. Back");
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
                try {
                    if (salaryAccountService.getAccounts().size() == 0) {
                        System.out.println("We have no accounts yet.");
                    } else {
                        System.out.println("Avalaible accounts :");
                        displayAccounts();
                    } 
                } catch (EmployeeException e) {
                    logger.error(e.getMessage()); 
                }  
                break;

            case 2:
                System.out.println("Enter the Id of the employee : ");
                try {
                    List<Employee> employeeDetails = operationService
                                                          .displayEmployees();
                    System.out.println("We have " + employeeDetails.size() 
                                              + " employees.");
                    String format = "| %-6s | %-15s |\n";
                    System.out.format(format, "Id", "Name");
                    for (Employee employee : employeeDetails) {
                        if (employee.getIsRemoved() == false) {
                            System.out.format(format, employee.getEmployeeId()
                                               , employee.getEmployeeName());
                        }
                    } 
                    int searchableId = scanner.nextInt();
                    Employee employerDetail = operationService
                                               .searchEmployee(searchableId);
                    if (employerDetail != null && employerDetail.getIsRemoved() == false) {
                        String pattern = "| %-6s | %-15s | %-15s | %-30s | %-30s |\n";
                        System.out.format(pattern, "Id", "Name", "Department",
                                           "Account No.", "IFSC code");
                        System.out.format(pattern, employerDetail.getEmployeeId()
                                          , employerDetail.getEmployeeName()
                                          , employerDetail.getDepartment().getDepartmentName()
                                          , employerDetail.getSalaryAccount().getAccountName()
                                          , employerDetail.getSalaryAccount().getIfscCode());

                    } else {
                        logger.info("Data of " + searchableId + " cannot found");
                    }  
                } catch (EmployeeException e) {
                    logger.error(e.getMessage()); 
                }      
                break;

            case 3:
                isExit = true;
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays the Account List inserted
     */
    public void displayAccounts() {
        try {
            for (Map.Entry<Integer, SalaryAccount> entry 
                 : salaryAccountService.getAccounts().entrySet()) {
                System.out.println("ID : " + entry.getKey() 
                                   + "Name : " + entry.getValue().getAccountName());
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage()); 
        }
    }
}