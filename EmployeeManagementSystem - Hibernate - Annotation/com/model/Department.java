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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.model.Employee;

/**
 * <p> 
 * Represents blueprint for the department datatype.
 * Contains details of employee such as Id, name.
 * Includes collections of employee alotted to a department.
 * </p>
 * 
 * @author Saiprasath
 * @version 1.0
 */
@Entity
@Table(name = "departments")
public class Department {
    
    @Id
    @Column(name = "department_id")
    @GeneratedValue
    private int departmentId;

    @Column(name = "department_name", unique = true)
    private String departmentName;

    @Column(name = "is_deleted")
    private boolean isRemoved;

    @OneToMany(mappedBy = "department",       
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER)
    public Set<Employee> employees;

    public Department() {}

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.isRemoved = false;
        employees = new HashSet<>();
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
        employees = new HashSet<>();
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName; 
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    } 
    /**
     * Returns employee list linked to the departments.
     *
     * @return Set<> value to get employee list.
     */
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}