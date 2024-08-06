package com.ideas2it.ems.salaryaccount.service;

import java.util.Map;
import java.util.Set;

import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.SalaryAccount;
import com.ideas2it.ems.salaryaccount.dao.SalaryAccountDao;
import com.ideas2it.ems.salaryaccount.dao.SalaryAccountDaoImpl;

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
    public int addAccount(SalaryAccount salaryAccount) throws EmployeeException {
        return salaryAccountDao.insertAccount(salaryAccount);
    }

    @Override
    public Map<Integer, SalaryAccount> getAccounts() throws EmployeeException {
        return salaryAccountDao.retrieveEmployeeAccounts();
    }

    @Override 
    public SalaryAccount getAccount(int accountId) throws EmployeeException {
        return salaryAccountDao.retrieveAccount(accountId);
    }

    @Override
    public void updateAccount(SalaryAccount salaryAccount) throws EmployeeException {
        salaryAccountDao.updateAccount(salaryAccount);
    }
}