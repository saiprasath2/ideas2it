package com.salaryaccount.service;

import java.util.Map;
import java.util.Set;

import com.exceptions.EmployeeException;
import com.model.Employee;
import com.model.SalaryAccount;
import com.salaryaccount.dao.SalaryAccountDao;
import com.salaryaccount.dao.SalaryAccountDaoImpl;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the accounts.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public class SalaryAccountServiceImpl implements SalaryAccountService {
    SalaryAccountDao salaryAccountDao = new SalaryAccountDaoImpl();

    @Override
    public boolean addAccount(String accountName, String IfscCode) throws EmployeeException {
        return salaryAccountDao.insertAccount(accountName, IfscCode);
    

    @Override
    public Map<Integer, SalaryAccount> getAccounts() throws EmployeeException {
        return salaryAccountDao.retrieveEmployeeAccounts();
    }

    @Override 
    public SalaryAccount getAccount(int accountId) throws EmployeeException {
        return salaryAccountDao.retrieveAccount(accountId);
    }
}