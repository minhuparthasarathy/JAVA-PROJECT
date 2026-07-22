// Concept Used: Class
// Internship class stores internship opportunity details
public class Internship {

    // Concept Used: Encapsulation
    // Private data members protect data from direct access
    private int id;
    private String company;
    private String role;
    private String requiredSkills;  // Comma-separated skills
    private double requiredCGPA;
    private String location;
    private double stipend;
    private int duration; // Duration in months

    // Concept Used: Constructor
    // Parameterized constructor to initialize all attributes
    public Internship(int id, String company, String role,
                      String requiredSkills, double requiredCGPA,
                      String location, double stipend, int duration) {

        this.id = id;
        this.company = company;
        this.role = role;
        this.requiredSkills = requiredSkills;
        this.requiredCGPA = requiredCGPA;
        this.location = location;
        this.stipend = stipend;
        this.duration = duration;

    }

    // Concept Used: Methods - Getters and Setters
    // Getter and Setter methods provide controlled access to private fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public double getRequiredCGPA() {
        return requiredCGPA;
    }

    public void setRequiredCGPA(double requiredCGPA) {
        this.requiredCGPA = requiredCGPA;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getStipend() {
        return stipend;
    }

    public void setStipend(double stipend) {
        this.stipend = stipend;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Concept Used: toString() Method
    // Returns a formatted string representation of Internship details
    @Override
    public String toString() {

        return "\n----- Internship Details -----" +
               "\nInternship ID : " + id +
               "\nCompany       : " + company +
               "\nRole          : " + role +
               "\nRequired Skills : " + requiredSkills +
               "\nRequired CGPA : " + requiredCGPA +
               "\nLocation      : " + location +
               "\nStipend       : $" + stipend +
               "\nDuration      : " + duration + " months" +
               "\n------------------------------";

    }

    // Concept Used: Method
    // Converts internship object to a pipe-delimited file format string
    // This format is used for saving to internships.txt (Module 5 storage)
    public String toFileString() {

        return id + "|" + company + "|" + role + "|" +
               requiredSkills + "|" + requiredCGPA + "|" +
               location + "|" + stipend + "|" + duration;

    }

}

