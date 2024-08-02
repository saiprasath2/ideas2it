package com.project.controller;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.model.Project;
import com.project.service.ProjectService;
import com.project.service.ProjectServiceImpl;
import com.model.Employee;
import com.employee.service.OperationService;
import com.employee.service.OperationServiceImpl;
import com.exceptions.EmployeeException;
import com.util.InputValidator;

/**
 * <p>
 * ProjectController handles all the operations related to the project based on the user's request.
 * It provide endpoints for creating new project details and employee along with the other operations,includes
 * - retrieving the whole employee details along with their associated project.
 * - removing the student from all associated  project.
 * </p>
 */
public class ProjectController {
    ProjectService projectService = new ProjectServiceImpl();
    OperationService operationService = new OperationServiceImpl();
    Scanner scanner = new Scanner(System.in);
    InputValidator validator = new InputValidator();
    
    /**
     * Scans input choice from user.
     */
    public void assistProject() {
        ProjectController projectController = new ProjectController();
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
                    int wish = scanner.nextInt();
                    userWish = wish;
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
                        boolean isValidInput = validator.checkProjectInput(userProject);
                        try {
                             if (isValidInput) {
                                 projectService
                                    .addProject(new Project(userProject));
                                 validInput = true;
                                 System.out.println("Created Successfully!");
                             } else {
                                 System.out.println("Enter the format correctly!!");
                             }
                        } catch (EmployeeException e) {
                            System.out.println(e.getMessage()); 
                        }
                    }
                    break;  
            
                case 2:
                    if (projectService.getProjects().size() == 0) {
                        System.out.println("No projects to delete! ");
                    } else {
                        try {
                            int projectId = validator.checkProject();
                            boolean containInput = projectService
                                                   .checkDeletionStatus(projectId);                          
                            if (containInput) {
                                projectService.deleteProject(projectId);
                                System.out.println("Project deleted successfully!");
                            } else {
                                System.out.println("Cannot delete the linked data!");
                            }
                        } catch (EmployeeException e) {
                            System.out.println(e.getMessage()); 
                        }  
                    }
                    break;
 
                case 3:
                    if (projectService.getProjects().size() == 0) {
                        System.out.println("We have no projects yet.");
                    } else {
                        System.out.println("Avalaible Projects :");
                        displayProjects();
                    }
                    break;
        
                case 4:
                    if (projectService.getProjects().size() == 0) {
                        System.out.println("No Projects found");
                    } else {
                        try {
                            int projectId = validator.checkProject();
                            Project project = projectService
                                                  .getProject(projectId); 
                            String pattern1 = "| %-6s | %-15s | %-15s | %-15s |"
                                               + " %-30s | %-6s |\n";
                            System.out.format(pattern1, "Id", "Name", "Contact"
                                              , "Department", "Project", "Age");
                            if (project.getEmployees().size() != 0) {
                                for (Employee employee : project.getEmployees()) {
                                    System.out.format(pattern1, employee.getId()
                                                      , employee.getName()
                                                      , employee.getNumber() 
                                                      , employee.getDepartmentName()
                                                                .getDepartmentName()
                                                      , employee.getAllProjects()
                                                      , employee.getAge());
                                }
                            } else {
                                System.out.println("No employees found !");
                            }
                        } catch (EmployeeException e) {
                            System.out.println(e.getMessage()); 
                        }
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
        for (Map.Entry<Integer, Project> entry 
                 : projectService.getProjects().entrySet()) {
            System.out.println("ID : " + entry.getKey() 
                               + "Name : " + entry.getValue().getProjectName());
        }
    }
}