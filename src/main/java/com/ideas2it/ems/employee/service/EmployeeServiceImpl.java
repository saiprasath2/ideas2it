package com.ideas2it.ems.employee.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.department.service.DepartmentServiceImpl;
import com.ideas2it.ems.employee.dao.EmployeeDao;
import com.ideas2it.ems.employee.dao.EmployeeDaoImpl;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.SalaryAccount;
import com.ideas2it.ems.employee.service.EmployeeService;
import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.salaryaccount.service.SalaryAccountService;
import com.ideas2it.ems.salaryaccount.service.SalaryAccountServiceImpl;

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