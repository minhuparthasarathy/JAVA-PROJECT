import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class SearchFilter {

    private ArrayList<Internship> allInternships;

    // MODULE 8: HashMap for fast internship lookup by ID (O(1) vs O(n))
    private HashMap<Integer, Internship> internshipMap;

    public SearchFilter(ArrayList<Internship> internships) {
        this.allInternships = new ArrayList<>(internships);
        this.internshipMap = new HashMap<>();

        for (Internship intern : internships) {
            this.internshipMap.put(intern.getId(), intern);
        }
    }

    private boolean containsIgnoreCase(String value, String query) {
        if (query == null || query.trim().isEmpty()) {
            return true;
        }

        return value != null
                && value.trim().toLowerCase().contains(query.trim().toLowerCase());
    }

    // MODULE 8: Fast lookup by ID using HashMap
    public Internship getInternshipById(int id) {
        return internshipMap.get(id);
    }

    public ArrayList<Internship> searchByCompany(
            ArrayList<Internship> source, String query) {

        ArrayList<Internship> result = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            result.addAll(source);
            return result;
        }

        String q = query.trim().toLowerCase();

        for (Internship intern : source) {
            if (containsIgnoreCase(intern.getCompany(), q)) {
                result.add(intern);
            }
        }

        return result;
    }

    public ArrayList<Internship> searchByRole(
            ArrayList<Internship> source, String query) {

        ArrayList<Internship> result = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            result.addAll(source);
            return result;
        }

        String q = query.trim().toLowerCase();

        for (Internship intern : source) {
            if (containsIgnoreCase(intern.getRole(), q)) {
                result.add(intern);
            }
        }

        return result;
    }

    public ArrayList<Internship> filterByLocation(
            ArrayList<Internship> source, String query) {

        ArrayList<Internship> result = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            result.addAll(source);
            return result;
        }

        String q = query.trim().toLowerCase();

        for (Internship intern : source) {
            String loc = intern.getLocation();

            if (loc != null && loc.trim().toLowerCase().contains(q)) {
                result.add(intern);
            }
        }

        return result;
    }

    public ArrayList<Internship> filterBySkill(
            ArrayList<Internship> source, String query) {

        ArrayList<Internship> result = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            result.addAll(source);
            return result;
        }

        // MODULE 8: HashSet for efficient skill matching
        Set<String> searchSkillSet = new HashSet<>();

        for (String s : query.trim().toLowerCase().split(",")) {
            String trimmed = s.trim();

            if (!trimmed.isEmpty()) {
                searchSkillSet.add(trimmed);
            }
        }

        for (Internship intern : source) {

            String required = intern.getRequiredSkills();

            if (required == null || required.trim().isEmpty()) {
                continue;
            }

            // MODULE 8: HashSet for efficient skill intersection
            Set<String> requiredSkillSet = new HashSet<>();

            for (String s : required.toLowerCase().split(",")) {
                String trimmed = s.trim();

                if (!trimmed.isEmpty()) {
                    requiredSkillSet.add(trimmed);
                }
            }

            // Check if any search skill matches any required skill
            Set<String> intersection = new HashSet<>(searchSkillSet);
            intersection.retainAll(requiredSkillSet);

            if (!intersection.isEmpty()) {
                result.add(intern);
            }
        }

        return result;
    }

    public ArrayList<Internship> filterByMinimumCGPA(
            ArrayList<Internship> source, double minCGPA) {

        ArrayList<Internship> result = new ArrayList<>();

        for (Internship intern : source) {

            // Minimum CGPA means the internship's required CGPA
            // must be equal to or greater than the entered value.
            if (intern.getRequiredCGPA() >= minCGPA) {
                result.add(intern);
            }
        }

        return result;
    }

    // MODULE 8: Comparator for sorting by stipend (descending)
    public static final Comparator<Internship> STIPEND_DESC =
            (a, b) -> Double.compare(b.getStipend(), a.getStipend());

    // MODULE 8: Comparator for required CGPA (descending)
    public static final Comparator<Internship> CGPA_DESC =
            (a, b) -> Double.compare(
                    b.getRequiredCGPA(),
                    a.getRequiredCGPA());

    // MODULE 8: Comparator for company name (ascending)
    public static final Comparator<Internship> COMPANY_ASC =
            (a, b) -> a.getCompany()
                    .compareToIgnoreCase(b.getCompany());

    /**
     * Sort internships using the specified comparator.
     * MODULE 8: Uses Comparator with List.sort().
     */
    public void sortResults(
            ArrayList<Internship> list,
            Comparator<Internship> comparator) {

        list.sort(comparator);
    }

    // MODULE 8: Unified search across company, role,
    // location, and skills using OR logic.
    // A single search term can match any of these fields.
    private boolean matchesSearch(Internship intern, String query) {

        if (query == null || query.trim().isEmpty()) {
            return true;
        }

        String q = query.trim().toLowerCase();

        return containsIgnoreCase(intern.getCompany(), q)
                || containsIgnoreCase(intern.getRole(), q)
                || containsIgnoreCase(intern.getLocation(), q)
                || containsIgnoreCase(intern.getRequiredSkills(), q);
    }

    /**
     * Apply the general search and filter criteria.
     *
     * Each filter is optional.
     * Empty fields are ignored.
     *
     * A single filled field is enough to return matching results.
     * Multiple filled fields are applied together.
     */
    public List<Internship> applyFilters(
            String search,
            String location,
            String skills,
            double minCGPA) {

        ArrayList<Internship> result = new ArrayList<>();

        // General search is optional.
        // It searches company, role, location, or skills.
        for (Internship intern : allInternships) {

            if (matchesSearch(intern, search)) {
                result.add(intern);
            }
        }

        // Apply location only when entered.
        result = filterByLocation(result, location);

        // Apply skills only when entered.
        result = filterBySkill(result, skills);

        // Apply CGPA only when entered.
        if (minCGPA > 0) {
            result = filterByMinimumCGPA(result, minCGPA);
        }

        return result;
    }

    /**
     * Apply separate company and role filters.
     *
     * Each field is optional.
     * If only company is entered, only company is checked.
     * If only role is entered, only role is checked.
     * If both are entered, both conditions must match.
     */
    public List<Internship> applyFilters(
            String company,
            String role,
            String location,
            String skills,
            double minCGPA) {

        ArrayList<Internship> result = new ArrayList<>();

        for (Internship intern : allInternships) {

            boolean companyEntered =
                    company != null && !company.trim().isEmpty();

            boolean roleEntered =
                    role != null && !role.trim().isEmpty();

            boolean companyMatches =
                    !companyEntered
                    || containsIgnoreCase(intern.getCompany(), company);

            boolean roleMatches =
                    !roleEntered
                    || containsIgnoreCase(intern.getRole(), role);

            // Empty fields are ignored.
            // Filled fields are applied together.
            if (companyMatches && roleMatches) {
                result.add(intern);
            }
        }

        // Apply location only when entered.
        result = filterByLocation(result, location);

        // Apply skills only when entered.
        result = filterBySkill(result, skills);

        // Apply CGPA only when entered.
        if (minCGPA > 0) {
            result = filterByMinimumCGPA(result, minCGPA);
        }

        return result;
    }

    /**
     * Clear all filters and return all internships.
     */
    public List<Internship> clearFilters() {
        return new ArrayList<>(allInternships);
    }
}