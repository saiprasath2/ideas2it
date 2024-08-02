package com.project.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Project;
import com.model.Employee;
import com.exceptions.EmployeeException;

/*
 * Inserts, deletes, updates and fetches data of the project.
 *
 * Handles datas of project along with employee entity to display 
 * the employees project wise.
 */

public class ProjectDao {
    public static Map<Integer, Project> projectAlotter = new HashMap<>();
    int projectCount = 0;
    
    /**
     * Inserts project into the storage map.
     *
     * @param project - Project value to add project name.
     */
    public void insertProject(Project project) throws EmployeeException {
        project.setProjectId(++projectCount);
        try {      
            projectAlotter.put(projectCount, project);
        } catch (Exception e) {
            throw new EmployeeException("Error at adding " + project, e);
        }
    }

    /**
     * Removes project from the Map.
     *
     * @param projectId - int value to delete the project.
     */
    public void removeProject(int projectId) throws EmployeeException {
        try {
            projectAlotter.remove(projectId);
        } catch (Exception e) {
            throw new EmployeeException("Error at removing " + projectId, e);
        }
    }
    
    /**
     * Checks the connection project with employees to delete it.
     *
     * @param project  Project value to check the project.
     * @return boolean value to delete the project.
     */
    public boolean checkRemovingStatus(Project project) {
        return project.getEmployees().size() == 0;
    } 

    /**
     * @return Map value of available projects.
     */
    public Map<Integer, Project> retrieveEmployeeProjects() {
        return projectAlotter;
    }        
    
    /**
     * Returns required project by the user.
     *
     * @param projectId - int value to fetch the project.
     * @return Project value to display the project.
     */
    public Project retrieveProject(int projectId) throws EmployeeException {
        try {
            return projectAlotter.get(projectId);
        } catch (Exception e) {
            throw new EmployeeException("Error at searching " + projectId, e);
        }
    }

    /**
     * Inserts employee name into assigned project's list.
     *
     * @param id - int value to get the project.
     * @param employee - Employee value to insert the employee.
     */
    public void insertEmployee(int id, Employee employee) throws EmployeeException {
        try {
            Project project = retrieveProject(id);
            project.getEmployees().add(employee);
            employee.getProject().add(project);
        } catch (Exception e) {
            throw new EmployeeException("Error at inserting " + id, e);
        }
    }
}