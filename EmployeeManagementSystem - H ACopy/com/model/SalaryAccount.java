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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.model.Employee;

/**
 * <p> Represents blueprint for the salary account datatype.
 * Contains details of Account such as Id, name.
 * Includes collections of employee alotted to a Account.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
@Entity
@Table(name = "salary_account")
public class SalaryAccount {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int accountId;

    @Column(name = "account_number", unique = true)
    private String accountName;

    @Column(name = "ifsc_code")
    private String IfscCode;

    public SalaryAccount() {}

    public SalaryAccount(int accountId, String accountName, String IfscCode) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.IfscCode = IfscCode;
        employees = new Employees();
    }

    public SalaryAccount(String accountName, String IFSCcode) {
        this.accountName = accountName;
        this.IfscCode = IfscCode;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public boolean getIfscCode() {
        return IfscCode;
    }

    public void setIfscCode(boolean IfscCode) {
        this.IfscCode = IfscCode;
    }
}