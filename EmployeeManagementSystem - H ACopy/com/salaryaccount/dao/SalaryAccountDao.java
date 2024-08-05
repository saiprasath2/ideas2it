package com.salaryaccount.dao;

import java.util.Map;

import com.exceptions.EmployeeException;
import com.model.SalaryAccount;

/*
 *<p>
 * Inserts, deletes, updates and fetches data of the salaryaccount.
 *
 * Handles datas of salaryaccount along with employee entity to display 
 * the employees account wise.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public interface SalaryAccountDao {

    /**
     * <p>
     * creates the account and inserts it to the storage with auto-incremented Id.
     * </p>
     *
     * @param accountName
     *         For which account could not be null.
     * @param IfscCode to insert ifsc code of account.
     * @return boolean value to indicate insertion status.
     * @throws EmployeeException when there is no proper details provided.
     */    
    public boolean insertAccount(String accountName, String IfscCode) throws EmployeeException;

    /**
     * <p>
     * Retrieves the accounts available.
     * </p>
     *
     * @return Map<> value of available projects.
     * @throws EmployeeException when there is no proper details provided. 
     */
    public Map<Integer, SalaryAccount> retrieveEmployeeAccounts() throws EmployeeException;

    /**
     * <p>
     * Retrieves required account by the user.
     * </p>
     *
     * @param accountId - int value to fetch the project.
     * @return account value to display the project.
     * @throws EmployeeException when there is no proper details provided.
     */  
    public SalaryAccount retrieveAccount(int accountId) throws EmployeeException;      
}
