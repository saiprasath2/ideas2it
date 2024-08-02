package com.project.model;

import java.util.ArrayList;
import java.util.List;

import com.employee.model.Employee;

/**
 * <p> Represents blueprint for the project datatype.
 * Contains details of project such as Id, name.
 * Includes collections of employee alotted to a project.
 * </p>
 * @author Saiprasath
 * @version 1.0
 */
public class Project {
    private int projectId;
    private String projectName;
    private List<Employee> employees;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Project(String projectName) {
        this.projectName = projectName;
        employees = new ArrayList<>();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Returns the employees stored in project.
     *
     * @returns List<> value of employees under the project.
     */
    public List<Employee> getEmployees() {
        return employees;
    }
}