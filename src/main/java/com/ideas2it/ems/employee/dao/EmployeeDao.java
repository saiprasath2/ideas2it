package com.ideas2it.ems.employee.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
public interface EmployeeDao {
    /**
     * <p>
     * insertIntoDatabase will insert datas from user into the database.
     * </p>
     *
     * @param employee - Employee value to set employee ID.
     * @return boolean value to display status.
     * @throws EmployeeException when insertion is failed.
     */   
    public boolean insertIntoDatabase(Employee employee) throws EmployeeException;

    /** 
     * <p>
     * softRemover will remove the data, without removing it from database.
     * </p>
     *
     * @param removeId - int value to remove the employeeId.
     * @return boolean value to send message.
     * @throws EmployeeException when removal is failed.
     */
    public boolean removeEmployee(int removableId) throws EmployeeException;

    /**
     * <p>
     * Returns the list of employees created.
     * </p>
     *
     * @return List<> value for all the employees in the database.
     * @throw EmployeeException when retrieving fails.
     */ 
    public List<Employee> retrieveEmployees() throws EmployeeException;

    /**
     * <p>
     * Searches and Returns the particular employee details.
     * </p>
     *
     * @param searchId - int value to search the employee.
     * @return Employee value to display the employee.
     * @throws EmployeeException when searching fails.
     */
    public Employee retrieveEmployee(int searchableId) throws EmployeeException;

    /**
     * <p>   
     * Checks the presence of the employee in the list.
     * </p>
     *
     * @return boolean value to check the employee.
     * @throws EmployeeException when checking fails.
     */
    public boolean checkEmployeeRecord() throws EmployeeException;

    /**
     * Updates the Employee details at Employee.
     *
     * @param employee  Employee value to update the employee.
     * @return boolean value to display the updated status.
     * @throws EmployeeException when updation fails.
     */ 
    public boolean updateEmployee(Employee employee) throws EmployeeException;
}