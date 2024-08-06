package com.ideas2it.ems.employee.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.query.Query; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.ems.assister.ConnectionAssister;
import com.ideas2it.ems.employee.dao.EmployeeDao;
import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

/*
 * <p>
 * Inserts, deletes, updates and fetches data of the employee.
 *
 * Handles datas of employee along with department entity to display 
 * the employees department wise.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean insertIntoDatabase(Employee employee) throws EmployeeException {
        Transaction transaction = null;   
        Integer id = 0;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            id = (Integer)session.save(employee);
            transaction.commit();
            if (id > 0) {
                return true;
            } 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at adding " + employee.getEmployeeName(), e);
        }
        return false;
    }
    
    @Override
    public boolean removeEmployee(int removableId) throws EmployeeException {
        Transaction transaction = null;   
        int rowsAffected = 0;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("update Employee set isRemoved = true "
                                                 + "where employeeId = :id");
            query.setParameter("id", removableId);
            rowsAffected = query.executeUpdate();
            transaction.commit();
            if (rowsAffected == 1) {
                return true;
            } 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at removing :" + removableId, e);
        }
        return false;   
    }
    
    @Override
    public List<Employee> retrieveEmployees() throws EmployeeException {
        List<Employee> employeeRecord = new ArrayList<>();
        Transaction transaction = null;   
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("from Employee where isRemoved = false",
                                                         Employee.class);
            employeeRecord = query.list();
            transaction.commit();
        } catch (HibernateException e) { 
            System.out.println(e.getMessage());        
            throw new EmployeeException("Error while fetching employees info", e);
        }
        return employeeRecord;
    }
    
    @Override
    public Employee retrieveEmployee(int searchableId) throws EmployeeException {
        Transaction transaction = null;   
        Employee employee = null;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            employee = session.createQuery("from Employee where employeeId = :id"
                                            + " and isRemoved = false", Employee.class).setParameter("id", searchableId).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at searching :" + searchableId, e);
        }
        return employee;
    }

    @Override
    public boolean checkEmployeeRecord() throws EmployeeException {
        boolean isEmpty = true;
        try {
            List<Employee> employeeRecord = retrieveEmployees();
            for (Employee employee : employeeRecord) {
                if (!employee.getIsRemoved()) {
                    isEmpty = false;    
                }
            }
        } catch (HibernateException e) {
            System.out.println("Cannot check the employee.");
            throw new EmployeeException("Error at checking.", e);
        }
        return isEmpty;
    }
   
    @Override
    public boolean updateEmployee(Employee employee) throws EmployeeException {
        Transaction transaction = null;   
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at updating :" + employee.getEmployeeName(), e);
        }
        return true;                 
    }
}