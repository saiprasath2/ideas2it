package com.ideas2it.ems.salaryaccount.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ems.assister.ConnectionAssister;
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
public class SalaryAccountDaoImpl implements SalaryAccountDao {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public  void insertAccount(SalaryAccount salaryAccount) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();  
            session.save(salaryAccount);
            transaction.commit();

        } catch (HibernateException e) {
            logger.error("Account cannot be added with name : {}", salaryAccount.getAccountName());
            throw new EmployeeException("Account cannot be added with name : " 
                                        + salaryAccount.getAccountName(), e);
        }
    }

    @Override
    public Map<Integer, SalaryAccount> getEmployeeAccounts() throws EmployeeException {
        Transaction transaction = null;   
        Map<Integer, SalaryAccount> accounts = new HashMap<>();
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<SalaryAccount> query = session.createQuery("from SalaryAccount", SalaryAccount.class);
            List<SalaryAccount> accountsFromRecord = query.list();
            for (SalaryAccount account : accountsFromRecord) {
                accounts.put(account.getAccountId(), account);
            }
            transaction.commit();
            return accounts;
        } catch (HibernateException e) {
            logger.error("Accounts cannot be retrieved!");
            throw new EmployeeException("Accounts cannot be retrieved!", e);
        } 
    }

    @Override
    public void updateAccount(SalaryAccount salaryAccount) throws EmployeeException {
        Transaction transaction = null;   
        Integer id = 0;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();  
            session.saveOrUpdate(salaryAccount);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Account cannot be updated with name : {}", salaryAccount.getAccountName());
            throw new EmployeeException("Account cannot be updated with name : " 
                                        + salaryAccount.getAccountName(), e);
        }
    }

    @Override
    public SalaryAccount getAccount(int accountId) throws EmployeeException {
        Transaction transaction = null;
        SalaryAccount salaryAccount = null;   
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            salaryAccount = session.get(SalaryAccount.class, accountId);
            transaction.commit();
            return salaryAccount;
        } catch (HibernateException e) {
            logger.error("Error at searching account with Id : {}", accountId);
            throw new EmployeeException("Error at searching account with Id : " + accountId, e);
        }
    } 
}