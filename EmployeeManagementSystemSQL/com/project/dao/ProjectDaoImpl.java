package com.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.assister.ConnectionAssister;
import com.exceptions.EmployeeException;
import com.model.Project;
import com.model.Employee;
import com.model.Department;
import com.project.dao.ProjectDao;

/*
 *<p>
 * Inserts, deletes, updates and fetches data of the project.
 *
 * Handles datas of project along with employee entity to display 
 * the employees project wise.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public class ProjectDaoImpl implements ProjectDao {    
    @Override
    public void insertProject(String projectName) throws EmployeeException {
        Connection connection = null;
        PreparedStatement statement = null;             
        try {
             connection = ConnectionAssister.getConnection();
             String query = "insert into projects (`ProjectName`) values (?)";      
             statement = connection.prepareStatement(query);
             statement.setString(1, projectName);
             int rowsAffected = statement.executeUpdate();
             if (rowsAffected == 1) {
                 System.out.println("inserted successfully");
             } else {
                 System.out.println("insertion failed");
             }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at adding " + projectName, e);
        }  finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
    }

    @Override
    public void removeProject(int projectId) throws EmployeeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "update projects set is_Deleted = 1 where project_id = " + projectId;
            statement = connection.prepareStatement(query);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Department removed successfully !");
            } else {
                System.out.println("Department removal failed !");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot remove the department with Id : " + projectId);
            throw new EmployeeException("Error at removing " + projectId, e);
        }  finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
    }
    
    @Override
    public Map<Integer, Project> retrieveEmployeeProjects() throws EmployeeException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "select * from projects where is_Deleted = 0";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Map<Integer, Project> projectAlotter = new HashMap<>();
            while (resultSet.next()) {
                Project project = new Project(resultSet.getInt("project_id"), 
                                             resultSet.getString("ProjectName"));
                projectAlotter.put(resultSet.getInt("project_id"), project);
            } 
            return projectAlotter;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at Retreving project " ,e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    }        
    
    @Override
    public Project retrieveProject(int projectId) throws EmployeeException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "select project_id, ProjectName from projects where "
                            + "project_id = " + projectId + " and is_Deleted = 0";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            Project project = new Project(resultSet.getInt("project_id"),
                                          resultSet.getString("ProjectName"));
            project.setEmployees(getEmployeesOfProjects(resultSet.getInt("project_id")));
            return project;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Couldn't retrieve the Project of Id : " + projectId);
            throw new EmployeeException("Error at searching " + projectId, e);
        } finally {
            ConnectionAssister.closeAllResources(resultSet, statement, connection);
        }
    }

    @Override
    public List<Employee> getEmployeesOfProjects(int projectId) throws EmployeeException {
        List<Employee> employeeRecords = new ArrayList<>();        
        Connection connection = null;
        Statement statement = null;
        ResultSet employeeResultSet = null;
        ResultSet projectResultSet = null;
        try {
            connection = ConnectionAssister.getConnection();
            String employeeQuery = "select * from employees e inner join association_employee_project a "
                           + " on e.employee_id = a.employee_id left join departments d on e.DepartmentId ="
                           + " d.DepartmentId where a.project_id = " + projectId;
            String projectQuery = "select ProjectName from projects where project_id = " + projectId ;
            statement = connection.createStatement();
            employeeResultSet = statement.executeQuery(employeeQuery);
            statement = connection.createStatement();
            projectResultSet = statement.executeQuery(projectQuery);
            projectResultSet.next();
            String projectName = projectResultSet.getString("ProjectName");
            while(employeeResultSet.next()) {
                Date dateOfBirth = employeeResultSet.getDate("employee_dob");
                LocalDate birthDate = dateOfBirth.toLocalDate();
                Department department = new Department(employeeResultSet.getInt("DepartmentId"), 
                                                       employeeResultSet.getString("DepartmentName"));
                Employee employee = new Employee(employeeResultSet.getInt("employee_id"),
                                                  employeeResultSet.getString("employee_name"),
                                                  employeeResultSet.getString("contact_number"),
                                                  department, birthDate);
		employeeRecords.add(employee);
                employee.setProjects(new ArrayList<>(Arrays.asList(new Project(projectId, projectName))));
            }
            return employeeRecords;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionAssister.closeAllResources(employeeResultSet, projectResultSet, statement, connection);
        }
        return new ArrayList<>();
    }    

    @Override
    public void insertEmployee(int id, Employee employee) throws EmployeeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionAssister.getConnection();
            String query = "insert into association_employee_project values (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, employee.getId());
            statement.setInt(2, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {
                System.out.println("Employee inserted under Projects");
            } else {
                System.out.println("Creation of association failed.");
            }
        } catch (Exception e) {
            System.out.println("Failed to add Employee with Id : " + employee.getId()
                                + "under Certificate Id : " + id);
            throw new EmployeeException("Error at inserting Employee" + employee.getId()
                                         + "under Projects" 
                                         + retrieveProject(id).getProjectName(), e);
        }  finally {
            ConnectionAssister.closeAllResources(statement, connection);
        }
    }
}