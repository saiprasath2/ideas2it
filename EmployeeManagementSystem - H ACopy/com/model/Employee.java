package com.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import javax.persistence.Table;

import com.model.Department;
import com.model.Project;
import com.model.SalaryAccount;

/**
 * <p> Represents blueprint for the Employee datatype.
 * Contains details of employee such as Id, name, age.
 * Includes collections of project and department alotted to employees.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
@Entity
@Table(name = "employees")
public class Employee {
     
    @Id
    @Column(name = "employee_id")
    @GeneratedValue
    private int employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "contact_number")
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "is_deleted")
    private boolean isRemoved;

    @Column(name = "employee_dob")
    private LocalDate dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "association_employee_project",
        joinColumns = {@JoinColumn(name = "employee_id")},
        inverseJoinColumns = {@JoinColumn(name = "project_id")}      
    )
    private Set<Project> project;

    @OneToOne(targetEntity = SalaryAccount.class,
              cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private SalaryAccount salaryAccount;

    public Employee() {}

    public Employee(String employeeName, 
                           String contactNumber,  
                           Department department,
                           LocalDate dateOfBirth,
                           SalaryAccount salaryAccount) {
        this.employeeName = employeeName;
        this.contactNumber = contactNumber;
        this.department = department;
        this.dateOfBirth = dateOfBirth;  
        this.isRemoved = false; 
        this.salaryAccount = salaryAccount;
        this.project = new HashSet<>();
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

    public SalaryAccount getSalaryAccount() {
        return salaryAccount;
    }

    public void setSalaryAccount(SalaryAccount salaryAccount) {
        this.salaryAccount = salaryAccount;
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