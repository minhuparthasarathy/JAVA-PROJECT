// Concept Used: Object Creation
// Main class serves as entry point for the application
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Concept Used: Object Creation
        // Create UserManager object to manage operations
        UserManager manager = new UserManager();

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println("PM Internship Recommendation System");
            System.out.println("================================");
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("3. Admin Login");
            System.out.println("4. View Profile");
            System.out.println("5. Update Profile");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    // Concept Used: Method Calling
                    manager.registerStudent();
                    break;

                case 2:
                    // Concept Used: Method Calling
                    manager.studentLogin();
                    break;

                case 3:
                    // Concept Used: Method Calling
                    manager.adminLogin();
                    break;

                case 4:
                    // Concept Used: Method Calling
                    manager.viewProfile();
                    break;

                case 5:
                    // Concept Used: Method Calling
                    manager.updateProfile();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 6);

    }
}
