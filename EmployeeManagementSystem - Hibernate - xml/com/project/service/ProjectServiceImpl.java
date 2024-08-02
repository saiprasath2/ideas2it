package com.project.service;

import java.util.Map;
import java.util.Set;

import com.exceptions.EmployeeException;
import com.model.Employee;
import com.model.Project;
import com.project.dao.ProjectDao;
import com.project.dao.ProjectDaoImpl;
import com.project.service.ProjectService;

/**
 * <p>
 * Manages the information by the following operation like creating, retrieving 
 * and removing the projects.
 * </p>
 *
 * @author Saiprasath
 * version 1.0
 */
public class ProjectServiceImpl implements ProjectService {
    ProjectDao projectDao = new ProjectDaoImpl();
    
    @Override
    public boolean addProject(String project) throws EmployeeException {
        return projectDao.insertProject(project);
    }

    @Override
    public Map<Integer, Project> getProjects() throws EmployeeException {
        return projectDao.retrieveEmployeeProjects();
    }

    @Override
    public Project getProject(int id) throws EmployeeException {
        return projectDao.retrieveProject(id);        
    }
   
    @Override   
    public boolean deleteProject(int projectId) throws EmployeeException {
        return projectDao.removeProject(projectId);
    }

    @Override
    public void addEmployee(int id, Employee employee) throws EmployeeException {
        projectDao.insertEmployee(id, employee);
    } 

    @Override
    public Set<Employee> getEmployeesOfProjects(int projectId) throws EmployeeException {
        return projectDao.getEmployeesOfProjects(projectId);
    }       
}