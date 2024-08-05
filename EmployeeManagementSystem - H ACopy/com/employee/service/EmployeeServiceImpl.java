package com.employee.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employee.dao.EmployeeDao;
import com.employee.dao.EmployeeDaoImpl;
import com.model.Department;
import com.model.Employee;
import com.model.SalaryAccount;
import com.employee.service.EmployeeService;
import com.exceptions.EmployeeException;
import com.salaryaccount.service.SalaryAccountService;
import com.salaryaccount.service.SalaryAccountServiceImpl;

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
    SalaryAccountService salaryAccountService = new SalaryAccountServiceImpl();

    @Override
    public boolean addEmployee(String employeeName, LocalDate employeeDOB,
                            String contactNumber, int departmentId,
                            String accountNumber, String ifscCode) throws EmployeeException { 
        Department departmentName = departmentService
                                        .getDepartment(departmentId); 
        SalaryAccount salaryAccount = new SalaryAccount(accountNumber, ifscCode);
        salaryAccountService.addAccount(salaryAccount);
        Employee employee = new Employee(employeeName, contactNumber,
                                         departmentName, employeeDOB, salaryAccount);
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