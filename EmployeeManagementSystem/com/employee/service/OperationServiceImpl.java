package com.employee.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.model.Department;
import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employee.dao.OperationDao;
import com.model.Employee;
import com.employee.service.OperationService;
import com.exceptions.EmployeeException;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the employees.
 * </p>
 */
public class OperationServiceImpl implements OperationService {
    OperationDao operationDao = new OperationDao();
    DepartmentService departmentService = new DepartmentServiceImpl();
    public List<Employee> serviceStorage = new ArrayList<>();

    /**
     * Passes the value from controller to OperationDao 
     * for insertion.
     *
     * @param employeeName  String value to set employee Name.
     * @param contactNumber  String value to set contact number.
     * @param department  String value to set employee department.
     * @param dateOfBirth  LocalDate to calculate age of employee.
     * @return boolean value to display status of addition.
     */
    public boolean addEmployee(String employeeName, LocalDate employeeDOB,
                            String contactNumber, int departmentId) throws EmployeeException { 
        Department departmentName = departmentService
                                        .getRequiredDepartment(departmentId); 
        return operationDao.insertIntoDatabase(employeeName, employeeDOB,
                                    contactNumber, departmentId, departmentName);
    }

     /**
     * Calls the checkEmployeeList method to check the list.
     *
     * @return boolean value to display availability in list.
     */
    public boolean checkEmptyList() {
        if (operationDao.checkEmployeeRecord()) {
            return true;
        }
        return false;
    }
    
    /**
     * Passes the value from controller to softRemover 
     * at OperationDao for insertion.
     *
     * @param removeId  int value to remove the employeeId.
     * @return boolean value to send message.
     */    
    public boolean removeEmployee(int removeId) throws EmployeeException {
        return operationDao.softRemover(removeId);
    }

    /**
     * Calls the showEmployees fuction at OperationDao
     * for displaying the list.
     *
     * @return List<> to print the list.
     */
    public List<Employee> displayEmployees() {
        List<Employee> serviceStorage = operationDao.showEmployees();
        return serviceStorage;  
    } 

    /**
     * searchEmployee calls the showEmployee fuction at OperationDao
     * for displaying the employee.
     *
     * @param searchId  int value to search the employee.
     * @return employee value to display an employee.
     */
    public Employee searchEmployee(int searchId) throws EmployeeException {
        return operationDao.showEmployee(searchId);
    }
    
    /**
     * updateEmployee will update the Employee details at Employee
     *
     * @param updateId  int value to update the employee.
     * @param updateChoice  int value for updation choice.
     * @param newName  String value for employee name.
     * @param updatedDateOfBirth  LocalDate value for age calculation.
     * @param newContactNumber  String value for contact Number.
     * @param departmentName  Department value for department of employee.
     * @return boolean value to return updation status.
     */ 
    public boolean updateEmployee(int updateId, int updateChoice, String newName,
                               LocalDate updatedDateOfBirth,
                               String newContactNumber,
                               int newDepartment) throws EmployeeException {
        Department departmentName = departmentService
                                        .getRequiredDepartment(newDepartment);
        return operationDao.updateEmployee(updateId, updateChoice, newName,
                                           updatedDateOfBirth, newContactNumber,
                                           departmentName);               
    }
   
    /**
     * Calls the displayByDepartment fuction at OperationDao
     * for displaying the list by department.
     *
     * @param choiceForView - int value to choose the department for display.
     * @return List<> value to display employees by department list.
     */
    public List<Employee> viewByDepartment(int choiceForView) throws EmployeeException {
        Map<Integer, Department> departmentRecord = getDepartments();
        List<Employee> serviceStorage = operationDao.
                                                 displayByDepartment(choiceForView, departmentRecord);
        return serviceStorage;
    }

    /**
     * Call retrieveDepartment to get the department list.
     *
     * @return Map<> value to display department list.
     */
    public Map<Integer, Department> getDepartments() {
        return departmentService.getDepartments();
    }
}