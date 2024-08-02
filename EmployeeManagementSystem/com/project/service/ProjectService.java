package com.project.service;

import java.util.Map;

import com.model.Employee;
import com.exceptions.EmployeeException;
import com.model.Project;

public interface ProjectService {
    public void addProject(Project project) throws EmployeeException;

    public Map<Integer, Project> getProjects();

    public Project getProject(int id) throws EmployeeException;

    public void deleteProject(int projectId) throws EmployeeException;

    public boolean checkDeletionStatus(int projectId) throws EmployeeException; 

    public void addEmployee(int id, Employee employee) throws EmployeeException;
}