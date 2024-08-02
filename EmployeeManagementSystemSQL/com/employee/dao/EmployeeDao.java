package com.employee.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.exceptions.EmployeeException;
import com.model.Department;
import com.model.Employee;
import com.model.Project;

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
     * @param employeeName - String value to set employee Name.
     * @param contactNumber - String value to set contact number.
     * @param department - String value to set employee department.
     * @param dateOfBirth - LocalDate to calculate age of employee.
     * @return boolean value to display status.
     * @throws EmployeeException when insertion is failed.
     */   
    public boolean insertIntoDatabase(String employeeName,  
                                   LocalDate dateOfBirth, String contactNumber, 
                                   int departmentId,
                                   Department departmentName) throws EmployeeException;

    /** 
     * <p>
     * softRemover will remove the data, without removing it from database.
     * </p>
     *
     * @param removeId - int value to remove the employeeId.
     * @return boolean value to send message.
     * @throws EmployeeException when removal is failed.
     */
    public boolean softRemover(int removeId) throws EmployeeException;

    /**
     * <p>
     * Returns the list of employees created.
     * </p>
     *
     * @return List<> value for all the employees in the database.
     * @throw EmployeeException when retrieving fails.
     */ 
    public List<Employee> showEmployees() throws EmployeeException;

   /** 
    * <p>
    * This method return the project List by Employee Id 
    * </p>
    *   
    * @param employeeId to search for projects Employee Id
    * @return List<> projects by Employee Id
    * @throws EmployeeException when retrival of project fails.
    */
    public List<Project> getProjectsOfEmployee(int employeeId) throws EmployeeException;

    /**
     * <p>
     * Searches and Returns the particular employee details.
     * </p>
     *
     * @param searchId - int value to search the employee.
     * @return Employee value to display the employee.
     * @throws EmployeeException when searching fails.
     */
    public Employee showEmployee(int searchId) throws EmployeeException;

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