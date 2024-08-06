package com.ideas2it.ems.project.service;

import java.util.Map;
import java.util.Set;

import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the projects.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public interface ProjectService {
    
    /**
     * <p>
     * Passes project name to insertProject to add.
     * </p>
     *
     * @param project  Project value to add project.
     * @return boolean value to indicate insertion status.
     * @throws EmployeeException when insertion is failed.
     */
    public boolean addProject(String project) throws EmployeeException;
 
    /**
     * <p>
     * Retrieves all projects available.
     * </p>
     *
     * @returns Map<> value to display projects.
     * @throws EmployeeException when retrieval is failed.
     */
    public Map<Integer, Project> getProjects() throws EmployeeException;

    /**
     * <p>
     * Retrieves required projects available.
     * </p>
     *
     * @returns Project value to display projectName.
     * @param id int value to get the project.
     * @throws EmployeeException when retrieval is failed.
     */
    public Project getProject(int id) throws EmployeeException;
    
    /**
     * <p>
     * Passes projectId to delete it.
     * </p>
     *
     * @param projectId int value to delete the given project.
     * @return boolean value to indicate deletion status
     * @throws EmployeeException when deletion is failed.
     */ 
    public boolean deleteProject(int projectId) throws EmployeeException;

    /**
     * <p>
     * Passes employee value to add under assigned project's list.
     * </p>
     *
     * @param id int value to get the project.
     * @param employee Employee value to add the employee.
     * @throws EmployeeException when addition is failed
     */
    public void addEmployee(int id, Employee employee) throws EmployeeException;

    /**
     * <p>
     * Returns employee record under the specific project.
     * </p>
     *
     * @param id int value to get the project.
     * @return Set<> value to list the employee.
     * @throws EmployeeException when addition is failed
     */
    public Set<Employee> getEmployeesOfProjects(int projectId) throws EmployeeException;
}