package com.ideas2it.ems.department.service;

import java.util.Map;
import java.util.Set;

import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

/** 
 * <p>
 * Passes value for insertion,deletion and retrieval on Department.
 * </p>
 *
 * @author   Saiprasath 
 * @version  1.4
 */
public interface DepartmentService {

    /**
     * <p>
     * passes the value for insertion into the collection.
     * </p>
     *
     * @param departmentName  String value to set department Name.
     * @return Department value to indicate insertion status.
     * @throws EmployeeException when insertion is failed.
     */    
    public Department addDepartment(String departmentName) throws EmployeeException;

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
     * @return boolean value to indicate deletion status.
     * @throws EmployeeException when deletion is failed.
     */
    public Department deleteDepartment(int departmentId) throws EmployeeException;

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