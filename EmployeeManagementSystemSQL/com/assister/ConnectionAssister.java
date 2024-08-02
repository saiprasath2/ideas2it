package com.assister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.exceptions.EmployeeException;

/**
 * <p>
 * Creates connection with Database for storage and retrieval of data.
 * </p>
 * 
 * @author Saiprasath
 * @author 1.0
 */
public class ConnectionAssister {
    private static final String dataBaseDriver = "com.mysql.jdbc.Driver";
    private static final String dataBaseUrl = "jdbc:mysql://localhost:3306/employeemanagementsystem";
    private static final String userName = "root";
    private static final String passWord = "";    

    /**
     * <p>
     * Creates connection using given username, password and address with database.
     * </p>
     */
    public static Connection getConnection() {
       Connection connection = null;
       try {
            Class.forName(dataBaseDriver);
            connection = DriverManager.getConnection(dataBaseUrl, 
                                                     userName, passWord);
        } catch (SQLException e) {
            System.out.println("Connection Failed" + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Class could not be loaded");
        } catch (Exception e) {
            System.out.println("Other Errors :" + e.getMessage());
        } 
        return connection;
    }   

    /**
     * <p>
     * This method closes two result sets, Statement statement and connection.
     * If not closed displays the error message.
     * </p>
     */
    public static void closeAllResources(ResultSet firstResultSet, ResultSet secondResultSet,
                                         Statement statement, Connection connection) {
        try {
            if(null != firstResultSet) {
                firstResultSet.close();
            }
        } catch(Exception e) {
            System.out.println("Error occured while closing the firstResult set");
        }
        try {
            if(null != secondResultSet) {
                secondResultSet.close();
            }
        } catch(Exception e) {
            System.out.println("Error occured while closing the secondResult set");
        }
        try {
            if(null != statement) {
                statement.close();
            }
        } catch(Exception e) {
            System.out.println("Error occured while closing the prepared statement");
        }
        try {
            if(null != connection) {
                connection.close();
            }
        } catch(Exception e) {
            System.out.println("Error occured while closing the connection");
        }
    }  
  
    /**
     * <p>
     * This method closes the prepared statement and connection.
     * If not closed displays the error message.
     * </p>
     */
    public static void closeAllResources(PreparedStatement preparedStatement, Connection connection) {
        try {
            if(null != preparedStatement) {
                preparedStatement.close();
            }
        } catch(Exception e) {
            System.out.println("Error occured while closing the prepared statement");
        }
        try {
            if(null != connection) {
                connection.close();
            }
        } catch(Exception e) {
            System.out.println("Error occured while closing the connection");
        }
    }

    /**
     * <p>
     * This method closes the result set, prepared statement and connection.
     * If not closed displays the error message.
     * </p>
     */
    public static void closeAllResources(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if(null != resultSet) {
                resultSet.close();
            }
        } catch(Exception e) {
            System.out.println("Error occured while closing the result set");
        }
        try {
            if(null != statement) {
                statement.close();
            }
        } catch(Exception e) {
            System.out.println("Error occured while closing thestatement");
        }
        try {
            if(null != connection) {
                connection.close();
            }
        } catch(Exception e) {
            System.out.println("Error occured while closing the connection");
        }
    }       
}