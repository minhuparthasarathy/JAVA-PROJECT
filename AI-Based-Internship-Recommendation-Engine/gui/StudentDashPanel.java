import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

/**
 * Swing GUI - Student Dashboard Panel
 * BorderLayout - outer layout
 * GridBagLayout - content layout
 * Encapsulation - accesses AppFrame and UserManager
 */
public class StudentDashPanel extends JPanel {

    private AppFrame frame;
    private JButton recoBtn;

    public StudentDashPanel(AppFrame frame) {
        this.frame = frame;
        setBackground(AppFrame.BG);
        setLayout(new BorderLayout(0, 0));

        add(createHeader(), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(AppFrame.PRIMARY);
        header.setPreferredSize(new Dimension(1000, 64));
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(0, 24, 0, 24));

        Student s = frame.getCurrentStudent();
        JPanel left = new JPanel();
        left.setBackground(AppFrame.PRIMARY);
        left.setLayout(new GridLayout(2, 1));
        left.setBorder(new EmptyBorder(4, 0, 4, 0));

        JLabel name = new JLabel("Welcome, " + (s != null ? s.getName() : "Student") + "!");
        name.setFont(AppFrame.PAGE_TITLE);
        name.setForeground(Color.WHITE);
        left.add(name);

        JLabel sub = new JLabel("Manage your profile and explore internships");
        sub.setFont(AppFrame.SMALL_FONT);
        sub.setForeground(new Color(191, 210, 254));
        left.add(sub);

        header.add(left, BorderLayout.CENTER);

        JButton logoutBtn = new JButton("Logout");
        AppFrame.styleDelete(logoutBtn);
        logoutBtn.addActionListener(e -> frame.logout());

        header.add(logoutBtn, BorderLayout.EAST);
        return header;
    }

    private JPanel createContent() {
        JPanel center = new JPanel();
        center.setBackground(AppFrame.BG);
        center.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 24, 20, 24);
        gbc.weightx = 1.0;

        JPanel card = new JPanel();
        card.setBackground(AppFrame.SURFACE);
        card.setBorder(new CompoundBorder(
            new EmptyBorder(24, 32, 24, 32),
            new LineBorder(AppFrame.BORDER_COLOR)
        ));
        card.setLayout(new GridBagLayout());
        GridBagConstraints cgb = new GridBagConstraints();
        cgb.gridx = 0;
        cgb.fill = GridBagConstraints.HORIZONTAL;
        cgb.weightx = 1.0;
        cgb.insets = new Insets(0, 0, 0, 0);

        Student s = frame.getCurrentStudent();
        if (s == null) {
            JLabel msg = new JLabel("Please login to continue.");
            msg.setFont(AppFrame.BODY_FONT);
            cgb.gridy = 0;
            card.add(msg, cgb);
            center.add(card, cgb);
            return center;
        }

        // Title
        JLabel title = new JLabel("Your Dashboard");
        title.setFont(AppFrame.PAGE_TITLE);
        title.setForeground(AppFrame.TEXT);
        cgb.gridy = 0;
        cgb.insets = new Insets(0, 0, 16, 0);
        card.add(title, cgb);

        // Profile summary
        JLabel summaryLabel = new JLabel("Profile Summary");
        summaryLabel.setFont(AppFrame.SECTION_TITLE);
        summaryLabel.setForeground(AppFrame.PRIMARY);
        cgb.gridy = 1;
        cgb.insets = new Insets(0, 0, 8, 0);
        card.add(summaryLabel, cgb);

        // Summary grid
        JPanel summaryGrid = new JPanel();
        summaryGrid.setBackground(AppFrame.PRIMARY_LIGHT);
        summaryGrid.setBorder(new EmptyBorder(12, 16, 12, 16));
        summaryGrid.setLayout(new GridLayout(2, 4, 8, 8));

        summaryGrid.add(createStat("Student ID", String.valueOf(s.getStudentId())));
        summaryGrid.add(createStat("Name", s.getName()));
        summaryGrid.add(createStat("Degree", s.getDegree()));
        summaryGrid.add(createStat("CGPA", String.valueOf(s.getCgpa())));
        summaryGrid.add(createStat("Age", String.valueOf(s.getAge())));
        summaryGrid.add(createStat("Skills", s.getSkills()));
        summaryGrid.add(createStat("Location", s.getPreferredLocation()));
        summaryGrid.add(new JLabel());

        cgb.gridy = 2;
        cgb.insets = new Insets(0, 0, 24, 0);
        card.add(summaryGrid, cgb);

        // Action buttons
        JLabel actionLabel = new JLabel("Quick Actions");
        actionLabel.setFont(AppFrame.SECTION_TITLE);
        actionLabel.setForeground(AppFrame.TEXT);
        cgb.gridy = 3;
        cgb.insets = new Insets(0, 0, 10, 0);
        card.add(actionLabel, cgb);

        JPanel btnRow = new JPanel();
        btnRow.setBackground(AppFrame.SURFACE);
        btnRow.setLayout(new GridLayout(2, 2, 10, 10));

        JButton profileBtn = createActionBtn("View Profile", AppFrame.PRIMARY, e -> frame.showProfile());
        recoBtn = createActionBtn("View Recommendations", AppFrame.PRIMARY, e -> frame.showRecommendations());
        JButton internshipBtn = createActionBtn("Search & Filter", AppFrame.PRIMARY, e -> frame.showSearch());
        JButton logoutBtn = createActionBtn("Logout", new Color(220, 38, 38), e -> frame.logout());

        btnRow.add(profileBtn);
        btnRow.add(recoBtn);
        btnRow.add(internshipBtn);
        btnRow.add(logoutBtn);

        cgb.gridy = 4;
        cgb.insets = new Insets(0, 0, 0, 0);
        card.add(btnRow, cgb);

        cgb.gridx = 0;
        cgb.gridy = 0;
        center.add(card, cgb);
        return center;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel();
        footer.setBackground(AppFrame.BG);
        footer.setBorder(new EmptyBorder(8, 0, 8, 0));
        JLabel footerLabel = new JLabel("  PM Internship Scheme");
        footerLabel.setFont(AppFrame.SMALL_FONT);
        footerLabel.setForeground(AppFrame.TEXT_SEC);
        footer.add(footerLabel);
        return footer;
    }

    private JPanel createStat(String label, String value) {
        JPanel p = new JPanel();
        p.setBackground(AppFrame.PRIMARY_LIGHT);
        p.setBorder(new EmptyBorder(6, 8, 6, 8));
        p.setLayout(new GridLayout(2, 1));
        JLabel l = new JLabel(label);
        l.setFont(AppFrame.SMALL_FONT);
        l.setForeground(AppFrame.TEXT_SEC);
        l.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel v = new JLabel(value);
        v.setFont(new Font("SansSerif", Font.BOLD, 13));
        v.setForeground(AppFrame.PRIMARY);
        v.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(l);
        p.add(v);
        return p;
    }

    private JButton createActionBtn(String text, Color bg, ActionListener listener) {
        JButton btn = new JButton(text);
        if (bg.equals(new Color(220, 38, 38))) {
            AppFrame.styleDelete(btn);
        } else {
            AppFrame.stylePrimary(btn);
        }
        btn.addActionListener(listener);
        return btn;
    }

    public void setRecoButtonEnabled(boolean enabled) {
        if (recoBtn != null) {
            recoBtn.setEnabled(enabled);
        }
    }

    public void refresh() {
        removeAll();
        add(createHeader(), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
        revalidate();
        repaint();
    }
}
