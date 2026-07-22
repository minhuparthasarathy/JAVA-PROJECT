import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

// Concept Used: Interface Implementation
// InternshipManager implements the InternshipOperations interface
public class InternshipManager implements InternshipOperations {

    // Concept Used: Collections (ArrayList)
    // ArrayList dynamically stores Internship objects
    private ArrayList<Internship> internships;

    // Concept Used: Object Creation
    private Scanner sc;

    // Counter to auto-generate unique internship IDs
    private int nextId;

    // Reference to UserManager for admin authentication check
    private UserManager userManager;

    // Concept Used: Constructor
    // Constructor accepts UserManager to integrate with Module 1 (User Management)
    public InternshipManager(UserManager userManager) {

        internships = new ArrayList<>();
        sc = new Scanner(System.in);
        nextId = 1;
        this.userManager = userManager;

    }

    // Concept Used: Method
    // Role-based access control - checks if logged-in user is Admin
    // Only Admin can access internship management features
    public boolean isAdminAccessible() {

        return userManager.isAdminAuthenticated();

    }

    // Concept Used: Method - Menu Driven
    // Displays Internship Management menu only if user is Admin
    public void showInternshipMenu() {

        // Concept Used: Conditional Statement
        // Check if user is authenticated as Admin, else deny access
        if (!isAdminAccessible()) {

            System.out.println("\nAccess Denied! Only Admin can manage internships.");
            System.out.println("Please login as Admin first.");
            return;

        }

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("    Internship Management Menu");
            System.out.println("=================================");
            System.out.println("1. Add Internship");
            System.out.println("2. Update Internship");
            System.out.println("3. Delete Internship");
            System.out.println("4. View All Internships");
            System.out.println("5. Back to Main Menu");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    // Concept Used: Method Calling
                    addInternshipFromInput();
                    break;

                case 2:
                    updateInternshipFromInput();
                    break;

                case 3:
                    deleteInternshipFromInput();
                    break;

                case 4:
                    // Concept Used: Method Calling
                    viewInternships();
                    break;

                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid Choice. Please try again.");

            }

        } while (choice != 5);

    }

    // Concept Used: Method
    // Takes user input and adds a new internship
    private void addInternshipFromInput() {

        System.out.println("\n----- Add New Internship -----");

        System.out.print("Company Name: ");
        String company = sc.nextLine();

        System.out.print("Role: ");
        String role = sc.nextLine();

        System.out.print("Required Skills (comma-separated): ");
        String skills = sc.nextLine();

        System.out.print("Required CGPA: ");
        double cgpa = sc.nextDouble();
        sc.nextLine();

        System.out.print("Location: ");
        String location = sc.nextLine();

        System.out.print("Stipend ($): ");
        double stipend = sc.nextDouble();
        sc.nextLine();

        System.out.print("Duration (months): ");
        int duration = sc.nextInt();
        sc.nextLine();

        // Concept Used: Object Creation
        // Create Internship object with auto-generated ID
        Internship internship = new Internship(nextId++, company, role,
                                                skills, cgpa, location,
                                                stipend, duration);

        // Concept Used: Method Calling
        addInternship(internship);

    }

    // Concept Used: Method - CRUD Implementation
    // Adds internship to the ArrayList collection
    @Override
    public void addInternship(Internship internship) {

        // Concept Used: Collections - ArrayList
        internships.add(internship);
        System.out.println("\nInternship Added Successfully!");

        // Concept Used: Method Calling
        saveInternshipsToFile();

    }

    // Takes user input to update an existing internship
    private void updateInternshipFromInput() {

        System.out.print("\nEnter Internship ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine();

        // Find internship by ID
        Internship existing = findInternshipById(id);

        if (existing == null) {

            System.out.println("Internship not found with ID: " + id);
            return;

        }

        System.out.println("\n----- Enter Updated Details -----");

        System.out.print("Company Name (" + existing.getCompany() + "): ");
        String company = sc.nextLine();
        if (company.isEmpty()) company = existing.getCompany();

        System.out.print("Role (" + existing.getRole() + "): ");
        String role = sc.nextLine();
        if (role.isEmpty()) role = existing.getRole();

        System.out.print("Required Skills (" + existing.getRequiredSkills() + "): ");
        String skills = sc.nextLine();
        if (skills.isEmpty()) skills = existing.getRequiredSkills();

        System.out.print("Required CGPA (" + existing.getRequiredCGPA() + "): ");
        String cgpaStr = sc.nextLine();
        double cgpa = cgpaStr.isEmpty() ? existing.getRequiredCGPA() : Double.parseDouble(cgpaStr);

        System.out.print("Location (" + existing.getLocation() + "): ");
        String location = sc.nextLine();
        if (location.isEmpty()) location = existing.getLocation();

        System.out.print("Stipend (" + existing.getStipend() + "): ");
        String stipendStr = sc.nextLine();
        double stipend = stipendStr.isEmpty() ? existing.getStipend() : Double.parseDouble(stipendStr);

        System.out.print("Duration in months (" + existing.getDuration() + "): ");
        String durStr = sc.nextLine();
        int duration = durStr.isEmpty() ? existing.getDuration() : Integer.parseInt(durStr);

        // Concept Used: Object Creation
        // Create updated internship object
        Internship updated = new Internship(id, company, role, skills,
                                            cgpa, location, stipend, duration);

        updateInternship(id, updated);

    }

    // Concept Used: Method - CRUD Implementation
    // Updates an existing internship in the collection
    @Override
    public void updateInternship(int id, Internship updatedInternship) {

        // Concept Used: Collections - ArrayList traversal
        for (int i = 0; i < internships.size(); i++) {

            Internship intern = internships.get(i);

            if (intern.getId() == id) {

                // Replace with updated object
                internships.set(i, updatedInternship);
                System.out.println("\nInternship Updated Successfully!");

                // Concept Used: Method Calling
                saveInternshipsToFile();
                return;

            }

        }

        System.out.println("Internship not found with ID: " + id);

    }

    // Takes user input to delete an internship
    private void deleteInternshipFromInput() {

        System.out.print("\nEnter Internship ID to Delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        deleteInternship(id);

    }

    // Concept Used: Method - CRUD Implementation
    // Deletes an internship from the collection by ID
    @Override
    public void deleteInternship(int id) {

        // Concept Used: Collections - ArrayList traversal with removal
        Internship toRemove = null;

        for (Internship intern : internships) {

            if (intern.getId() == id) {

                toRemove = intern;
                break;

            }

        }

        if (toRemove != null) {

            internships.remove(toRemove);
            System.out.println("\nInternship Deleted Successfully!");

            // Concept Used: Method Calling
            saveInternshipsToFile();

        } else {

            System.out.println("Internship not found with ID: " + id);

        }

    }

    // Concept Used: Method - CRUD Implementation
    // Displays all internships in the collection
    @Override
    public void viewInternships() {

        // Concept Used: Conditional Statement
        if (internships.isEmpty()) {

            System.out.println("\nNo Internships Available.");
            return;

        }

        System.out.println("\n===== All Internships =====");

        // Concept Used: Collections - for-each loop
        for (Internship intern : internships) {

            // Concept Used: Method Calling - toString()
            System.out.println(intern.toString());

        }

        System.out.println("\nTotal Internships: " + internships.size());

    }

    // Concept Used: Method - File I/O (Integration with Module 5)
    // Saves internship data to internships.txt file
    public void saveInternshipsToFile() {

        try {

            // Concept Used: File Handling
            FileWriter writer = new FileWriter("internships.txt");

            // Concept Used: String Handling - Building content
            for (Internship intern : internships) {

                writer.write(intern.toFileString() + "\n");

            }

            writer.close();

        } catch (IOException e) {

            System.out.println("Error saving internships to file: " + e.getMessage());

        }

    }

    // Concept Used: Method
    // Helper method to find an internship by its ID
    private Internship findInternshipById(int id) {

        // Concept Used: Collections - ArrayList traversal
        for (Internship intern : internships) {

            if (intern.getId() == id) {

                return intern;

            }

        }

        return null;

    }

    // Concept Used: Method
    // Returns the ArrayList of internships (for Module 3 - Recommendation Engine)
    public ArrayList<Internship> getAllInternships() {

        return internships;

    }

}

