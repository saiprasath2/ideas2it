package com.ideas2it.ems.department.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.HibernateException; 
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ems.assister.ConnectionAssister;
import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

/**
 * <p>
 * Designed to encapsulate all relevant information about a department and
 * to provide actions for accessing and modifying that information, as well as for
 * associating employees with the department.
 * </p>
 *
 * @author Saiprasath
 * @version 1.4
 */
public class DepartmentDaoImpl implements DepartmentDao { 
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Department insertDepartment(String departmentName) throws EmployeeException {
        Transaction transaction = null;   
        Integer id = 0;
        logger.debug("Creating new Department.");
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Department department = new Department(departmentName);
            id = (Integer) session.save(department);
            System.out.println(id);
            transaction.commit();
            if (id > 0) {
                return department;
            }
        } catch (HibernateException e) {
            logger.error("Department cannot be added with name : " 
                                        + departmentName);
            throw new EmployeeException("Department cannot be added with name : " 
                                        + departmentName, e);
        }
        return null;
    }  

    @Override
    public Department removeDepartment(int departmentId) throws EmployeeException {
        Transaction transaction = null;   
        int rowsAffected = 0;
        logger.debug("Removing given department" + departmentId);
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("update Department set isRemoved = true"
                                                 + " where departmentId = :id");
            query.setParameter("id", departmentId);
            rowsAffected = query.executeUpdate();
            transaction.commit();
            Department department = getDepartment(departmentId);
            if (rowsAffected == 1) {
                return department;
            }
        } catch (HibernateException e) {
            logger.error("Error at removing the department with id : {}", departmentId);
            throw new EmployeeException("Error at removing " + departmentId, e);
        }
        return null;
    }

    @Override
    public Map<Integer, Department> retrieveEmployeeDepartments() throws EmployeeException {
        Transaction transaction = null;   
        Map<Integer, Department> departments = new HashMap<>();
        logger.debug("Retrieving all the available departments.");
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Department> query = session.createQuery("from Department where isRemoved = false", Department.class);
            List<Department> departmentsFromRecord = query.list();
            for (Department department : departmentsFromRecord) {
                departments.put(department.getDepartmentId(), department);
            }
            transaction.commit();
            return departments;
        } catch (HibernateException e) {
            logger.error("Departments cannot be retrieved!");
            throw new EmployeeException("Departments cannot be retrieved!", e);
        } 
    } 

    @Override
    public Department getDepartment(int departmentId) throws EmployeeException {
        Transaction transaction = null;
        Department department = null;
        logger.debug("Retriving the given department with id : {}", departmentId);
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            department = session.get(Department.class, departmentId);
            transaction.commit();
            return department;
        } catch (HibernateException e) {
            logger.error("Error at searching the department with Id : {}", departmentId);
            throw new EmployeeException("Error at searching department with Id" + departmentId, e);
        }
    }

    @Override
    public Set<Employee> getEmployeesOfDepartment(int choiceForView) throws EmployeeException {
        Set<Employee> employees = new HashSet<>();
        Transaction transaction = null;   
        logger.debug("Retriving the employees of department with id : " + choiceForView);
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, choiceForView);
            if (department != null) {
                employees = department.getEmployees();
            } 
            transaction.commit();
            return employees; 
        } catch (Exception e) {
            logger.error("Error at searching employees at department with ID :{}", choiceForView);
            throw new EmployeeException("Error at searching employees at department with ID :" + choiceForView, e);
        }
    }
}