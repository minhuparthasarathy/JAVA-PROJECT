import java.util.ArrayList;

public class SearchFilter {

    private ArrayList<Internship> allInternships;

    public SearchFilter(ArrayList<Internship> internships) {
        this.allInternships = new ArrayList<>(internships);
    }

    public ArrayList<Internship> searchByCompany(ArrayList<Internship> source, String query) {
        ArrayList<Internship> result = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            result.addAll(source);
            return result;
        }
        String q = query.trim().toLowerCase();
        for (Internship intern : source) {
            if (intern.getCompany() != null && intern.getCompany().toLowerCase().contains(q)) {
                result.add(intern);
            }
        }
        return result;
    }

    public ArrayList<Internship> searchByRole(ArrayList<Internship> source, String query) {
        ArrayList<Internship> result = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            result.addAll(source);
            return result;
        }
        String q = query.trim().toLowerCase();
        for (Internship intern : source) {
            if (intern.getRole() != null && intern.getRole().toLowerCase().contains(q)) {
                result.add(intern);
            }
        }
        return result;
    }

    public ArrayList<Internship> filterByLocation(ArrayList<Internship> source, String query) {
        ArrayList<Internship> result = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            result.addAll(source);
            return result;
        }
        String q = query.trim().toLowerCase();
        for (Internship intern : source) {
            if (intern.getLocation() != null && intern.getLocation().toLowerCase().contains(q)) {
                result.add(intern);
            }
        }
        return result;
    }

    public ArrayList<Internship> filterBySkill(ArrayList<Internship> source, String query) {
        ArrayList<Internship> result = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            result.addAll(source);
            return result;
        }
        String[] searchSkills = query.trim().toLowerCase().split(",");
        for (Internship intern : source) {
            String required = intern.getRequiredSkills();
            if (required == null || required.trim().isEmpty()) {
                continue;
            }
            String[] requiredSkills = required.toLowerCase().split(",");
            boolean match = false;
            for (String ss : searchSkills) {
                ss = ss.trim();
                if (ss.isEmpty()) continue;
                for (String rs : requiredSkills) {
                    rs = rs.trim();
                    if (ss.equals(rs)) {
                        match = true;
                        break;
                    }
                }
                if (match) break;
            }
            if (match) {
                result.add(intern);
            }
        }
        return result;
    }

    public ArrayList<Internship> filterByMinimumCGPA(ArrayList<Internship> source, double minCGPA) {
        ArrayList<Internship> result = new ArrayList<>();
        for (Internship intern : source) {
            if (intern.getRequiredCGPA() <= minCGPA) {
                result.add(intern);
            }
        }
        return result;
    }

    public ArrayList<Internship> applyFilters(String company, String role,
                                               String location, String skills,
                                               double minCGPA) {
        ArrayList<Internship> result = new ArrayList<>(allInternships);
        result = searchByCompany(result, company);
        result = searchByRole(result, role);
        result = filterByLocation(result, location);
        result = filterBySkill(result, skills);
        result = filterByMinimumCGPA(result, minCGPA);
        return result;
    }

    public ArrayList<Internship> clearFilters() {
        return new ArrayList<>(allInternships);
    }
}
