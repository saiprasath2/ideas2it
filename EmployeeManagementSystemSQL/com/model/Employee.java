package com.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

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
    public boolean isRemoved;
    private LocalDate dateOfBirth;
    public List<Project> project;
    public List<Employee> employees;

    public Employee(int employeeId, String employeeName, 
                           String contactNumber,  
                           Department department,
                           LocalDate dateOfBirth) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.contactNumber = contactNumber;
        this.department = department;
        this.dateOfBirth = dateOfBirth;  
        this.isRemoved = false; 
        employees = new ArrayList<>();
        project = new ArrayList<>();
    }
    
    public int getId() {
        return employeeId;
    }
    
    public void setId(int employeeId) {
        this.employeeId = employeeId;   
    }

    public String getName() {
        return employeeName;
    }

    public void setName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getNumber() {
        return contactNumber;
    }

    public void setNumber(String contactNumber) {
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
    /**
     * Returns list of projects assigned for an employee to getAllprojects.
     *
     * @return List<> value to get project list.
     */
    public List<Project> getProject() {
        return project;
    }

    public void setProjects(List<Project> project) {
        this.project = project;
    }

    /**
     * Returns list of employees assigned under departments.
     *
     * @return List<> value to get employee list.
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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