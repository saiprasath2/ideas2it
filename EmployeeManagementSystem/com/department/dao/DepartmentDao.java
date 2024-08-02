package com.department.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Department;
import com.model.Employee;
import com.exceptions.EmployeeException;

/*
 * Inserts, deletes and fetches data of the department.
 *
 * Handles datas of department to check connectivity of department to employee.
 */
public class DepartmentDao {
    public static Map<Integer, Department> departmentAlotter = new HashMap<>();
    int departmentCount = 0;

    /**
     * insertDepartment will insert datas from user to database.
     *
     * @param department - Department value to set department name.
     */
    public void insertDepartment(Department department) throws EmployeeException {
        department.setDepartmentId(++departmentCount);
        try {
            departmentAlotter.put(departmentCount, department);
        } catch (Exception e) {
            throw new EmployeeException("Error at adding " + department, e);
        }
    }  

    /**
     * removeDepartment will remove the data from the database.
     *
     * @param departmentId - int value to remove the departmentId.
     */
    public void removeDepartment(int departmentId) throws EmployeeException {
        try {
            departmentAlotter.remove(departmentId);
        } catch (Exception e) {
            throw new EmployeeException("Error at removing " + departmentId, e);
        }
    }

    /**
     * checkRemovingStatus will check the deletion status of the department.
     *
     * @param checkingData - ArrayList<> value to compare the 
     * employee's department.
     *
     * @param department - Department value to check the departments.
     *
     * @return boolean value to display the deletion status.
     */
    public boolean checkRemovingStatus(Department department, 
                                    List<Employee> checkEmployee) {
        boolean containEntry = false;
        for (Employee employee : checkEmployee) {
            if (employee.getDepartmentName()
                   .equals(department)) {
                containEntry = true;
            }
        }
        return containEntry;
    } 

    /**
     * giveEmployeeDepartments will return department names.
     *
     * @return HashMap<> value to display the department list.
     */
    public Map<Integer, Department> retrieveEmployeeDepartments() {
        return departmentAlotter;
    } 

    /**
     *  giveDepartment will return specific department.
     *
     * @param departmentId - int value to get that department.
     *
     * @return Department value to print the department name.
     */
    public Department getDepartment(int departmentId) throws EmployeeException {
        try {
            return departmentAlotter.get(departmentId);
        } catch (Exception e) {
            throw new EmployeeException("Error at searching " + departmentId, e);
        }
    }
}