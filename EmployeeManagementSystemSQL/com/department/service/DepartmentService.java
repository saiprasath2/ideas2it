package com.department.service;

import java.util.List;
import java.util.Map;

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
     * @throws EmployeeException when insertion is failed.
     */    
    public void addDepartment(String departmentName) throws EmployeeException;

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
     * @throws EmployeeException when deletion is failed.
     */
    public void deleteDepartment(int departmentId) throws EmployeeException;

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
     * @return List<> value to display the employees.
     * @throws EmployeeException when retrieval is failed.
     */
    public List<Employee> getEmployeesOfDepartment(int departmentId) throws EmployeeException;
}