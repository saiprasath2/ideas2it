package com.department.service;

import java.util.Map;
import java.util.Set;

import com.department.dao.DepartmentDao;
import com.department.dao.DepartmentDaoImpl;
import com.department.service.DepartmentService;
import com.exceptions.EmployeeException;
import com.model.Department;
import com.model.Employee;


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
    public boolean addDepartment(String department) throws EmployeeException {
        return departmentDao.insertDepartment(department);
    }
 
    @Override
    public Map<Integer, Department> getDepartments() throws EmployeeException {
        return departmentDao.retrieveEmployeeDepartments();
    }

    @Override
    public boolean deleteDepartment(int departmentId) throws EmployeeException {
        return departmentDao.removeDepartment(departmentId);
    }
    
    @Override
    public Department getDepartment(int departmentId) throws EmployeeException {
        return departmentDao.getDepartment(departmentId);
    }

    @Override
    public Set<Employee> getEmployeesOfDepartment(int departmentId) throws EmployeeException {
        return departmentDao.getEmployeesOfDepartment(departmentId);
    }     
}