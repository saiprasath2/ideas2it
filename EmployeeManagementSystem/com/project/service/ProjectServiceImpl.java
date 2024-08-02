package com.project.service;

import java.util.Map;

import com.model.Employee;
import com.exceptions.EmployeeException;
import com.project.dao.ProjectDao;
import com.project.service.ProjectService;
import com.model.Project;


/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the projects.
 * </p>
 */
public class ProjectServiceImpl implements ProjectService {
    ProjectDao projectDao = new ProjectDao();
    
    /**
     * Passes project name to insertProject to add.
     *
     * @param project  Project value to add project.
     */ 
    public void addProject(Project project) throws EmployeeException {
        projectDao.insertProject(project);
    }

    /**
     * Retrieves all projects available.
     *
     * @returns Map<> value to display projects.
     */
    public Map<Integer, Project> getProjects() {
        return projectDao.retrieveEmployeeProjects();
    }

    /**
     * Retrieves required projects available.
     *
     * @returns Project value to display projectName.
     * @param id int value to get the project.
     */
    public Project getProject(int id) throws EmployeeException {
        return projectDao.retrieveProject(id);        
    }
   
    /**
     * Passes projectId to delete it.
     *
     * @param projectId int value to delete the given project.
     */ 
    public void deleteProject(int projectId) throws EmployeeException {
        projectDao.removeProject(projectId);
    }

    /**
     * Passes project id to check it's connection with employee
     *
     * @param projectid int value to check the project.
     * @return boolean value to decide project's deletio.
     */
    public boolean checkDeletionStatus(int projectId) throws EmployeeException {        
        Project project = projectDao
                              .retrieveProject(projectId);
        return projectDao
                   .checkRemovingStatus(project);       
    } 

    /**
     * Passes employee value to add under assigned project's list.
     *
     * @param id int value to get the project.
     * @param employee Employee value to add the employee.
     */
    public void addEmployee(int id, Employee employee) throws EmployeeException {
        projectDao.insertEmployee(id, employee);
    }        
}