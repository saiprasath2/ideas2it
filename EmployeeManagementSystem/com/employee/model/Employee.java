package com.employee.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.department.model.Department;
import com.project.model.Project;

/**
 * <p> Represents blueprint for the Employee datatype.
 * Contains details of employee such as Id, name, age.
 * Includes collections of project and department alotted to employees.
 * </p>
 * @author Saiprasath
 * @version 1.0
 */
public class Employee {
    private int employeeId;
    private String employeeName;
    private String contactNumber;
    private Department departmentName;
    private int departmentId;
    public boolean isRemoved;
    private LocalDate dateOfBirth;
    public List<Project> project;

    public Employee(int employeeId, String employeeName, 
                           String contactNumber, int departmentId, 
                           Department departmentName,
                           LocalDate dateOfBirth) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.contactNumber = contactNumber;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.dateOfBirth = dateOfBirth;  
        this.isRemoved = false;  
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Department getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(Department departmentName) {
        this.departmentName = departmentName;
    }
    
    public LocalDate getDateOfBirth() {
       return dateOfBirth; 
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * The method getIsRemoved will return the deletion status of employee.
     *
     * @return boolean value of current deletion status.
     */
    public boolean getIsRemoved() {
        return isRemoved;
    }

    /**
     * The method setIsRemoved will update the deletion status of the employee.
     */
    public void setIsRemoved() {
        this.isRemoved = true;
    }

    /**
     * Returns list of projects assigned for an employee to getAllprojects.
     *
     * @return List<> value to get project list.
     */
    public List<Project> getProject() {
        return project;
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