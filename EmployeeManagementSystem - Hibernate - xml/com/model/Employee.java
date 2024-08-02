package com.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import com.model.Department;
import com.model.Project;

/**
 * <p> Represents blueprint for the Employee datatype.
 * Contains details of employee such as Id, name, age.
 * Includes collections of project and department alotted to employees.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public class Employee {
    private int employeeId;
    private String employeeName;
    private String contactNumber;
    private Department department;
    private boolean isRemoved;
    private LocalDate dateOfBirth;
    private Set<Project> project;

    public Employee() {}

    public Employee(String employeeName, 
                           String contactNumber,  
                           Department department,
                           LocalDate dateOfBirth) {
        this.employeeName = employeeName;
        this.contactNumber = contactNumber;
        this.department = department;
        this.dateOfBirth = dateOfBirth;  
        this.isRemoved = false; 
        project = new HashSet<>();
    }
    
    public int getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;   
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public LocalDate getDateOfBirth() {
       return dateOfBirth; 
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }
    /**
     * Returns set of projects assigned for an employee to getAllprojects.
     *
     * @return Set<> value to get project list.
     */
    public Set<Project> getProject() {
        return project;
    }

    public void setProject(Set<Project> project) {
        this.project = project;
    }

    /**
     * Returns all the project alloted to an employee using stringbuilder.
     * 
     * @return String value to print projects.
     */
    public String getAllProjects() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Project project : getProject()) {
            stringBuilder.append(project.getProjectName() + ",");
        }
        return stringBuilder.toString();
    }

    /**
     * Calculates the current age of the employee from
     * dateOfBirth value passed.
     *
     * @return String value of current age of the employee.
     */
    public String getAge() {
        if(dateOfBirth != null) {
            return Period.between(dateOfBirth, LocalDate.now()).getYears()
                + "y" + Period.between(dateOfBirth, LocalDate.now()).getMonths() + "m";
        }
        return "";
    }
}