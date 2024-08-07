package com.ideas2it.ems.project.dao;

import java.util.Map;
import java.util.Set;

import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.Employee;

/**
 * <p>
 * Inserts, deletes, updates and fetches data of the project.
 *
 * Handles data's of project along with employee entity to display
 * the employees project wise.
 * </p>
 *
 * @author Saiprasath
 * @version 1.4
 */
public interface ProjectDao {
    
    /**
     * <p>
     * creates the project and inserts it to the storage with auto-incremented Id.
     * </p>
     *
     * @param projectName
     *         For which project could not be null.
     * @return Project value to indicate insertion status.
     * @throws EmployeeException when there is no proper details provided.
     */
    Project insertProject(String projectName) throws EmployeeException;

    /**
     * <p>
     * Removes the project database by changing the boolean value.
     * </p>
     *
     * @param projectId - int value to delete the project.
     * @return Project value to indicate removal status.
     * @throws EmployeeException when there is no proper details provided.
     */
    Project removeProject(int projectId) throws EmployeeException;

    /**
     * <p>
     * Retrieves the projects available.
     * </p>
     *
     * @return Map<> value of available projects.
     * @throws EmployeeException when there is no proper details provided. 
     */
    Map<Integer, Project> getEmployeeProjects() throws EmployeeException;

    /**
     * <p>
     * Retrieves required project by the user.
     * </p>
     *
     * @param projectId - int value to fetch the project.
     * @return Project value to display the project.
     * @throws EmployeeException when there is no proper details provided.
     */
    Project getProject(int projectId) throws EmployeeException;
   
    /**
     * <p>
     * Retrieves all employees assigned under the project.
     * </p>
     *
     * @param projectId - int value to fetch the project.
     * @return Set<> value to display the employee.
     * @throws EmployeeException when there is no proper details provided.
     */
    Set<Employee> getEmployeesOfProjects(int projectId) throws EmployeeException;
  
    /**
     * <p>
     * Inserts employee name into assigned project's list.
     * </p>
     *
     * @param id - int value to get the project.
     * @param employee - Employee value to insert the employee.
     * @throws EmployeeException When there is no proper insertion of employee
     *                           into the projects.
     */
    void insertEmployee(int id, Employee employee) throws EmployeeException;
}