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

    // Tracks if admin is currently authenticated
    private boolean isAdminLoggedIn = false;

    // Reference to RecommendationEngine for Module 3 integration
    private RecommendationEngine recommendationEngine;

    // Concept Used: Method
    // Sets the RecommendationEngine instance for generating recommendations
    public void setRecommendationEngine(RecommendationEngine engine) {

        this.recommendationEngine = engine;

    }

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
            isAdminLoggedIn = true;

        } else {

            System.out.println("Invalid Admin Credentials.");
        }
    }

    // Concept Used: Method
    // Non-Scanner authentication for Swing GUI integration
    // Returns authenticated Student or null
    public Student authenticateStudent(int studentId, String password) {

        for (int i = 0; i < count; i++) {

            if (students[i].getStudentId() == studentId &&
                students[i].getPassword().equals(password)) {

                return students[i];

            }

        }

        return null;

    }

    // Concept Used: Method
    // Non-Scanner admin authentication for Swing GUI integration
    // Returns true if credentials match
    public boolean authenticateAdmin(String username, String password) {

        if (username == null || username.isEmpty()) {
            return false;
        }
        if (password == null || password.isEmpty()) {
            return false;
        }
        if (username.equals(adminUsername) &&
            password.equals(adminPassword)) {

            isAdminLoggedIn = true;
            return true;

        }
        return false;

    }

    // Concept Used: Method
    // Returns whether an admin is currently authenticated
    public boolean isAdminAuthenticated() {

        return isAdminLoggedIn;

    }

    // Concept Used: Method
    // Logs out the admin by setting authentication flag to false
    public void adminLogout() {

        isAdminLoggedIn = false;
        System.out.println("Admin logged out successfully.");

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
            System.out.println("3.View Internship Recommendations");
            System.out.println("4.Logout");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    s.displayProfile();
                    break;

                case 2:
                    updateStudentProfile(s);
                    break;

                case 3:
                    // Concept Used: Method Calling
                    // View personalized internship recommendations (Module 3)
                    if (recommendationEngine != null) {

                        recommendationEngine.viewRecommendations(s);

                    } else {

                        System.out.println("Recommendation Engine not available.");

                    }
                    break;

            }

        } while (choice != 4);

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
