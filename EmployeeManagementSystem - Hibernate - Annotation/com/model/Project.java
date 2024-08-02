package com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @Column(name = "project_id") 
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int projectId;

    @Column(name = "project_name", unique = true)
    private String projectName;

    @Column(name = "is_deleted")
    private boolean isRemoved;

    @ManyToMany(mappedBy = "project",
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER)
    private Set<Employee> employees;

    public Project() {}

    public Project(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.isRemoved = false;
        employees = new HashSet<>();
    }

    public Project(String projectName) {
        this.projectName = projectName;
        employees = new HashSet<>();
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

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }
    /**
     * Returns the employees stored in project.
     *
     * @returns Set<> value of employees under the project.
     */
    public Set<Employee> getEmployees() {
        return employees;
    }
  
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}