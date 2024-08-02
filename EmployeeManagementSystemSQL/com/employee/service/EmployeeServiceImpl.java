package com.employee.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employee.service.EmployeeService;
import com.employee.dao.EmployeeDao;
import com.employee.dao.EmployeeDaoImpl;
import com.exceptions.EmployeeException;
import com.model.Department;
import com.model.Employee;

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
    public List<Employee> serviceStorage = new ArrayList<>();

    @Override
    public boolean addEmployee(String employeeName, LocalDate employeeDOB,
                            String contactNumber, int departmentId) throws EmployeeException { 
        Department departmentName = departmentService
                                        .getDepartment(departmentId); 
        return operationDao.insertIntoDatabase(employeeName, employeeDOB,
                                    contactNumber, departmentId, departmentName);
    }
    
    @Override
    public boolean checkEmptyList() throws EmployeeException {
        if (operationDao.checkEmployeeRecord()) {
            return true;
        }
        return false;
    }
    
    @Override   
    public boolean removeEmployee(int removeId) throws EmployeeException {
        return operationDao.softRemover(removeId);
    }

    @Override
    public List<Employee> displayEmployees() throws EmployeeException {
        List<Employee> serviceStorage = operationDao.showEmployees();
        return serviceStorage;  
    } 

    @Override
    public Employee searchEmployee(int searchId) throws EmployeeException {
        return operationDao.showEmployee(searchId);
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