package com.ideas2it.ems.project.controller;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.exceptions.EmployeeException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.project.service.ProjectService;
import com.ideas2it.ems.project.service.ProjectServiceImpl;
import com.ideas2it.ems.util.InputReader;

/**
 * <p>
 * ProjectController handles all the operations related to the project based on the user's request.
 * It provide endpoints for creating new project details and employee along with the other operations,includes
 * - retrieving the whole employee details along with their associated project.
 * - removing the employees from all associated  project.
 * </p>
 *
 * @author Saiprasath
 * version 1.4
 */
public class ProjectController {
    ProjectService projectService = new ProjectServiceImpl();
    private static final Logger logger = LogManager.getLogger();
    Scanner scanner = new Scanner(System.in);
    InputReader reader = new InputReader();
    
    /**
     * Scans input choice from user.
     */
    public void assistProject() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Project Management :");
            System.out.println("1. Would you like to add a project?");
            System.out.println("2. Would you like to Delete a project?");
            System.out.println("3. Would you like to Display all projects?");
            System.out.println("4. Would you like to Display the employees" 
                                + " project wise?");
            System.out.println("5. Back");
            int userWish = 0;
            boolean isValid = false;
            while (!isValid) {
                try {
                    System.out.println("Enter choice : ");
                    userWish = scanner.nextInt();
                    isValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Enter Integer format!!");
                    scanner.next();
                } 
            }
            switch (userWish) {
                case 1:
                    scanner.nextLine();
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.println("Give the Project to be added :");
                        String userProject = scanner.nextLine();
                        boolean isValidInput = reader.checkInput(userProject);
                        try {
                             if (isValidInput) {
                                 Project isInserted = projectService
                                    .addProject(userProject);
                                  validInput = true;
                                 if (null != isInserted) {
                                     logger.info("{}Created Successfully!", userProject);
                                 } else {
                                     logger.info("{}Failed to create", userProject);
                                 } 
                             } else {
                                 System.out.println("Enter the format correctly!!");
                             }
                        } catch (EmployeeException e) {
                            logger.error(e.getMessage()); 
                        }
                    }
                    break;  
            
                case 2:
                    try {
                        if (projectService.getProjects().isEmpty()) {
                            System.out.println("No projects to delete! ");
                        } else {
                            int projectId = reader.checkProject();
                            Project isDeleted = projectService.deleteProject(projectId);
                            if (null != isDeleted) {
                                logger.info("Id : {} deleted successfully!", projectId);
                            } else {
                                logger.info("Id : {} Deletion failed", projectId);
                            }
                        } 
                    } catch (EmployeeException e) {
                        logger.error(e.getMessage()); 
                    }  
                    break;
 
                case 3:
                    try {
                        if (projectService.getProjects().isEmpty()) {
                            System.out.println("We have no projects yet.");
                        } else {
                             System.out.println("Avalaible Projects :");
                             displayProjects();
                        } 
                    } catch (EmployeeException e) {
                        logger.error(e.getMessage()); 
                    }  
                    break;
        
                case 4:
                    try {
                        if (projectService.getProjects().isEmpty()) {
                            System.out.println("No Projects found");
                        } else {
                            int projectId = reader.checkProject();
                            Set<Employee> employees = projectService
                                                      .getEmployeesOfProjects(projectId); 
                            String pattern1 = "| %-6s | %-15s | %-15s | %-15s |"
                                               + " %-30s | %-6s |\n";
                            System.out.format(pattern1, "Id", "Name", "Contact"
                                              , "Department", "Project", "Age");
                            if (!employees.isEmpty()) {
                                for (Employee employee : employees) {
                                    System.out.format(pattern1, employee.getEmployeeId()
                                                      , employee.getEmployeeName()
                                                      , employee.getContactNumber() 
                                                      , employee.getDepartment()
                                                                .getDepartmentName()
                                                      , employee.getAllProjects()
                                                      , employee.getAge());
                                }
                            } else {
                                logger.info("No employees found " + projectId);
                            }
                        } 
                    } catch (EmployeeException e) {
                        logger.error(e.getMessage()); 
                    }
                    break;

                case 5:
                    isExit = true;
                    break;               
      
                default:
                    System.out.println("Enter valid choice !");
            }
        }
    }

    /**
     * Displays the project List inserted
     */
    public void displayProjects() {
        try {
            for (Map.Entry<Integer, Project> entry 
                 : projectService.getProjects().entrySet()) {
                System.out.println("ID : " + entry.getKey() 
                                   + "Name : " + entry.getValue().getProjectName());
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage()); 
        }
    }
}