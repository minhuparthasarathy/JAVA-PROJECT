// Concept Used: Class
// Student class represents student objects
public class Student {

    // Concept Used: Encapsulation
    // Private variables prevent direct modification
    private int studentId;
    private String name;
    private int age;
    private String degree;
    private double cgpa;
    private String skills;
    private String interests;
    private String preferredLocation;
    private String password;

    // Concept Used: Constructor
    // Used to initialize Student object values
    public Student(int studentId,
                   String name,
                   int age,
                   String degree,
                   double cgpa,
                   String skills,
                   String interests,
                   String preferredLocation,
                   String password) {

        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.degree = degree;
        this.cgpa = cgpa;
        this.skills = skills;
        this.interests = interests;
        this.preferredLocation = preferredLocation;
        this.password = password;

    }

    // Concept Used: Methods
    // Getter methods to access private variables

    public int getStudentId() {

        return studentId;

    }

    public String getName() {

        return name;

    }

    public int getAge() {

        return age;

    }

    public String getDegree() {

        return degree;

    }

    public double getCgpa() {

        return cgpa;

    }

    public String getSkills() {

        return skills;

    }

    public String getInterests() {

        return interests;

    }

    public String getPreferredLocation() {

        return preferredLocation;

    }

    public String getPassword() {

        return password;

    }

    // Concept Used: Methods
    // Setter methods to modify private variables

    public void setSkills(String skills) {

        this.skills = skills;

    }

    public void setInterests(String interests) {

        this.interests = interests;

    }

    public void setPreferredLocation(String preferredLocation) {

        this.preferredLocation = preferredLocation;

    }

    // Concept Used: Method
    // Updates student information
    public void updateProfile(String skills,
                              String interests,
                              String location) {

        this.skills = skills;
        this.interests = interests;
        this.preferredLocation = location;

    }

    // Concept Used: Method
    // Converts student object to a pipe-delimited file format string
    // This format is used for saving to students.txt (Module 5 storage)
    public String toFileString() {

        return studentId + "|" + name + "|" + age + "|" + degree + "|" +
               cgpa + "|" + skills + "|" + interests + "|" +
               preferredLocation + "|" + password;

    }

    // Concept Used: Method
    // Displays student details
    public void displayProfile() {

        System.out.println("\n----- Student Profile -----");

        System.out.println("Student ID : " + studentId);

        System.out.println("Name : " + name);

        System.out.println("Age : " + age);

        System.out.println("Degree : " + degree);

        System.out.println("CGPA : " + cgpa);

        System.out.println("Skills : " + skills);

        System.out.println("Interests : " + interests);

        System.out.println("Preferred Location : "
                            + preferredLocation);

    }

}
