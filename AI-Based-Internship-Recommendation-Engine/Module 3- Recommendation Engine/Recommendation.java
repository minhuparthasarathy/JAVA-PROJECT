// Concept Used: Class
// Recommendation class links a Student with a matching Internship and a computed score
public class Recommendation {

    // Concept Used: Encapsulation
    // Private data members protect data from direct access
    private Student student;
    private Internship internship;
    private double matchScore;

    // Concept Used: Constructor
    // Parameterized constructor to initialize all attributes
    public Recommendation(Student student, Internship internship, double matchScore) {

        this.student = student;
        this.internship = internship;
        this.matchScore = matchScore;

    }

    // Concept Used: Methods - Getters and Setters

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Internship getInternship() {
        return internship;
    }

    public void setInternship(Internship internship) {
        this.internship = internship;
    }

    public double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(double matchScore) {
        this.matchScore = matchScore;
    }

    // Concept Used: toString() Method
    // Returns a formatted string representation of the recommendation
    @Override
    public String toString() {

        return "\n----- Recommendation -----" +
               "\nStudent Name    : " + student.getName() +
               "\nStudent CGPA    : " + student.getCgpa() +
               "\nStudent Skills  : " + student.getSkills() +
               "\nCompany         : " + internship.getCompany() +
               "\nRole            : " + internship.getRole() +
               "\nRequired Skills : " + internship.getRequiredSkills() +
               "\nRequired CGPA   : " + internship.getRequiredCGPA() +
               "\nLocation        : " + internship.getLocation() +
               "\nStipend         : $" + internship.getStipend() +
               "\nDuration        : " + internship.getDuration() + " months" +
               "\nMatch Score     : " + String.format("%.2f", matchScore) + "%" +
               "\n--------------------------";

    }

}
