package com.department.service;

import java.util.List;
import java.util.Map;

import com.model.Department;
import com.exceptions.EmployeeException;
import com.model.Employee;

public interface DepartmentService {
    public void addDepartment(Department department) throws EmployeeException;

    public Map<Integer, Department> getDepartments();

    public void deleteDepartment(int departmentId) throws EmployeeException;

    public Department getRequiredDepartment(int departmentId) throws EmployeeException;

    public boolean checkDeletionStatus(int departmentId,
                                       List<Employee> employee) throws EmployeeException;
}