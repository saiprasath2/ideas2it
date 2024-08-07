package com.ideas2it.ems.salaryaccount.service;

import java.util.Map;

import com.ideas2it.ems.exceptions.EmployeeException;
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
 * version 1.4
 */
public class SalaryAccountServiceImpl implements SalaryAccountService {
    SalaryAccountDao salaryAccountDao = new SalaryAccountDaoImpl();

    @Override
    public void addAccount(SalaryAccount salaryAccount) throws EmployeeException {
        salaryAccountDao.insertAccount(salaryAccount);
    }

    @Override
    public Map<Integer, SalaryAccount> getAccounts() throws EmployeeException {
        return salaryAccountDao.getEmployeeAccounts();
    }

    @Override 
    public SalaryAccount getAccount(int accountId) throws EmployeeException {
        return salaryAccountDao.getAccount(accountId);
    }

    @Override
    public void updateAccount(SalaryAccount salaryAccount) throws EmployeeException {
        salaryAccountDao.updateAccount(salaryAccount);
    }
}