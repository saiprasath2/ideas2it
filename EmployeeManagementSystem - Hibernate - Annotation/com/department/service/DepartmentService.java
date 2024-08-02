package com.department.service;

import java.util.Map;
import java.util.Set;

import com.exceptions.EmployeeException;
import com.model.Department;
import com.model.Employee;

/** 
 * <p>
 * Passes value for insertion,deletion and retrieval on Department.
 * </p>
 *
 * @author   Saiprasath 
 * @version  1.0
 */
public interface DepartmentService {

    /**
     * <p>
     * passes the value for insertion into the collection.
     * </p>
     *
     * @param department  String value to set department Name.
     * @return boolean to indicate insertion status.
     * @throws EmployeeException when insertion is failed.
     */    
    public boolean addDepartment(String departmentName) throws EmployeeException;

    /**
     * Calls retrieveEmployeeDepartments to get the department list.
     *
     * @return Map<> value to display department list.
     * @throws EmployeeException when retrieval is failed.
     */
    public Map<Integer, Department> getDepartments() throws EmployeeException;

    /**
     * Calls the removeDepartment to delete the department.
     *
     * @param departmentId  int value to delete the department.
     * @returns boolean value to indicate deletion status.
     * @throws EmployeeException when deletion is failed.
     */
    public boolean deleteDepartment(int departmentId) throws EmployeeException;

    /**
     * Calls the getDepartment to fetch the department and returns the department.
     *
     * @param departmentId  int value to display the department.
     * @return Department value to display the department.
     * @throws EmployeeException when retrieval is failed.
     */
    public Department getDepartment(int departmentId) throws EmployeeException;

    /**
     * Retrieves employees under the department.
     *
     * @param departmentId  int value to display the department.
     * @return Set<> value to display the employees.
     * @throws EmployeeException when retrieval is failed.
     */
    public Set<Employee> getEmployeesOfDepartment(int departmentId) throws EmployeeException;
}