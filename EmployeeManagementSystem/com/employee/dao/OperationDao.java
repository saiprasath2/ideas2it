package com.employee.dao;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.model.Department;
import com.model.Employee;
import com.exceptions.EmployeeException;

/*
 * Inserts, deletes, updates and fetches data of the employee.
 *
 * Handles datas of employee along with department entity to display 
 * the employees department wise.
 */

public class OperationDao {
    public static List<Employee> employeeRecord = new ArrayList<>();
    int employeeCount = 0;

    /**
     * insertIntoDatabase will insert datas from user into the database.
     *
     * @param employeecount - int value to set employee ID.
     * @param employeeName - String value to set employee Name.
     * @param contactNumber - String value to set contact number.
     * @param department - String value to set employee department.
     * @param dateOfBirth - LocalDate to calculate age of employee.
     * @return boolean value to display status.
     */
    public boolean insertIntoDatabase(String employeeName,  
                                   LocalDate dateOfBirth, String contactNumber, 
                                   int departmentId,
                                   Department departmentName) throws EmployeeException {
        try {
            Employee employeeDetail = new Employee(++employeeCount, 
                                                                 employeeName,
                                                                 contactNumber,
                                                                 departmentId,
                                                                 departmentName,
                                                                 dateOfBirth);
            employeeRecord.add(employeeDetail);
            employeeDetail.getDepartmentName().getEmployees().add(employeeDetail);
            return true;
        } catch (Exception e) {
            throw new EmployeeException("Error at adding " + employeeName, e);
        }
    }
    
    /**
     * softRemover will remove the data, without removing it from database.
     *
     * @param removeId - int value to remove the employeeId.
     * @return boolean value to send message.
     */
    public boolean softRemover(int removeId) throws EmployeeException {
        boolean employeeFound = false;
        Iterator<Employee> employeeIterator = employeeRecord.iterator();
        try {
            while (employeeIterator.hasNext()) {
                Employee presentEmployee = employeeIterator.next();
                if (presentEmployee.getId() == removeId) {
                    employeeFound = true;
                    presentEmployee.setIsRemoved();
                    return true;
                }
            }
        } catch (Exception e) {
            throw new EmployeeException("Error at deleting :" + removeId, e);
        }
        return false;   
    }
    
    /**
     * @return List<> value for all the employees in the database.
     */
    public List<Employee> showEmployees() {
        return employeeRecord;
    }
    
    /**
     * showEmployee will display the particular employee details.
     *
     * @param searchId - int value to search the employee.
     * @return Employee value to display the employee.
     */
    public Employee showEmployee(int searchId) throws EmployeeException {
        Iterator<Employee> employeeIterator = employeeRecord.iterator();
        try {
            while (employeeIterator.hasNext()) {
                Employee currentEmployee = employeeIterator.next();
                if (currentEmployee.getId() == searchId) {
                    return currentEmployee;
                }          
            }
            return null;  
        } catch (Exception e) {
            throw new EmployeeException("Error at searching :" + searchId, e);
        }
    }

     /**
     * checkemployeeRecord will check the presence of the employee in the list.
     *
     * @return boolean value to check the employee.
     */
    public boolean checkEmployeeRecord() {
        boolean isEmpty = true;
         for (Employee employee : employeeRecord) {
            if(!employee.getIsRemoved()) {
                isEmpty = false;    
            }
        }
        return isEmpty;
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
     * @return boolean value to display the updated status.
     */ 
    public boolean updateEmployee(int updateId, int updateChoice, String newName,
                               LocalDate updatedDateOfBirth,
                               String newContactNumber,
                               Department departmentName) throws EmployeeException {
        Iterator<Employee> employeeIterator = employeeRecord
                                                             .iterator();
        try {
            while (employeeIterator.hasNext()) {
                Employee currentEmployee = employeeIterator.next();
                if (currentEmployee.getId() == updateId) {
                    switch (updateChoice) {
                    case 1:
                        currentEmployee.setName(newName);
                        break;

                    case 2:
                        currentEmployee.setDateOfBirth(updatedDateOfBirth);
                        break;

                    case 3:
                        currentEmployee.setNumber(newContactNumber);
                        break;

                    case 4: 
                        currentEmployee.setDepartmentName(departmentName);
                        break;

                    default:
                        System.out.println("\nFailed to Update!");                    
                    }
                    return true;
                } 
            }
        } catch (Exception e) {
            throw new EmployeeException("Error at updating :" + updateId, e);
        } 
         return false;                 
    }
    
    /**
     * displayByDepartment will display the employers based on their department.
     *
     * @param choiceForView - int value to choose the department for display.
     * return list<> value to give the employee list.
     */
    public List<Employee> displayByDepartment(int choiceForView,
                                              Map<Integer, Department> departmentRecord) throws EmployeeException {
        List<Employee> departments =new ArrayList<>();
        try {
            if (departmentRecord.containsKey(choiceForView)) {
                for (Employee employee : employeeRecord) {
                    if (employee.getDepartmentName().equals(departmentRecord
                                                 .get(choiceForView))) {
                     departments.add(employee);
                 } 
            } 
            return departments;   
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            throw new EmployeeException("Error at searching :" + choiceForView, e);
        }
    }
}