package com.project.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;

import com.assister.ConnectionAssister;
import com.exceptions.EmployeeException;
import com.model.Project;
import com.model.Employee;
import com.project.dao.ProjectDao;
import org.hibernate.HibernateException; 
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 *<p>
 * Inserts, deletes, updates and fetches data of the project.
 *
 * Handles datas of project along with employee entity to display 
 * the employees project wise.
 * </p>
 *
 * @author Saiprasath
 * @version 1.0
 */
public class ProjectDaoImpl implements ProjectDao {    
    @Override
    public boolean insertProject(String projectName) throws EmployeeException {
        Transaction transaction = null;   
        Integer id = 0;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); 
            Project project = new Project(projectName);  
            id = (Integer) session.save(project);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Project cannot be added with name : " 
                                        + projectName, e);
        }
        if (id > 0) {
           return true;
        }
        return false;
    }  


    @Override
    public boolean removeProject(int projectId) throws EmployeeException {
        Transaction transaction = null;   
        int rowsAffected = 0;
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("update Project set isRemoved = true"
                                                 + " where projectId = :id").setParameter("id", projectId);
            rowsAffected = query.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at removing " + projectId, e);
        }
        return false;
    }
    
    @Override
    public Map<Integer, Project> retrieveEmployeeProjects() throws EmployeeException {
        Transaction transaction = null;   
        Map<Integer, Project> projects = new HashMap<>();
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Project> query = session.createQuery("from Project where isRemoved = false", Project.class);
            List<Project> projectsFromRecord = query.list();
            for (Project project : projectsFromRecord) {
                projects.put(project.getProjectId(), project);
            }
            transaction.commit();
            return projects;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Projects cannot be retrieved!", e);
        } 
    }     
    
    @Override
    public Project retrieveProject(int projectId) throws EmployeeException {  
        Transaction transaction = null;
        Project project = null;   
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            project = session.get(Project.class, projectId);
            transaction.commit();
            return project;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at searching " + projectId, e);
        }
    }

    @Override
    public Set<Employee> getEmployeesOfProjects(int projectId) throws EmployeeException {
        Set<Employee> employees = new HashSet<>();
        Transaction transaction = null;   
        try (Session session = ConnectionAssister.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Project project = session.get(Project.class, projectId);
            employees = project.getEmployees();
            if (project != null) {
                employees = project.getEmployees();
            } 
            transaction.commit(); 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at searching :" + projectId, e);
        }
        return employees;
    } 

    @Override
    public void insertEmployee(int id, Employee employee) throws EmployeeException {
        Transaction transaction = null;   
        int rowsAffected = 0;
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
            System.out.println(e.getMessage());
            throw new EmployeeException("Error at inserting Employee" + newEmployee.getEmployeeId()
                                         + "under Projects" 
                                         + retrieveProject(id).getProjectName(), e);
        }
    }
}