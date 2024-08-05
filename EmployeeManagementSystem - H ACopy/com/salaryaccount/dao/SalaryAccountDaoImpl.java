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
    public int insertAccount(SalaryAccount salaryAccount) throws EmployeeException {
        Transaction transaction = null;   
        Integer id = 0;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();  
            id = (Integer) session.save(salaryAccount);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Account cannot be added with name : " 
                                        + salaryAccount.getAccountName(), e);
        }
        return id;
    }

    @Override
    public Map<Integer, SalaryAccount> retrieveEmployeeAccounts() throws EmployeeException {
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            throw new EmployeeException("Account cannot be updated with name : " 
                                        + salaryAccount.getAccountName(), e);
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