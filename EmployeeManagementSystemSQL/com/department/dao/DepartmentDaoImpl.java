package com.department.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.assister.ConnectionAssister;
import com.department.dao.DepartmentDao;
import com.exceptions.EmployeeException;
import com.model.Department;
import com.model.Employee;

/**
 * <p>
 * Inserts, deletes and fetches data of the department.
 *
 * Handles datas of department to check connectivity of department to employee.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public class DepartmentDaoImpl implements DepartmentDao { 
    @Override
    public void insertDepartment(String departmentName) throws EmployeeException {
        Connection connection = null;
        PreparedStatement statement = null;    
        try {
            connection = ConnectionAssister.getConnection();
            String query = "insert into departments(`DepartmentName`) values (?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, departmentName);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Department inserted success !");
            } else {
                System.out.println("Department insertion failed !");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot create department : " + departmentName);
            throw new EmployeeException("Department cannot be added with name : " 
                                        + departmentName, e);
        } finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
    }  

    @Override
    public void removeDepartment(int departmentId) throws EmployeeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "update departments set is_Deleted = 1 where DepartmentId = " + departmentId;
            statement = connection.prepareStatement(query);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Department removed successfully !");
            } else {
                System.out.println("Department removal failed !");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot remove the department with Id : " + departmentId);
            throw new EmployeeException("Error at removing " + departmentId, e);
        } finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
    }

    @Override
    public Map<Integer, Department> retrieveEmployeeDepartments() throws EmployeeException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            Map<Integer, Department> departments = new HashMap<>();
            String query = "select * from departments where is_Deleted = 0";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Department department = new Department(resultSet.getInt("DepartmentId"),
                                                       resultSet.getString("DepartmentName"));
                departments.put(resultSet.getInt("DepartmentId"), department);
            }
            return departments;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot retrieve Departments !");
            throw new EmployeeException("Departments cannot be retrieved!", e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    } 

    @Override
    public Department getDepartment(int departmentId) throws EmployeeException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "select DepartmentId, DepartmentName from departments where" +
                           " DepartmentId = " + departmentId + " and is_Deleted = 0";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            Department department = new Department(resultSet.getInt("DepartmentId"),
                                                   resultSet.getString("DepartmentName"));
            department.setEmployees(getEmployeesOfDepartment(resultSet.getInt("departmentId")));
            return department;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Couldn't retrieve the department of Id : " + departmentId);
            throw new EmployeeException("Error at searching " + departmentId, e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    }

    @Override
    public List<Employee> getEmployeesOfDepartment(int choiceForView) throws EmployeeException {
        List<Employee> employees =new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "SELECT e.employee_id, e.employee_name, e.employee_dob,"
                       + " e.contact_number, d.DepartmentName, d.DepartmentId, e.is_Deleted"
                       + " FROM employees e LEFT JOIN departments d on e.DepartmentId "
                       + "= d.DepartmentId where d.DepartmentId = " + choiceForView + " and e.is_Deleted = 0";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Date dateOfBirth = resultSet.getDate("employee_dob");
                LocalDate birthDate = dateOfBirth.toLocalDate();
                Department department = new Department(resultSet.getInt("DepartmentId"),
                                                       resultSet.getString("DepartmentName"));           
                Employee employee = new Employee(resultSet.getInt("employee_id"),
                                                  resultSet.getString("employee_name"),
                                                  resultSet.getString("contact_number"),
                                                  department, birthDate); 
                employees.add(employee);
            } 
            return employees; 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at searching :" + choiceForView, e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    }
}