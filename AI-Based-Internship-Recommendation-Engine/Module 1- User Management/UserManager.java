import java.util.Scanner;

// Concept Used: Interface Implementation
// UserManager implements the UserOperations interface
public class UserManager implements UserOperations {

    // Concept Used: Array
    // Array to store registered Student objects
    Student[] students = new Student[50];
    int count = 0;

    // Concept Used: Object Creation
    Scanner sc = new Scanner(System.in);

    // Fixed admin credentials
    private final String adminUsername = "admin";
    private final String adminPassword = "admin123";

    // Tracks currently logged-in student
    private Student currentStudent = null;

    // Concept Used: Method
    // Student Registration - accepts details and stores Student object
    public void registerStudent() {

        System.out.print("Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Degree: ");
        String degree = sc.nextLine();

        System.out.print("CGPA: ");
        double cgpa = sc.nextDouble();
        sc.nextLine();

        System.out.print("Skills: ");
        String skills = sc.nextLine();

        System.out.print("Interests: ");
        String interests = sc.nextLine();

        System.out.print("Preferred Location: ");
        String location = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        // Concept Used: Object Creation
        // Create Student object and store in array
        students[count++] = new Student(id, name, age, degree,
                cgpa, skills, interests, location, password);

        System.out.println("\nRegistration Successful!");
    }

    // Concept Used: Method
    // Student Login verifies credentials against registered students
    public void studentLogin() {

        System.out.print("Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        // Concept Used: Array
        // Traverse array to match credentials
        for (int i = 0; i < count; i++) {

            if (students[i].getStudentId() == id &&
                students[i].getPassword().equals(password)) {

                System.out.println("Login Successful.");
                currentStudent = students[i];
                studentMenu(currentStudent);
                return;
            }
        }

        System.out.println("Invalid Credentials.");
    }

    // Concept Used: Method
    // Admin Login verifies fixed admin credentials using String handling
    public void adminLogin() {

        System.out.print("Username: ");
        String user = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (user.equals(adminUsername) &&
            pass.equals(adminPassword)) {

            System.out.println("Admin Login Successful.");

        } else {

            System.out.println("Invalid Admin Credentials.");
        }
    }

    // Concept Used: Method
    // Displays profile of logged-in student
    public void viewProfile() {

        if (currentStudent != null) {
            currentStudent.displayProfile();
        } else {
            System.out.println("Please login as a student first.");
        }
    }

    // Concept Used: Method
    // Updates profile of logged-in student
    public void updateProfile() {

        if (currentStudent != null) {
            System.out.print("New Skills: ");
            currentStudent.setSkills(sc.nextLine());

            System.out.print("New Interests: ");
            currentStudent.setInterests(sc.nextLine());

            System.out.print("Preferred Location: ");
            currentStudent.setPreferredLocation(sc.nextLine());

            System.out.println("Profile Updated Successfully.");
        } else {
            System.out.println("Please login as a student first.");
        }
    }

    // Student Menu shown after successful login
    private void studentMenu(Student s) {

        int choice;

        do {

            System.out.println("\n1.View Profile");
            System.out.println("2.Update Profile");
            System.out.println("3.Logout");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    s.displayProfile();
                    break;

                case 2:
                    updateStudentProfile(s);
                    break;

            }

        } while (choice != 3);

        currentStudent = null;
    }

    // Update profile for specific student menu
    private void updateStudentProfile(Student s) {

        System.out.print("New Skills: ");
        s.setSkills(sc.nextLine());

        System.out.print("New Interests: ");
        s.setInterests(sc.nextLine());

        System.out.print("Preferred Location: ");
        s.setPreferredLocation(sc.nextLine());

        System.out.println("Profile Updated Successfully.");
    }

}
