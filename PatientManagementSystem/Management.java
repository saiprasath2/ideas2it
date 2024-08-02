import java.util.Scanner;
import java.util.InputMismatchException;

import com.diagnosis.controller.DiagnosisController;
import com.diagnosis.service.DiagnosisService;
import com.diagnosis.service.DiagnosisServiceImpl;
import com.exceptions.PatientException;
import com.patient.controller.PatientController;

/**
 * Decides the service type based on user input.
 * example : Diagnosis, Patient.
 *
 * If there is no diagnosis, it will not allow calling patient controller.
 */
public class Management {
    private Scanner scanner = new Scanner(System.in);
    private PatientController patientController = new PatientController();
    private DiagnosisController diagnosisController = new DiagnosisController();
    private DiagnosisService diagnosisService = new DiagnosisServiceImpl();

    public void assistManagement() {
        boolean isExit = false;
        Management manage = new Management();
        while (!isExit) {
            System.out.println("Employee Management Services : ");
            System.out.println("1. Would you like to view Patient services?");
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
                diagnosisController.assistDiagnosis();
                break;
 
            case 2:
                try {
                    if (diagnosisService.getAllDiagnosis().size() == 0) {
                        System.out.println("We have no diagnosis yet.\nAdd Diagnois please..!");
                    } else {
                        patientController.assistPatient();
                    }
                } catch (PatientException e) {
                    System.out.println(e.getMessage()); 
                }
                break;    

            case 3:
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