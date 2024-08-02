package com.model;

import java.util.ArrayList;
import java.util.List;

import com.model.Employee;

/**
 * <p> Represents blueprint for the project datatype.
 * Contains details of project such as Id, name.
 * Includes collections of employee alotted to a project.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public class Project {
    private int projectId;
    private String projectName;
    private List<Employee> employees;

    public Project(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
        employees = new ArrayList<>();
    }

   public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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
  
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}