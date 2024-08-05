package com.employee.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.model.Department;
import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employee.dao.EmployeeDao;
import com.employee.dao.EmployeeDaoImpl;
import com.model.Employee;
import com.employee.service.EmployeeService;
import com.exceptions.EmployeeException;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the employees.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao operationDao = new EmployeeDaoImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public boolean addEmployee(String employeeName, LocalDate employeeDOB,
                            String contactNumber, int departmentId) throws EmployeeException { 
        Department departmentName = departmentService
                                        .getDepartment(departmentId); 
        Employee employee = new Employee(employeeName, contactNumber,
                                         departmentName, employeeDOB);
        return operationDao.insertIntoDatabase(employee);
    }
    
    @Override
    public boolean checkEmptyList() throws EmployeeException {
        if (operationDao.checkEmployeeRecord()) {
            return true;
        }
        return false;
    }
    
    @Override   
    public boolean removeEmployee(int removableId) throws EmployeeException {
        return operationDao.removeEmployee(removableId);
    }

    @Override
    public List<Employee> displayEmployees() throws EmployeeException {
        List<Employee> serviceStorage = operationDao.retrieveEmployees();
        return serviceStorage;  
    } 

    @Override
    public Employee searchEmployee(int searchableId) throws EmployeeException {
        return operationDao.retrieveEmployee(searchableId);
    }
  
    @Override
    public boolean updateEmployee(Employee employee) throws EmployeeException {
        return operationDao.updateEmployee(employee);               
    }
   
    @Override
    public Map<Integer, Department> getDepartments() throws EmployeeException {
        return departmentService.getDepartments();
    }
}