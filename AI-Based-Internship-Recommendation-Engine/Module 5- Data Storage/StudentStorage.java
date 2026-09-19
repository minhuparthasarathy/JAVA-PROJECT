import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Module 5 – Data Storage
 * StudentStorage handles loading and saving student data to students.txt
 * Uses FileReader, FileWriter, BufferedReader, BufferedWriter
 */
public class StudentStorage {

    private static final String STUDENTS_FILE = "students.txt";

    /**
     * Loads all students from students.txt
     * Returns empty list if file does not exist or has errors
     */
    public static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            File file = new File(STUDENTS_FILE);
            if (!file.exists()) {
                return students;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 9) {
                    try {
                        Student s = new Student(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim(),
                            Integer.parseInt(parts[2].trim()),
                            parts[3].trim(),
                            Double.parseDouble(parts[4].trim()),
                            parts[5].trim(),
                            parts[6].trim(),
                            parts[7].trim(),
                            parts[8].trim(),
                            0
                        );
                        students.add(s);
                    } catch (NumberFormatException e) {
                        // Skip malformed lines
                    }
                } else if (parts.length == 10) {
                    try {
                        Student s = new Student(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim(),
                            Integer.parseInt(parts[2].trim()),
                            parts[3].trim(),
                            Double.parseDouble(parts[4].trim()),
                            parts[5].trim(),
                            parts[6].trim(),
                            parts[7].trim(),
                            parts[9].trim(),
                            Double.parseDouble(parts[8].trim())
                        );
                        students.add(s);
                    } catch (NumberFormatException e) {
                        // Skip malformed lines
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            // Return empty list on error - do not crash
        }
        return students;
    }

    /**
     * Saves all students to students.txt (overwrites entire file)
     */
    public static void saveStudents(ArrayList<Student> students) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE));
            for (Student s : students) {
                writer.write(s.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "Unable to save student data. Please check file permissions.\nError: " + e.getMessage(),
                "Storage Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Saves a single student by loading existing, adding/updating, and saving all
     */
    public static void saveStudent(Student student) {
        ArrayList<Student> students = loadStudents();
        boolean found = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == student.getStudentId()) {
                students.set(i, student);
                found = true;
                break;
            }
        }
        if (!found) {
            students.add(student);
        }
        saveStudents(students);
    }

    /**
     * Removes a student by ID and saves
     */
    public static void deleteStudent(int studentId) {
        ArrayList<Student> students = loadStudents();
        students.removeIf(s -> s.getStudentId() == studentId);
        saveStudents(students);
    }
}
