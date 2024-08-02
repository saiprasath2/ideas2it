package com.department.dao;

import java.util.List;
import java.util.Map;

import com.exceptions.EmployeeException;
import com.model.Department;
import com.model.Employee;


/**
 * <p>
 * Inserts, deletes and fetches data of the department.
 *
 * Handles datas of department to check connectivity of department to employee.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public interface DepartmentDao {
 
    /**
     * <p>
     * Insert datas from user to database with 
     * auto-incremented value.
     * </p>
     *
     * @param departmentName - String value to set department name.
     * @throws EmployeeException when issue arises during insertion of value.
     */ 
    public void insertDepartment(String departmentName) throws EmployeeException;

    /**
     * <p>
     * Removes the data from the database.
     * </p>
     *
     * @param departmentId - int value to remove the departmentId.
     * @throws EmployeeException when issue arises during removal of department.
     */
    public void removeDepartment(int departmentId) throws EmployeeException;

    /**
     * <p>
     * Return department names that are created.
     * </p>
     *
     * @return HashMap<> value to display the department list.
     * @throws EmployeeException when retrieval fails.
     */
    public Map<Integer, Department> retrieveEmployeeDepartments() throws EmployeeException;

    /**
     * <p> 
     * Returns specific department.
     * </p>
     *
     * @param departmentId - int value to get that department.
     * @return Department value to print the department name.
     * @throws EmployeeException when finding fails.
     */
    public Department getDepartment(int departmentId) throws EmployeeException;    

   /**
     * Displays the employers based on their department.
     *
     * @param choiceForView - int value to choose the department for display.
     * @return list<> value to give the employee list.
     * @throws EmployeeException when displaying fails.
     */
    public List<Employee> getEmployeesOfDepartment(int choiceForView) throws EmployeeException;    
}