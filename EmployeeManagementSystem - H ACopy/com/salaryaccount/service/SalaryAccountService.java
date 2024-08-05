package com.salaryaccount.service;

import java.util.Map;
import java.util.Set;

import com.exceptions.EmployeeException;
import com.model.Employee;
import com.model.SalaryAccount;


/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the accounts.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public interface SalaryAccountService {
    
    /**
     * <p>
     * Passes account details to insertAccount to add.
     * </p>
     *
     * @param salaryAccount SalaryAccount value to add account details.
     * @return int value to indicate insertion status.
     * @throws EmployeeException when insertion is failed.
     */
    public int addAccount(SalaryAccount salaryAccount) throws EmployeeException;

    /**
     * <p>
     * Retrieves the accounts available.
     * </p>
     *
     * @return Map<> value of available projects.
     * @throws EmployeeException when there is no proper details provided. 
     */
    public Map<Integer, SalaryAccount> getAccounts() throws EmployeeException;

    /**
     * <p>
     * Retrieves required account by the user.
     * </p>
     *
     * @param accountId - int value to fetch the project.
     * @return account value to display the project.
     * @throws EmployeeException when there is no proper details provided.
     */  
    public SalaryAccount getAccount(int accountId) throws EmployeeException;   

    /**
     * <p>
     * Passes account details to updateAccount to add.
     * </p>
     *
     * @param salaryAccount SalaryAccount value to update account details.
     * @throws EmployeeException when insertion is failed.
     */
    public void updateAccount(SalaryAccount salaryAccount) throws EmployeeException;  
}