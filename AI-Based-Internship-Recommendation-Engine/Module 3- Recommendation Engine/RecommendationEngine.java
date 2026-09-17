import java.util.ArrayList;

// Concept Used: Class
// RecommendationEngine generates personalized internship recommendations for students
public class RecommendationEngine {

    // Concept Used: Object Creation
    // References to Module 1 (UserManager) and Module 2 (InternshipManager)
    private UserManager userManager;
    private InternshipManager internshipManager;

    // Concept Used: Constructor
    // Constructor receives UserManager and InternshipManager to bridge both modules
    public RecommendationEngine(UserManager userManager, InternshipManager internshipManager) {

        this.userManager = userManager;
        this.internshipManager = internshipManager;

    }

    // Concept Used: Method
    // Generates a list of recommendations for a given student
    // Compares student profile against all available internships
    public ArrayList<Recommendation> generateRecommendations(Student student) {

        ArrayList<Recommendation> recommendations = new ArrayList<>();

        if (student == null) {
            return recommendations;
        }

        // Get all internships from Module 2
        ArrayList<Internship> internships = internshipManager.getAllInternships();

        // Concept Used: Conditional Statement
        // If no internships are available, return empty list
        if (internships.isEmpty()) {

            return recommendations;

        }

        // Concept Used: Collections - for-each loop
        // Iterate through all internships to find matching ones
        for (Internship internship : internships) {

            // Check CGPA eligibility first
            if (student.getCgpa() < internship.getRequiredCGPA()) {
                continue;
            }

            // Check skill compatibility - at least one skill must match
            if (!hasSkillMatch(student, internship)) {
                continue;
            }

            // Concept Used: Method Calling
            // Calculate match score between student and this internship
            double score = calculateMatchScore(student, internship);

            // Concept Used: Conditional Statement
            // Only recommend internships with match score > 0
            if (score > 0) {

                // Concept Used: Object Creation
                // Create Recommendation object with student, internship, and score
                recommendations.add(new Recommendation(student, internship, score));

            }

        }

        return recommendations;

    }

    // Concept Used: Method
    // Checks if the student has at least one skill matching at least one required skill
    // Used as a prerequisite for recommendation eligibility
    private boolean hasSkillMatch(Student student, Internship internship) {

        String studentSkillsRaw = student.getSkills();
        String requiredSkillsRaw = internship.getRequiredSkills();

        if (studentSkillsRaw == null || studentSkillsRaw.trim().isEmpty() ||
            requiredSkillsRaw == null || requiredSkillsRaw.trim().isEmpty()) {

            return false;

        }

        String[] studentSkills = studentSkillsRaw.split(",");
        String[] requiredSkills = requiredSkillsRaw.split(",");

        for (String ss : studentSkills) {

            ss = ss.trim().toLowerCase();

            for (String rs : requiredSkills) {

                rs = rs.trim().toLowerCase();

                if (ss.equals(rs)) {

                    return true;

                }

            }

        }

        return false;

    }

    // Concept Used: Method
    // Calculates a match score between a student and an internship
    // Score is based on: skill match percentage (60%) + CGPA eligibility (40%)
    public double calculateMatchScore(Student student, Internship internship) {

        if (student == null || internship == null) {
            return 0.0;
        }

        // Concept Used: Conditional Statement
        // Quick check: if student CGPA is less than required CGPA, score is 0
        if (student.getCgpa() < internship.getRequiredCGPA()) {

            return 0.0;

        }

        // Concept Used: String Handling - Skills Comparison
        // Step 1: Get and normalize skills strings
        String studentSkillsRaw = student.getSkills();
        String requiredSkillsRaw = internship.getRequiredSkills();

        // Handle null or empty skills
        if (studentSkillsRaw == null || studentSkillsRaw.trim().isEmpty() ||
            requiredSkillsRaw == null || requiredSkillsRaw.trim().isEmpty()) {

            return 0.0;

        }

        // Concept Used: String Handling - split(), trim(), toLowerCase()
        // Split skills by comma, trim whitespace, and convert to lowercase
        String[] studentSkills = studentSkillsRaw.split(",");
        String[] requiredSkills = requiredSkillsRaw.split(",");

        // Trim and lowercase all skills for case-insensitive comparison
        for (int i = 0; i < studentSkills.length; i++) {

            studentSkills[i] = studentSkills[i].trim().toLowerCase();

        }

        for (int i = 0; i < requiredSkills.length; i++) {

            requiredSkills[i] = requiredSkills[i].trim().toLowerCase();

        }

        // Concept Used: String Handling - Matching
        // Count how many of the student's skills match the required skills
        int matchCount = 0;

        for (String studentSkill : studentSkills) {

            for (String requiredSkill : requiredSkills) {

                // Concept Used: String Methods - equals()
                if (studentSkill.equals(requiredSkill)) {

                    matchCount++;
                    break; // Count each student skill only once

                }

            }

        }

        // Concept Used: Calculations - Percentage
        // Calculate skill match percentage (how many required skills the student has)
        double skillMatchPercentage = (double) matchCount / requiredSkills.length;

        // If no skills match, do not recommend
        if (matchCount == 0) {
            return 0.0;
        }

        // Concept Used: Calculations - Weighted Score
        // Skills contribute 60% of the total score
        double skillScore = skillMatchPercentage * 60.0;

        // CGPA contributes 40% of the total score
        // Normalize CGPA against maximum possible (10.0)
        double cgpaScore = (student.getCgpa() / 10.0) * 40.0;

        // Concept Used: Method Return
        // Total match score = skills (60%) + CGPA (40%)
        return skillScore + cgpaScore;

    }

    // Concept Used: Method
    // Builds a detailed skills comparison showing matched and missing skills
    private String buildSkillsComparison(Student student, Internship internship) {

        String studentSkillsRaw = student.getSkills();
        String requiredSkillsRaw = internship.getRequiredSkills();

        if (studentSkillsRaw == null || studentSkillsRaw.trim().isEmpty()) {
            return "\n  [Student has no skills listed]";
        }

        if (requiredSkillsRaw == null || requiredSkillsRaw.trim().isEmpty()) {
            return "\n  [Internship has no skills required]";
        }

        // Split and trim skill arrays
        String[] studentSkills = studentSkillsRaw.split(",");
        String[] requiredSkills = requiredSkillsRaw.split(",");

        for (int i = 0; i < studentSkills.length; i++) {
            studentSkills[i] = studentSkills[i].trim();
        }
        for (int i = 0; i < requiredSkills.length; i++) {
            requiredSkills[i] = requiredSkills[i].trim();
        }

        // Build matched and missing lists
        StringBuilder matchedBuilder = new StringBuilder();
        StringBuilder missingBuilder = new StringBuilder();

        for (String reqSkill : requiredSkills) {
            boolean found = false;
            for (String stuSkill : studentSkills) {
                if (stuSkill.equalsIgnoreCase(reqSkill)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                if (matchedBuilder.length() > 0) matchedBuilder.append(", ");
                matchedBuilder.append(reqSkill);
            } else {
                if (missingBuilder.length() > 0) missingBuilder.append(", ");
                missingBuilder.append(reqSkill);
            }
        }

        // Build the formatted output
        String result = "\n  Your Skills         : " + studentSkillsRaw;

        String matched = matchedBuilder.toString();
        String missing = missingBuilder.toString();

        if (!matched.isEmpty()) {
            result += "\n  ✓ Skills Matched    : " + matched;
        } else {
            result += "\n  ✓ Skills Matched    : None";
        }

        if (!missing.isEmpty()) {
            result += "\n  ✗ Skills Still Needed : " + missing;
        } else {
            result += "\n  ✗ Skills Still Needed : None (Perfect Match!)";
        }

        return result;

    }

    // Concept Used: Method
    // Displays recommendations for a student in the console with detailed skills breakdown
    public void viewRecommendations(Student student) {

        // Concept Used: Method Calling
        // Generate recommendations for this student
        ArrayList<Recommendation> recommendations = generateRecommendations(student);

        System.out.println("\n========================================");
        System.out.println("    INTERNSHIP RECOMMENDATIONS FOR");
        System.out.println("         " + student.getName().toUpperCase());
        System.out.println("========================================");

        // Display student's current profile summary
        System.out.println("\n----- Your Profile Summary -----");
        System.out.println("  Name    : " + student.getName());
        System.out.println("  CGPA    : " + student.getCgpa());
        System.out.println("  Skills  : " + student.getSkills());
        System.out.println("  Degree  : " + student.getDegree());
        System.out.println("  Location: " + student.getPreferredLocation());
        System.out.println("--------------------------------");

        // Concept Used: Conditional Statement
        if (recommendations.isEmpty()) {

            System.out.println("\nNo matching internships found based on your current profile.");
            System.out.println("Try updating your skills or CGPA to get better recommendations.");

        } else {

            System.out.println("\nFound " + recommendations.size() + " matching internship(s) for you!\n");

            // Concept Used: Collections - for-each loop
            // Display each recommendation with detailed skills comparison
            int recNumber = 1;
            for (Recommendation rec : recommendations) {

                Internship intern = rec.getInternship();

                System.out.println("=========================================");
                System.out.println("  Recommendation #" + recNumber);
                System.out.println("=========================================");

                // Internship details
                System.out.println("  Company     : " + intern.getCompany());
                System.out.println("  Role        : " + intern.getRole());
                System.out.println("  Location    : " + intern.getLocation());
                System.out.println("  Stipend     : $" + intern.getStipend());
                System.out.println("  Duration    : " + intern.getDuration() + " months");

                // CGPA comparison
                System.out.println("\n  -- CGPA Check --");
                System.out.println("  Your CGPA        : " + student.getCgpa());
                System.out.println("  Required CGPA    : " + intern.getRequiredCGPA());
                System.out.println("  CGPA Met?        : " + (student.getCgpa() >= intern.getRequiredCGPA() ? "YES ✓" : "NO ✗"));

                // Skills comparison
                System.out.println("\n  -- Skills Comparison --");
                System.out.println(buildSkillsComparison(student, intern));

                // Score breakdown
                System.out.println("\n  -- Score Breakdown --");
                System.out.println("  Skills Score (60% weight) : " + String.format("%.2f", rec.getMatchScore() * 0.6) + " / 60");
                System.out.println("  CGPA Score (40% weight)   : " + String.format("%.2f", rec.getMatchScore() * 0.4) + " / 40");
                System.out.println("  TOTAL MATCH SCORE         : " + String.format("%.2f", rec.getMatchScore()) + "%");

                System.out.println("=========================================\n");
                recNumber++;

            }

            // Show best match summary
            System.out.println("\n************** BEST MATCH SUMMARY **************");

            Recommendation best = recommendations.get(0);
            for (Recommendation rec : recommendations) {
                if (rec.getMatchScore() > best.getMatchScore()) {
                    best = rec;
                }
            }

            System.out.println("  Company : " + best.getInternship().getCompany());
            System.out.println("  Role    : " + best.getInternship().getRole());
            System.out.println("  Score   : " + String.format("%.2f", best.getMatchScore()) + "%");
            System.out.println("**************************************************");

        }

        System.out.println("========================================\n");

    }

}
