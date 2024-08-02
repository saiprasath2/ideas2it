package com.department.service;

import java.util.List;
import java.util.Map;

import com.department.dao.DepartmentDao;
import com.department.dao.DepartmentDaoImpl;
import com.model.Department;
import com.department.service.DepartmentService;
import com.model.Employee;
import com.exceptions.EmployeeException;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the employees.
 * </p>
 *
 * author Saiprasath
 * version 1.0
 */
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentDao departmentDao = new DepartmentDaoImpl();
    
    @Override
    public void addDepartment(String department) throws EmployeeException {
        departmentDao.insertDepartment(department);
    }
 
    @Override
    public Map<Integer, Department> getDepartments() throws EmployeeException {
        return departmentDao.retrieveEmployeeDepartments();
    }

    @Override
    public void deleteDepartment(int departmentId) throws EmployeeException {
        departmentDao.removeDepartment(departmentId);
    }
    
    @Override
    public Department getDepartment(int departmentId) throws EmployeeException {
        return departmentDao.getDepartment(departmentId);
    }

    @Override
    public List<Employee> getEmployeesOfDepartment(int departmentId) throws EmployeeException {
        return departmentDao.getEmployeesOfDepartment(departmentId);
    }     
}