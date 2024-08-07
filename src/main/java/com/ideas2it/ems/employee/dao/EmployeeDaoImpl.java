package com.ideas2it.ems.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.HibernateException;
import org.hibernate.query.Query; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.ems.assister.ConnectionAssister;
import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.Employee;

/*
 * <p>
 * Inserts, deletes, updates and fetches data of the employee.
 *
 * Handles data's of employee along with department entity to display
 * the employees department wise.
 * </p>
 *
 * @author Saiprasath
 * @version 1.4
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Employee insertIntoDatabase(Employee employee) throws EmployeeException {
        Transaction transaction = null;   
        Integer id = 0;
        logger.debug("Creating the new employee {}", employee.getEmployeeName());
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            id = (Integer)session.save(employee);
            transaction.commit();
            if (id > 0) {
                return employee;
            } 
        } catch (HibernateException e) {
            logger.info("Error at adding {}", employee.getEmployeeName());
            throw new EmployeeException("Error at adding " + employee.getEmployeeName(), e);
        }
        return null;
    }
    
    @Override
    public Employee removeEmployee(int removableId) throws EmployeeException {
        Transaction transaction = null;   
        int rowsAffected = 0;
        logger.debug("Removing given employee with Id ; {}", removableId);
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("update Employee set isRemoved = true "
                                                 + "where employeeId = :id");
            query.setParameter("id", removableId);
            rowsAffected = query.executeUpdate();
            transaction.commit();
            Employee employee = getEmployee(removableId);
            if (rowsAffected == 1) {
                return employee;
            } 
        } catch (HibernateException e) {
            logger.info("Error at removing :{}", removableId);
            throw new EmployeeException("Error at removing :" + removableId, e);
        }
        return null;
    }
    
    @Override
    public List<Employee> getEmployees() throws EmployeeException {
        List<Employee> employeeRecord = new ArrayList<>();
        Transaction transaction = null;
        logger.debug("Retrieving employees from records.");
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("from Employee where isRemoved = false",
                                                         Employee.class);
            employeeRecord = query.list();
            transaction.commit();
        } catch (HibernateException e) { 
            logger.debug("Error while fetching employees info");
            throw new EmployeeException("Error while fetching employees info", e);
        }
        return employeeRecord;
    }
    
    @Override
    public Employee getEmployee(int searchableId) throws EmployeeException {
        Transaction transaction = null;   
        Employee employee = null;
        logger.debug("Retrieving the employee with ID : {}", searchableId);
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            employee = session.createQuery("from Employee where employeeId = :id"
                                            + " and isRemoved = false", Employee.class).setParameter("id", searchableId).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("Error at searching :{}", searchableId);
            throw new EmployeeException("Error at searching :" + searchableId, e);
        }
        return employee;
    }

    @Override
    public boolean checkEmployeeRecord() throws EmployeeException {
        boolean isEmpty = true;
        try {
            List<Employee> employeeRecord = getEmployees();
            for (Employee employee : employeeRecord) {
                if (!employee.getIsRemoved()) {
                    isEmpty = false;    
                }
            }
        } catch (HibernateException e) {
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
            logger.debug("Error at updating employee with name :{}", employee.getEmployeeName());
            throw new EmployeeException("Error at updating employee with name :" + employee.getEmployeeName(), e);
        }
        return true;                 
    }
}