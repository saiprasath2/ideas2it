package com.ideas2it.ems.employee.dao;

import java.util.List;

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
public interface EmployeeDao {
    /**
     * <p>
     * insertIntoDatabase will insert data's from user into the database.
     * </p>
     *
     * @param employee - Employee value to set employee ID.
     * @return Employee value to display status.
     * @throws EmployeeException when insertion is failed.
     */
    Employee insertIntoDatabase(Employee employee) throws EmployeeException;

    /** 
     * <p>
     * softRemover will remove the data, without removing it from database.
     * </p>
     *
     * @param removableId - int value to remove the employeeId.
     * @return Employee value to send message.
     * @throws EmployeeException when removal is failed.
     */
    Employee removeEmployee(int removableId) throws EmployeeException;

    /**
     * <p>
     * Returns the list of employees created.
     * </p>
     *
     * @return List<> value for all the employees in the database.
     * @throws EmployeeException when retrieving fails.
     */
    List<Employee> getEmployees() throws EmployeeException;

    /**
     * <p>
     * Searches and Returns the particular employee details.
     * </p>
     *
     * @param searchableId - int value to search the employee.
     * @return Employee value to display the employee.
     * @throws EmployeeException when searching fails.
     */
    Employee getEmployee(int searchableId) throws EmployeeException;

    /**
     * <p>   
     * Checks the presence of the employee in the list.
     * </p>
     *
     * @return boolean value to check the employee.
     * @throws EmployeeException when checking fails.
     */
    boolean checkEmployeeRecord() throws EmployeeException;

    /**
     * Updates the Employee details at Employee.
     *
     * @param employee  Employee value to update the employee.
     * @return boolean value to display the updated status.
     * @throws EmployeeException when updation fails.
     */
    boolean updateEmployee(Employee employee) throws EmployeeException;
}