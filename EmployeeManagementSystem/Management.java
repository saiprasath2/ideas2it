import java.util.Scanner;
import java.util.InputMismatchException;

import com.department.controller.DepartmentController;
import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employee.controller.OperationController;
import com.project.controller.ProjectController;

/**
 * Decides the service type based on user input.
 * example : Employee, Department, Project.
 *
 * If there is no department, it will not allow calling Employee controller.
 */
public class Management {
    private Scanner scanner = new Scanner(System.in);
    private ProjectController projectController = new ProjectController();
    private OperationController operationController = new OperationController();
    private DepartmentController departmentController = new DepartmentController();
    private DepartmentService departmentService = new DepartmentServiceImpl();

    public void assistManagement() {
        boolean isExit = false;
        Management manage = new Management();
        while (!isExit) {
            System.out.println("Employee Management Services : ");
            System.out.println("1. Would you like to view Department services?");
            System.out.println("2. Would you like to view Employee services?");
            System.out.println("3. Would you like to view Project services?");
            System.out.println("4. Exit...");
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
                departmentController.assistDepartment();
                break;
 
            case 2:
                if (departmentService.getDepartments().size() == 0) {
                    System.out.println("We have no departments yet.\nAdd Department please..!");
                } else {
                    operationController.assistEmployee();
                }
                break;    

            case 3:
                projectController.assistProject();
                break;

            case 4:
                isExit = true;
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
            }
        } 
    } 

    public static void main(String[] args) {
        Management manage = new Management();
        manage.assistManagement();
    }
}