package com.ideas2it.ems.assister;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2it.ems.exceptions.EmployeeException;

/**
 * <p>
 * Creates connection for managing Hibernate SessionFactory.
 * </p>
 * 
 * @author Saiprasath
 * @author 1.0
 */
public class ConnectionAssister {
    private static SessionFactory sessionFactory = null;

    /**
     * <p>
     * Builds the SessionFactory.
     * </p>
     *
     * @return SessionFactory instance, when there is no 
     * instance it will return null.
     */
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch(Exception e) {
            System.err.println("Creation of Initial SessionFactory failed." + e);
            e.printStackTrace();
        }
    }  

    public static SessionFactory buildSessionFactory() {
        return sessionFactory;
    }

    /**
     * <p>
     * Method to retrieve the SessionFactory instance.
     * </p>
     *
     * @return SessionFactory instance
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * <p>
     * Closes the SessionFactory instance.
     * </p>
     */ 
    public static void CloseSessionFactory() {
        getSessionFactory().close();
    }   
}