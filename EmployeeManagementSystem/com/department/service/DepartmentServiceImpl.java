package com.department.service;

import java.util.List;
import java.util.Map;

import com.department.dao.DepartmentDao;
import com.model.Department;
import com.department.service.DepartmentService;
import com.model.Employee;
import com.exceptions.EmployeeException;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the employees.
 * </p>
 */
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentDao departmentDao = new DepartmentDao();

    /**
     * passes the value for insertion.
     *
     * @param department  Department value to set department Name.
     */
    public void addDepartment(Department department) throws EmployeeException {
        departmentDao.insertDepartment(department);
    }
 
    /**
     * Calls retrieveEmployeeDepartments to get the department list.
     *
     * @return Map<> value to display department list.
     */
    public Map<Integer, Department> getDepartments() {
        return departmentDao.retrieveEmployeeDepartments();
    }

    /**
     * Calls the removeDepartment to delete the department.
     *
     * @param departmentId  int value to delete the department.
     */
    public void deleteDepartment(int departmentId) throws EmployeeException {
        departmentDao.removeDepartment(departmentId);
    }
    
    /**
     * Calls the getDepartment to d the department and returns the department.
     *
     * @param departmentId  int value to display the department.
     * @return Department value to display the department.
     */
    public Department getRequiredDepartment(int departmentId) throws EmployeeException {
        return departmentDao.getDepartment(departmentId);
    }

    /**
     * Calls the checkRemovingStatus to check a department can be deleted.
     *
     * @param departmentId  int value to check the deletionStatus.
     * @param checkingData  List<> to check department List.
     * @return boolean value to give link status of the department.
     */
    public boolean checkDeletionStatus(int departmentId,
                                       List<Employee> employee) throws EmployeeException  {
        
        Department department = departmentDao
                                   .getDepartment(departmentId);
        return departmentDao
                   .checkRemovingStatus(department, employee);       
    }           
}