package com.salaryaccount.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;

import com.assister.ConnectionAssister;
import com.exceptions.EmployeeException;
import com.model.Employee;
import com.model.SalaryAccount;
import com.project.dao.ProjectDao;
import org.hibernate.HibernateException; 
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
public class SalaryAccountDaoImpl implements SalaryAccountDao {
    @Override
    public boolean insertAccount(String accountName, String IfscCode) throws EmployeeException {
        Transaction transaction = null;   
        Integer id = 0;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); 
            SalaryAccount salaryAccount = new SalaryAccount(accountName, IfscCode);  
            id = (Integer) session.saveOrUpdate(salaryAccount);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Account cannot be added with name : " 
                                        + accountName, e);
        }
        if (id > 0) {
           return true;
        }
        return false;
    }

    @Override
    public Map<Integer, SalaryAccount> retrieveEmployeeAccounts() throws EmployeeException {
        Transaction transaction = null;   
        Map<Integer, SalaryAccount> accounts = new HashMap<>();
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Project> query = session.createQuery("from SalaryAccount", SalaryAccount.class);
            List<SalaryAccount> accountsFromRecord = query.list();
            for (SalaryAccount account : accountsFromRecord) {
                accounts.put(account.getAccountId(), account);
            }
            transaction.commit();
            return accounts;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Accounts cannot be retrieved!", e);
        } 
    }

    @Override
    public SalaryAccount retrieveAccount(int accountId) throws EmployeeException {  
        Transaction transaction = null;
        SalaryAccount salaryAccount = null;   
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            salaryAccount = session.get(SalaryAccount.class, accountId);
            transaction.commit();
            return salaryAccount;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at searching " + accountId, e);
        }
    } 
}