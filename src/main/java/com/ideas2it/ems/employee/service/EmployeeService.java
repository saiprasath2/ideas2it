package com.ideas2it.ems.employee.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.Employee;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the employees.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public interface EmployeeService {

    /**
     * <p>
     * Passes the value from controller to OperationDao 
     * for insertion.
     * </p>
     *
     * @param employeeName  String value to set employee Name.
     * @param contactNumber  String value to set contact number.
     * @param departmentId  String value to set employee department.
     * @param employeeDOB LocalDate to calculate age of employee.
     * @return Employee value to display status of addition.
     * @throws EmployeeException when insertion fails.
     */
    public Employee addEmployee(String employeeName, LocalDate employeeDOB,
                               String contactNumber, 
                               int departmentId, String accountName,
                               String ifscCode) throws EmployeeException ;

    /**
     * <p> 
     * Calls the checkEmployeeList method to check the list.
     * </p>
     *
     * @return boolean value to display availability in list.
     * @throws EmployeeException when checking fails.
     */
    public boolean checkEmptyList() throws EmployeeException;

    /**
     * <p>
     * Passes the value from controller to softRemover 
     * at OperationDao for insertion.
     * </p>
     *
     * @param removableId  int value to remove the employeeId.
     * @return Employee value to send message.
     * @throws EmployeeException when removal fails.
     */ 
    public Employee removeEmployee(int removableId) throws EmployeeException;

    /**
     * <p>
     * Calls the showEmployees fuction at OperationDao
     * for displaying the list.
     * </p>
     *
     * @return List<> to print the list.
     * @throws EmployeeException when retrieval fails.
     */
    public List<Employee> retrieveEmployees() throws EmployeeException;

    /**
     * <p>
     * Calls the showEmployee fuction at OperationDao
     * for displaying the employee.
     * </p>
     *
     * @param searchableId  int value to search the employee.
     * @return employee value to display an employee.
     * @throws EmployeeException when search fails.
     */
    public Employee searchEmployee(int searchableId) throws EmployeeException;

    /**
     * <p>
     * Passes value to update the employee at database.
     *</p>
     *
     * @param employee Employee value to update the employee.
     * @return boolean value to indicate updation of an employee.
     * @throws EmployeeException when updation fails.
     */
    public boolean updateEmployee(Employee employee) throws EmployeeException;
}