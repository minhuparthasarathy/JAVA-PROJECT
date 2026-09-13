import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Swing GUI - Main application frame
 * BorderLayout - outer frame layout
 * CardLayout - screen navigation
 * Encapsulation - holds all business logic objects
 * File I/O - loads internships.txt
 * Object Creation - initializes demo data
 */
public class AppFrame extends JFrame {

    private UserManager userManager;
    private InternshipManager internshipManager;
    private RecommendationEngine recommendationEngine;
    private Student currentStudent = null;
    private boolean isAdminLoggedIn = false;
    private CardLayout cardLayout;
    private JPanel cards;

    // All panels
    private WelcomePanel welcomePanel;
    private StudentLoginPanel studentLoginPanel;
    private StudentRegPanel studentRegPanel;
    private StudentDashPanel studentDashPanel;
    private ProfilePanel profilePanel;
    private RecoPanel recoPanel;
    private AdminLoginPanel adminLoginPanel;
    private AdminDashPanel adminDashPanel;
    private InternshipMgmtPanel internshipMgmtPanel;
    private SearchPanel searchPanel;

    public static final Color PRIMARY = new Color(37, 99, 235);
    public static final Color PRIMARY_DARK = new Color(29, 78, 216);
    public static final Color PRIMARY_LIGHT = new Color(219, 234, 254);
    public static final Color BG = new Color(248, 250, 252);
    public static final Color SURFACE = Color.WHITE;
    public static final Color TEXT = new Color(30, 41, 59);
    public static final Color TEXT_SEC = new Color(100, 116, 139);
    public static final Color SUCCESS = new Color(22, 163, 74);
    public static final Color ERROR = new Color(220, 38, 38);
    public static final Color BORDER_COLOR = new Color(226, 232, 240);
    public static final Color PRIMARY_HOVER = new Color(30, 80, 210);
    public static final Color PRIMARY_PRESSED = new Color(22, 65, 175);
    public static final Color PRIMARY_DARK_HOVER = new Color(24, 68, 195);
    public static final Color PRIMARY_DARK_PRESSED = new Color(16, 52, 160);
    public static final Color RED_HOVER = new Color(195, 30, 30);
    public static final Color RED_PRESSED = new Color(170, 20, 20);
    public static final Font APP_TITLE = new Font("SansSerif", Font.BOLD, 24);
    public static final Font PAGE_TITLE = new Font("SansSerif", Font.BOLD, 18);
    public static final Font SECTION_TITLE = new Font("SansSerif", Font.BOLD, 15);
    public static final Font LABEL_FONT = new Font("SansSerif", Font.PLAIN, 13);
    public static final Font DESC_FONT = new Font("SansSerif", Font.PLAIN, 13);
    public static final Font SMALL_FONT = new Font("SansSerif", Font.PLAIN, 11);
    public static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 14);
    public static final Font BODY_FONT = new Font("SansSerif", Font.PLAIN, 14);

    public static void applyHover(JButton btn, Color bg, Color hover, Color pressed) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(MouseEvent e) { btn.setBackground(bg); }
            public void mousePressed(MouseEvent e) { btn.setBackground(pressed); }
            public void mouseReleased(MouseEvent e) { btn.setBackground(hover); }
        });
    }

    public static void stylePrimary(JButton btn) {
        btn.setFont(BUTTON_FONT);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setUI(new BasicButtonUI());
        btn.setBackground(PRIMARY);
        btn.setForeground(Color.WHITE);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.repaint();
        applyHover(btn, PRIMARY, PRIMARY_HOVER, PRIMARY_PRESSED);
    }

    public static void styleDelete(JButton btn) {
        btn.setFont(BUTTON_FONT);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setUI(new BasicButtonUI());
        btn.setBackground(new Color(220, 38, 38));
        btn.setForeground(Color.WHITE);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.repaint();
        applyHover(btn, new Color(220, 38, 38), RED_HOVER, RED_PRESSED);
    }

    public static void styleBack(JButton btn) {
        btn.setFont(BUTTON_FONT);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setUI(new BasicButtonUI());
        btn.setBackground(Color.LIGHT_GRAY);
        btn.setForeground(Color.DARK_GRAY);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.repaint();
        applyHover(btn, Color.LIGHT_GRAY, new Color(180, 180, 180), new Color(150, 150, 150));
    }

    /**
     * Constructor - initializes all business logic and GUI
     */
    public AppFrame() {
        setTitle("AI-Based Internship Recommendation Engine");
        setSize(1000, 720);
        setMinimumSize(new Dimension(960, 640));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(BG);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Ignore - using default L&F
        }

        // Swing GUI - Force buttons to render with custom colors
        UIManager.put("Button.opaque", true);
        UIManager.put("Button.contentAreaFilled", true);
        UIManager.put("Button.borderPainted", false);

        // Initialize business logic
        userManager = new UserManager();

        // Load existing students from storage (Module 5)
        ArrayList<Student> loadedStudents = StudentStorage.loadStudents();
        for (Student s : loadedStudents) {
            if (userManager.count < userManager.students.length) {
                userManager.students[userManager.count++] = s;
            }
        }

        internshipManager = new InternshipManager(userManager);
        recommendationEngine = new RecommendationEngine(userManager, internshipManager);
        userManager.setRecommendationEngine(recommendationEngine);

        // CardLayout for screen navigation
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setBackground(BG);

        // Create all panels
        welcomePanel = new WelcomePanel(this);
        studentLoginPanel = new StudentLoginPanel(this);
        studentRegPanel = new StudentRegPanel(this);
        studentDashPanel = new StudentDashPanel(this);
        profilePanel = new ProfilePanel(this);
        recoPanel = new RecoPanel(this);
        adminLoginPanel = new AdminLoginPanel(this);
        adminDashPanel = new AdminDashPanel(this);
        internshipMgmtPanel = new InternshipMgmtPanel(this);
        searchPanel = new SearchPanel(this);

        cards.add(welcomePanel, "welcome");
        cards.add(studentLoginPanel, "studentLogin");
        cards.add(studentRegPanel, "studentReg");
        cards.add(studentDashPanel, "studentDash");
        cards.add(profilePanel, "profile");
        cards.add(recoPanel, "recommendations");
        cards.add(adminLoginPanel, "adminLogin");
        cards.add(adminDashPanel, "adminDash");
        cards.add(internshipMgmtPanel, "internshipMgmt");
        cards.add(searchPanel, "search");

        setLayout(new BorderLayout());
        add(cards, BorderLayout.CENTER);

        // Load internship data from file
        loadInternshipsFromFile();
        initializeDemoData();
        showWelcome();
    }

    private void loadInternshipsFromFile() {
        try {
            java.io.File file = new java.io.File("internships.txt");
            if (!file.exists()) return;
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 8) {
                    try {
                        Internship intern = new Internship(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim(), parts[2].trim(), parts[3].trim(),
                            Double.parseDouble(parts[4].trim()),
                            parts[5].trim(), Double.parseDouble(parts[6].trim()),
                            Integer.parseInt(parts[7].trim())
                        );
                        internshipManager.addInternship(intern);
                    } catch (NumberFormatException e) {
                        // Skip malformed lines
                    }
                }
            }
            fileScanner.close();
        } catch (java.io.FileNotFoundException e) {
            // File not found - no pre-loaded internships
        }
    }

    /**
     * Object Creation - demo student for testing login
     */
    private void initializeDemoData() {
        for (int i = 0; i < userManager.count; i++) {
            if (userManager.students[i].getStudentId() == 101) return;
        }
        Student demo = new Student(101, "Alice Johnson", 21,
            "B.Tech Computer Science", 8.5,
            "java,python,react", "Web Development, AI",
            "Bangalore", "pass123");
        userManager.students[userManager.count++] = demo;
    }

    public void showPanel(String name) { cardLayout.show(cards, name); }
    public void showWelcome() { showPanel("welcome"); }
    public void showStudentLogin() { showPanel("studentLogin"); }
    public void showStudentReg() { showPanel("studentReg"); }
    public void showStudentDash() { studentDashPanel.refresh(); showPanel("studentDash"); }
    public void showProfile() { profilePanel.refresh(); showPanel("profile"); }
    public void showRecommendations() { recoPanel.refresh(); showPanel("recommendations"); }
    public void showAdminLogin() { showPanel("adminLogin"); }
    public void showAdminDash() { showPanel("adminDash"); }
    public void showInternshipMgmt() { showPanel("internshipMgmt"); }
    public void showSearch() { searchPanel.refresh(); showPanel("search"); }

    public void setCurrentStudent(Student s) { this.currentStudent = s; }
    public Student getCurrentStudent() { return currentStudent; }
    public void setAdminLoggedIn(boolean val) { this.isAdminLoggedIn = val; }
    public boolean isAdminLoggedIn() { return isAdminLoggedIn; }

    public void logout() {
        currentStudent = null;
        isAdminLoggedIn = false;
        userManager.adminLogout();
        showWelcome();
    }

    public UserManager getUserManager() { return userManager; }
    public InternshipManager getInternshipManager() { return internshipManager; }
    public RecommendationEngine getRecommendationEngine() { return recommendationEngine; }
    public boolean isStudentLoggedIn() { return currentStudent != null; }
}
