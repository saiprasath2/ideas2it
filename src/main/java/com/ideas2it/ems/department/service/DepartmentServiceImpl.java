package com.ideas2it.ems.department.service;

import java.util.Map;
import java.util.Set;

import com.ideas2it.ems.department.dao.DepartmentDao;
import com.ideas2it.ems.department.dao.DepartmentDaoImpl;
import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;


/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the employees.
 * </p>
 *
 * author Saiprasath
 * version 1.4
 */
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentDao departmentDao = new DepartmentDaoImpl();
    
    @Override
    public Department addDepartment(String department) throws EmployeeException {
        return departmentDao.insertDepartment(department);
    }
 
    @Override
    public Map<Integer, Department> getDepartments() throws EmployeeException {
        return departmentDao.retrieveEmployeeDepartments();
    }

    @Override
    public Department deleteDepartment(int departmentId) throws EmployeeException {
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