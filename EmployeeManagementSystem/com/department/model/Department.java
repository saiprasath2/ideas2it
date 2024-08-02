package com.department.model;

import java.util.ArrayList;
import java.util.List;

import com.employee.model.Employee;

/**
 * <p> Represents blueprint for the department datatype.
 * Contains details of employee such as Id, name.
 * Includes collections of employee alotted to a department.
 * </p>
 * @author Saiprasath
 * @version 1.0
 */
public class Department {
    private int departmentId;
    private String departmentName;
    public List<Employee> employees;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        employees = new ArrayList<>();
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName; 
    }
 
    /**
     * Returns employee list linked to the departments.
     *
     * @return List<> value to get employee list.
     */
    public List<Employee> getEmployees() {
        return employees;
    }
}