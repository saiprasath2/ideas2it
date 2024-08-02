package com.employee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.assister.ConnectionAssister;
import com.employee.dao.EmployeeDao;
import com.exceptions.EmployeeException;
import com.model.Department;
import com.model.Employee;
import com.model.Project;

/*
 * <p>
 * Inserts, deletes, updates and fetches data of the employee.
 *
 * Handles datas of employee along with department entity to display 
 * the employees department wise.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean insertIntoDatabase(String employeeName,  
                                   LocalDate dateOfBirth, String contactNumber, 
                                   int departmentId,
                                   Department departmentName) throws EmployeeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "insert into employees "
                           +"(`employee_name`, `employee_dob`, `contact_number`"
                           + ", `DepartmentId`) VALUES "
                           + "(?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, employeeName);
            statement.setDate(2, Date.valueOf(dateOfBirth));
            statement.setString(3, contactNumber);
            statement.setInt(4, departmentId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot add employee with Name : " + employeeName); 
            throw new EmployeeException("Error at adding " + employeeName, e);
        } finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
        return false;
    }
    
    @Override
    public boolean softRemover(int removeId) throws EmployeeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "update employees set is_Deleted = 1 where employee_id = " + removeId;
            statement = connection.prepareStatement(query);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Cannot remove Employee with ID : " + removeId);
            throw new EmployeeException("Error at removing :" + removeId, e);
        } finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
        return false;   
    }
    
    @Override
    public List<Employee> showEmployees() throws EmployeeException {
        List<Employee> employeeRecord = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "select e.employee_id, e.employee_name, e.employee_dob, e.contact_number, "
                           + "e.DepartmentId, d.DepartmentName from employees e LEFT JOIN departments d on"
                           + " e.DepartmentId = d.DepartmentId where e.is_Deleted = 0";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  
            while (resultSet.next()) {
                Date dateOfBirth = resultSet.getDate("employee_dob");
                LocalDate birthDate = dateOfBirth.toLocalDate();
                Department department = new Department(resultSet.getInt("DepartmentId"), resultSet.getString("DepartmentName"));           
                Employee employee = new Employee(resultSet.getInt("employee_id"),
                                                  resultSet.getString("employee_name"),
                                                  resultSet.getString("contact_number"),
                                                  department, birthDate);
                employee.setProjects(getProjectsOfEmployee(resultSet.getInt("employee_id")));
                employeeRecord.add(employee);             
            }
            return employeeRecord;
        } catch (Exception e) {  
            System.out.println(e.getMessage());        
            System.out.println("Cannot fetch employees data !");
            throw new EmployeeException("Error while fetching employees info", e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    }
    
    @Override
    public List<Project> getProjectsOfEmployee(int employeeId) throws EmployeeException {
        String query = "select p.project_id, p.ProjectName from association_employee_project a "
                       + "right join projects p on a.project_id = p.project_id"
                       + " where a.employee_id = " + employeeId;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
             connection = ConnectionAssister.getConnection();
             statement = connection.createStatement();
             resultSet = statement.executeQuery(query);
             List<Project> projects = new ArrayList<>();
             while(resultSet.next()) {
                 projects.add(new Project(resultSet.getInt("project_id"),
                                                  resultSet.getString("ProjectName")));
             }
             return projects;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot get projects of employee with Id : " + employeeId);
            throw new EmployeeException("Projects of employee failed of Id : " + employeeId, e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    }
    
    @Override
    public Employee showEmployee(int searchId) throws EmployeeException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "select e.employee_id, e.employee_name, e.employee_dob, e.contact_number, "
                           + "e.DepartmentId, d.DepartmentName, e.is_Deleted from employees e LEFT JOIN departments d on"
                           + " e.DepartmentId = d.DepartmentId where e.employee_id = " + searchId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            if (resultSet.getInt("is_Deleted") == 0) {
                Date dateOfBirth = resultSet.getDate("employee_dob");
                LocalDate birthDate = dateOfBirth.toLocalDate();
                Department department = new Department(resultSet.getInt("DepartmentId"), resultSet.getString("DepartmentName"));           
                Employee employee = new Employee(resultSet.getInt("employee_id"),
                                                  resultSet.getString("employee_name"),
                                                  resultSet.getString("contact_number"),
                                                  department, birthDate);
                employee.setProjects(getProjectsOfEmployee(resultSet.getInt("employee_id")));
                return employee;
            } else {
                return null;  
            }
        } catch (Exception e) {
            System.out.println("Cannot find employee with Id : " + searchId);
            throw new EmployeeException("Error at searching :" + searchId, e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    }

    @Override
    public boolean checkEmployeeRecord() throws EmployeeException {
        boolean isEmpty = true;
        try {
            List<Employee> employeeRecord = showEmployees();
            for (Employee employee : employeeRecord) {
                if (!employee.getIsRemoved()) {
                    isEmpty = false;    
                }
            }
        } catch (Exception e) {
            System.out.println("Cannot check the employee.");
            throw new EmployeeException("Error at checking.", e);
        }
        return isEmpty;
    }
   
    @Override
    public boolean updateEmployee(Employee employee) throws EmployeeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionAssister.getConnection();
	    String query = "update employees set employee_name = ?, employee_dob = ?,"
                            + " DepartmentId = ?, contact_number = ? where employee_id = " 
                            + employee.getId();
	    statement = connection.prepareStatement(query);
            statement.setString(1, employee.getName());
	    statement.setDate(2, Date.valueOf(employee.getDateOfBirth()));
	    statement.setInt(3, employee.getDepartment().getDepartmentId());
            statement.setString(4, employee.getNumber());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at updating :" + employee.getName(), e);
        } finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
        return false;                 
    }
}