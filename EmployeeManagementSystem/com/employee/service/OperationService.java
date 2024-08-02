package com.employee.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.model.Department;
import com.exceptions.EmployeeException;
import com.model.Employee;

public interface OperationService {
    public boolean addEmployee(String employeeName, LocalDate employeeDOB,
                               String contactNumber, 
                               int departmentId) throws EmployeeException ;

    public boolean checkEmptyList();

    public boolean removeEmployee(int removeId) throws EmployeeException;

    public List<Employee> displayEmployees();

    public Employee searchEmployee(int searchId) throws EmployeeException;

    public boolean updateEmployee(int updateId, int updateChoice, String newName,
                               LocalDate updatedDateOfBirth,
                               String newContactNumber,
                               int newDepartment) throws EmployeeException;

    public List<Employee> viewByDepartment(int choiceForView) throws EmployeeException;

    public Map<Integer, Department> getDepartments();   
}