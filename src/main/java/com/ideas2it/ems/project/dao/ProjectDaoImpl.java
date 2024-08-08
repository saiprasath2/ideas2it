package com.ideas2it.ems.project.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.assister.ConnectionAssister;
import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * <p>
 * Inserts, deletes, updates and fetches data of the project.
 *
 * Handles data's of project along with employee entity to display
 * the employees project wise.
 * </p>
 *
 * @author Saiprasath
 * @version 1.4
 **/
public class ProjectDaoImpl implements ProjectDao {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Project insertProject(String projectName) throws EmployeeException {
        Transaction transaction = null;   
        Integer id = 0;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); 
            Project project = new Project(projectName);  
            id = (Integer) session.save(project);
            transaction.commit();
            if (id > 0) {
                return project;
            }
        } catch (HibernateException e) {
            logger.error("Project cannot be added with name : " 
                                        + projectName);
            throw new EmployeeException("Project cannot be added with name : " 
                                        + projectName, e);
        }
        return null;
    }  


    @Override
    public Project removeProject(int projectId) throws EmployeeException {
        Transaction transaction = null;   
        int rowsAffected = 0;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("update Project set isRemoved = true"
                                                 + " where projectId = :id").setParameter("id", projectId);
            rowsAffected = query.executeUpdate();
            Project project = getProject(projectId);
            if (rowsAffected == 1) {
                return project;
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error at removing with Id :{}", projectId);
            throw new EmployeeException("Error at removing with Id :" + projectId, e);
        }
        return null;
    }
    
    @Override
    public Map<Integer, Project> getEmployeeProjects() throws EmployeeException {
        Map<Integer, Project> projects = new HashMap<>();
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            Query<Project> query = session.createQuery("from Project where isRemoved = false", Project.class);
            List<Project> projectsFromRecord = query.list();
            for (Project project : projectsFromRecord) {
                projects.put(project.getProjectId(), project);
            }
            return projects;
        } catch (HibernateException e) {
            logger.error("Projects cannot be retrieved!");
            throw new EmployeeException("Projects cannot be retrieved!", e);
        } 
    }     
    
    @Override
    public Project getProject(int projectId) throws EmployeeException {
        Project project = null;   
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            project = session.get(Project.class, projectId);
            return project;
        } catch (HibernateException e) {
            logger.error("Error at searching project with Id : " + projectId);
            throw new EmployeeException("Error at searching project with Id : " + projectId, e);
        }
    }

    @Override
    public Set<Employee> getEmployeesOfProjects(int projectId) throws EmployeeException {
        Set<Employee> employees = new HashSet<>();
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            Project project = session.get(Project.class, projectId);
            employees = project.getEmployees();
            if (null != project) {
                employees = project.getEmployees();
            }
        } catch (HibernateException e) {
            logger.error("Error at searching project with ID:" + projectId);
            throw new EmployeeException("Error at searching project with Id :" + projectId, e);
        }
        return employees;
    } 

    @Override
    public void insertEmployee(int id, Employee employee) throws EmployeeException {
        Transaction transaction = null;
        Employee newEmployee = null;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            newEmployee = session.get(Employee.class, employee.getEmployeeId());
            Project project = session.get(Project.class, id);
            Set<Project> projects = newEmployee.getProject();
            Set<Employee> employees = project.getEmployees();
            projects.add(project);
            employees.add(newEmployee);
            session.saveOrUpdate(newEmployee);
            session.saveOrUpdate(project);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error at inserting Employee" + employee.getEmployeeId()
                                         + "under Projects" + getProject(id).getProjectName());
            throw new EmployeeException("Error at inserting Employee" + employee.getEmployeeId()
                                         + "under Projects" + getProject(id).getProjectName(), e);
        }
    }
}