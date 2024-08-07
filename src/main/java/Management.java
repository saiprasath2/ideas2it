import java.util.Scanner;
import java.util.InputMismatchException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.department.controller.DepartmentController;
import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.department.service.DepartmentServiceImpl;
import com.ideas2it.ems.employee.controller.EmployeeController;
import com.ideas2it.ems.project.controller.ProjectController;
import com.ideas2it.ems.salaryaccount.controller.SalaryAccountController;
import com.ideas2it.ems.exceptions.EmployeeException;

/**
 * <p>
 * Decides the service type based on user input.
 * example : Employee, Department, Project.
 * </p>
 *
 * If there is no department, it will not allow calling Employee controller.
 *
 * @author Saiprasath
 * @version 1.4
 */
public class Management {
    private final Scanner scanner = new Scanner(System.in);
    private final ProjectController projectController = new ProjectController();
    private final EmployeeController operationController = new EmployeeController();
    private final DepartmentController departmentController = new DepartmentController();
    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private static final Logger logger = LogManager.getLogger();
    private final SalaryAccountController salaryAccountController = new SalaryAccountController();

    public void assistManagement() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Employee Management Services : ");
            System.out.println("1. Would you like to view Department services?");
            System.out.println("2. Would you like to view Employee services?");
            System.out.println("3. Would you like to view Project services?");
            System.out.println("4. Would you like to view Account services?");
            System.out.println("5. Exit...");
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
                departmentController.assistDepartment();
                break;
 
            case 2:
                try {
                    if (departmentService.getDepartments().isEmpty()) {
                        System.out.println("We have no departments yet.\nAdd Department please..!");
                    } else {
                        operationController.assistEmployee();
                    }
                } catch (EmployeeException e) {
                    logger.error(e.getMessage());
                }
                break;    

            case 3:
                projectController.assistProject();
                break;

            case 4:
                salaryAccountController.assistAccount();

            case 5:
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