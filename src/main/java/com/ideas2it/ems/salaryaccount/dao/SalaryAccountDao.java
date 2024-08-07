package com.ideas2it.ems.salaryaccount.dao;

import java.util.Map;

import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.SalaryAccount;

/*
 *<p>
 * Inserts, deletes, updates and fetches data of the salaryaccount.
 *
 * Handles data's of salary account along with employee entity to display
 * the employees account wise.
 * </p>
 *
 * @author Saiprasath
 * @version 1.4
 */
public interface SalaryAccountDao {

    /**
     * <p>
     * creates the account and inserts it to the storage with auto-incremented Id.
     * </p>
     *
     * @param salaryAccount SalaryAccount value to insert account details.
     * @throws EmployeeException when there is no proper details provided.
     */
     void insertAccount(SalaryAccount salaryAccount) throws EmployeeException;

    /**
     * <p>
     * Retrieves the accounts available.
     * </p>
     *
     * @return Map<> value of available projects.
     * @throws EmployeeException when there is no proper details provided. 
     */
    Map<Integer, SalaryAccount> getEmployeeAccounts() throws EmployeeException;

    /**
     * <p>
     * Retrieves required account by the user.
     * </p>
     *
     * @param accountId - int value to fetch the project.
     * @return account value to display the project.
     * @throws EmployeeException when there is no proper details provided.
     */
    SalaryAccount getAccount(int accountId) throws EmployeeException;
  
    /**
     * <p>
     * Updates the account and inserts it to the storage with auto-incremented Id.
     * </p>
     *
     * @param salaryAccount to update account details.
     * @throws EmployeeException when there is no proper details provided.
     */
    void updateAccount(SalaryAccount salaryAccount) throws EmployeeException;
}
