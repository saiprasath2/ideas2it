package com.ideas2it.ems.util;

import java.time.LocalDate;
import java.time.Period;

/**
 * <p>
 * Contains the validation methods for the inputs and calculating age.
 * e.g. name,phone number.
 * </p>
 *
 * @author Saiprasath
 * @version 1.4
 */
public class Util {
    /**
     * </p>
     * Checks the validation of the name.
     * </p>
     *
     * @param checkableName - String value to check the name.
     * @return boolean value to decide validity of input.
     */
    public static boolean checkString(String checkableName){
        return checkableName.matches("[a-zA-Z\\s]+");
    }

    /**
     * <p>
     * Checks and returns the validation of the number.
     * </p>
     *
     * @param checkableNumber - String value to check the number.
     * @return boolean value to check validity.
     */
    public static boolean checkNumber(String checkableNumber){
        return checkableNumber.matches("\\d{10}");
    }

    /**
     * <p>
     * Checks the validation of the department.
     * </p>
     *
     * @param checkableDept - String value to check the department.
     * @return boolean value of input to decide the input format.
     */
    public static boolean checkDeptInput(String checkableDept){
        return checkableDept.matches("[a-zA-Z\\s]+");
    }

    /**
     * <p>
     * Checks the format of input project name/ account number/ ifsc Code.
     * </p>
     *
     * param checkableInput String value to check the format.
     * @return boolean value to validate input.
     */
    public static boolean checkInput(String checkableInput){
        return checkableInput.matches("[a-zA-Z0-9 ]+");
    }

    /**
     * <p>
     * Calculates the age of employee using date of birth.
     * </p>
     *
     * param date LocalDate value to calculate.
     * @return String value of age in year and months.
     */
    public static String calculateAge(LocalDate date) {
            return Period.between(date, LocalDate.now()).getYears()
                    + "y " + Period.between(date, LocalDate.now()).getMonths() + "m";
    }
}
