import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Swing GUI - Recommendations Panel
 * BorderLayout - outer layout
 * Encapsulation - RecommendationEngine, Recommendation
 * ArrayList - recommendations list
 */
public class RecoPanel extends JPanel {

    private AppFrame frame;
    private JPanel center;
    private SwingWorker<ArrayList<Recommendation>, Void> recoWorker;

    public RecoPanel(AppFrame frame) {
        this.frame = frame;
        setBackground(AppFrame.BG);
        setLayout(new BorderLayout(0, 0));

        add(createHeader("Internship Recommendations"), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
    }

    private JPanel createHeader(String title) {
        JPanel header = new JPanel();
        header.setBackground(AppFrame.PRIMARY);
        header.setPreferredSize(new Dimension(1000, 64));
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(0, 24, 0, 24));

        JLabel label = new JLabel(title);
        label.setFont(AppFrame.PAGE_TITLE);
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(0, 0, 0, 16));
        header.add(label, BorderLayout.CENTER);
        return header;
    }

    private JPanel createContent() {
        center = new JPanel();
        center.setBackground(AppFrame.BG);
        center.setLayout(new BorderLayout(0, 8));
        center.setBorder(new EmptyBorder(20, 24, 20, 24));

        Student s = frame.getCurrentStudent();
        if (s == null) {
            center.add(new JLabel("Please login to see recommendations."));
            return center;
        }

        JLabel loadingLabel = new JLabel("Generating recommendations...");
        loadingLabel.setFont(AppFrame.BODY_FONT);
        loadingLabel.setForeground(AppFrame.TEXT_SEC);
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        center.add(loadingLabel, BorderLayout.CENTER);

        startRecommendationTask(s);

        return center;
    }

    private void startRecommendationTask(Student s) {
        if (recoWorker != null) {
            recoWorker.cancel(true);
            recoWorker = null;
        }
        recoWorker = new SwingWorker<ArrayList<Recommendation>, Void>() {
            @Override
            protected ArrayList<Recommendation> doInBackground() throws Exception {
                return frame.getRecommendationEngine().generateRecommendations(s);
            }

        @Override
        protected void done() {
            if (isCancelled()) {
                frame.setRecoButtonEnabled(true);
                recoWorker = null;
                return;
            }
            try {
                ArrayList<Recommendation> recommendations = get();
                replaceWithResults(recommendations);
            } catch (Exception e) {
                replaceWithError(e);
            } finally {
                frame.setRecoButtonEnabled(true);
                recoWorker = null;
            }
        }
        };
        frame.setRecoButtonEnabled(false);
        recoWorker.execute();
    }

    private void replaceWithResults(ArrayList<Recommendation> recommendations) {
        remove(center);
        center = buildResultsPanel(recommendations);
        add(center, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void replaceWithError(Exception e) {
        remove(center);
        center = new JPanel();
        center.setBackground(AppFrame.BG);
        center.setLayout(new BorderLayout(0, 8));
        center.setBorder(new EmptyBorder(20, 24, 20, 24));

        String message = "Failed to load recommendations.";
        if (e.getMessage() != null && !e.getMessage().isEmpty()) {
            message += " " + e.getMessage();
        }
        JLabel errorLabel = new JLabel(
            "<html><center>" + message + "<br>Please try again later.</center></html>");
        errorLabel.setFont(AppFrame.BODY_FONT);
        errorLabel.setForeground(AppFrame.ERROR);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        center.add(errorLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(AppFrame.BG);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backBtn = new JButton("Back to Dashboard");
        AppFrame.styleBack(backBtn);
        backBtn.addActionListener(e2 -> frame.showStudentDash());

        bottomPanel.add(backBtn);
        center.add(bottomPanel, BorderLayout.SOUTH);

        add(center, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JPanel buildResultsPanel(ArrayList<Recommendation> recommendations) {
        JPanel panel = new JPanel();
        panel.setBackground(AppFrame.BG);
        panel.setLayout(new BorderLayout(0, 8));
        panel.setBorder(new EmptyBorder(20, 24, 20, 24));

        JPanel listPanel = new JPanel();
        listPanel.setBackground(AppFrame.BG);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(new EmptyBorder(0, 0, 16, 0));

        if (recommendations.isEmpty()) {
            JLabel noReco = new JLabel(
                "<html><center>No matching internships found based on your current profile.<br>" +
                "Try updating your skills or CGPA to get better recommendations.</center></html>");
            noReco.setFont(AppFrame.DESC_FONT);
            noReco.setForeground(AppFrame.TEXT_SEC);
            noReco.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(noReco);
        } else {
            for (int i = 0; i < recommendations.size(); i++) {
                boolean isBest = (i == 0);
                JComponent card = createRecoCard(recommendations.get(i), isBest);
                listPanel.add(card);
                listPanel.add(Box.createVerticalStrut(16));
            }
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setBlockIncrement(48);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(AppFrame.BG);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backBtn = new JButton("Back to Dashboard");
        AppFrame.styleBack(backBtn);
        backBtn.addActionListener(e -> frame.showStudentDash());

        bottomPanel.add(backBtn);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates a recommendation card with clear label/value sections
     */
    private JComponent createRecoCard(Recommendation rec, boolean isBest) {
        Internship intern = rec.getInternship();
        Student student = rec.getStudent();
        double score = rec.getMatchScore();

        JPanel card = new JPanel();
        card.setBackground(AppFrame.SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(16, 20, 16, 20),
            BorderFactory.createLineBorder(isBest ? AppFrame.SUCCESS : AppFrame.BORDER_COLOR,
                isBest ? 2 : 1)
        ));
        card.setLayout(new BorderLayout(0, 8));
        card.setMaximumSize(new Dimension(900, Integer.MAX_VALUE));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Right side - Match score badge
        JPanel right = new JPanel();
        right.setBackground(AppFrame.SURFACE);
        right.setLayout(new GridBagLayout());
        right.setMaximumSize(new Dimension(90, Integer.MAX_VALUE));

        JLabel scoreCircle = new JLabel(String.format("%.1f", score) + "%");
        scoreCircle.setFont(new Font("SansSerif", Font.BOLD, 22));
        scoreCircle.setForeground(AppFrame.PRIMARY);
        scoreCircle.setHorizontalAlignment(SwingConstants.CENTER);
        scoreCircle.setPreferredSize(new Dimension(70, 70));
        right.add(scoreCircle);

        if (isBest) {
            JLabel badge = new JLabel("\u2605 BEST MATCH");
            badge.setFont(new Font("SansSerif", Font.BOLD, 11));
            badge.setForeground(AppFrame.SUCCESS);
            badge.setHorizontalAlignment(SwingConstants.CENTER);
            badge.setAlignmentX(Component.CENTER_ALIGNMENT);
            right.add(Box.createVerticalStrut(4));
            right.add(badge);
        }

        // Left side - Details with proper spacing
        JPanel left = new JPanel();
        left.setBackground(AppFrame.SURFACE);
        left.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1.0;

        // Company
        JLabel company = new JLabel("<html><b>" + escapeHtml(intern.getCompany()) + "</b></html>");
        company.setFont(new Font("SansSerif", Font.BOLD, 16));
        company.setForeground(AppFrame.TEXT);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 2, 0);
        left.add(company, gbc);

        // Role + Location
        JLabel role = new JLabel(escapeHtml(intern.getRole()) + "  \u2022  " + escapeHtml(intern.getLocation()));
        role.setFont(AppFrame.BODY_FONT);
        role.setForeground(AppFrame.PRIMARY);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 12, 0);
        left.add(role, gbc);

        // Details with labels
        String[][] details = {
            {"Required CGPA", String.valueOf(intern.getRequiredCGPA())},
            {"Stipend", String.format("$%,.0f", intern.getStipend())},
            {"Duration", intern.getDuration() + " months"},
            {"Required Skills", escapeHtml(intern.getRequiredSkills())},
        };

        for (int i = 0; i < details.length; i++) {
            JLabel dl = new JLabel("<html><font color='#64748B' size='-1'>" + details[i][0] + "</font></html>");
            gbc.gridy = 2 + i * 2;
            gbc.insets = new Insets(0, 0, 2, 0);
            left.add(dl, gbc);

            JLabel dv = new JLabel(details[i][1]);
            dv.setFont(AppFrame.BODY_FONT);
            dv.setForeground(AppFrame.TEXT);
            gbc.gridy = 3 + i * 2;
            gbc.insets = new Insets(0, 0, 10, 0);
            left.add(dv, gbc);
        }

        // CGPA check
        boolean cgpaMet = student.getCgpa() >= intern.getRequiredCGPA();
        JLabel cgpaCheck = new JLabel(
            "Your CGPA: " + student.getCgpa() + " | Required: " + intern.getRequiredCGPA() +
            "  \u2192  " + (cgpaMet ? "YES \u2713" : "NO \u2717"));
        cgpaCheck.setFont(AppFrame.SMALL_FONT);
        cgpaCheck.setForeground(cgpaMet ? AppFrame.SUCCESS : AppFrame.ERROR);
        gbc.gridy = 10;
        gbc.insets = new Insets(6, 0, 0, 0);
        left.add(cgpaCheck, gbc);

        // Matched/missing skills
        String[] sSkills = student.getSkills().toLowerCase().split(",");
        String[] rSkills = intern.getRequiredSkills().toLowerCase().split(",");
        boolean[] used = new boolean[rSkills.length];
        ArrayList<String> matched = new ArrayList<>();
        for (String ss : sSkills) {
            ss = ss.trim();
            for (int j = 0; j < rSkills.length; j++) {
                if (rSkills[j].trim().equals(ss) && !used[j]) {
                    matched.add(rSkills[j].trim());
                    used[j] = true;
                    break;
                }
            }
        }
        ArrayList<String> missing = new ArrayList<>();
        for (int j = 0; j < rSkills.length; j++) {
            if (!used[j]) missing.add(rSkills[j].trim());
        }

        JLabel matchLbl = new JLabel("Matched: " + (matched.isEmpty() ? "None" : String.join(", ", matched)));
        matchLbl.setFont(AppFrame.SMALL_FONT);
        matchLbl.setForeground(AppFrame.TEXT);
        gbc.gridy = 11;
        gbc.insets = new Insets(4, 0, 0, 0);
        left.add(matchLbl, gbc);

        JLabel missLbl = new JLabel("Missing: " + (missing.isEmpty() ? "None (Perfect Match!)" : String.join(", ", missing)));
        missLbl.setFont(AppFrame.SMALL_FONT);
        missLbl.setForeground(AppFrame.TEXT_SEC);
        gbc.gridy = 12;
        gbc.insets = new Insets(2, 0, 0, 0);
        left.add(missLbl, gbc);

        // Score breakdown
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(AppFrame.PRIMARY_LIGHT);
        scorePanel.setBorder(new EmptyBorder(8, 8, 8, 8));
        scorePanel.setLayout(new GridLayout(3, 1, 4, 4));
        scorePanel.setMaximumSize(new Dimension(400, 80));

        double skillScore = score * 0.6;
        double cgpaScore = score * 0.4;
        scorePanel.add(createMiniBar("Skills (60%): " + String.format("%.1f", skillScore) + " / 60", skillScore, 60, AppFrame.PRIMARY));
        scorePanel.add(createMiniBar("CGPA (40%): " + String.format("%.1f", cgpaScore) + " / 40", cgpaScore, 40, AppFrame.SUCCESS));

        JLabel total = new JLabel("Total Match Score: " + String.format("%.1f", score) + "%");
        total.setFont(new Font("SansSerif", Font.BOLD, 13));
        total.setForeground(AppFrame.PRIMARY);
        total.setHorizontalAlignment(SwingConstants.CENTER);
        scorePanel.add(total);

        gbc.gridy = 14;
        gbc.insets = new Insets(10, 0, 0, 0);
        left.add(scorePanel, gbc);

        card.add(left, BorderLayout.CENTER);
        card.add(right, BorderLayout.EAST);

        return card;
    }

    /**
     * Progress bar component for score visualization
     */
    private JPanel createMiniBar(String label, double value, double max, Color color) {
        JPanel p = new JPanel();
        p.setBackground(AppFrame.SURFACE);
        p.setLayout(new BorderLayout(0, 2));
        p.setBorder(new EmptyBorder(2, 2, 2, 2));

        JLabel l = new JLabel(label);
        l.setFont(AppFrame.SMALL_FONT);
        l.setForeground(AppFrame.TEXT_SEC);
        p.add(l, BorderLayout.NORTH);

        JProgressBar bar = new JProgressBar(0, (int) max);
        bar.setValue((int) value);
        bar.setForeground(color);
        bar.setBackground(AppFrame.BORDER_COLOR);
        bar.setBorderPainted(false);
        bar.setPreferredSize(new Dimension(350, 8));
        p.add(bar, BorderLayout.CENTER);

        return p;
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    public void refresh() {
        removeAll();
        add(createHeader("Internship Recommendations"), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
