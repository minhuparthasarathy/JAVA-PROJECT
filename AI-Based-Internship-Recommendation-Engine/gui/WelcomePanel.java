import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

/**
 * Swing GUI - Welcome/Landing Panel
 * BorderLayout - outer layout
 * GridBagLayout - button layout with proper gaps
 * Encapsulation - uses AppFrame reference
 */
public class WelcomePanel extends JPanel {

    private AppFrame frame;

    public WelcomePanel(AppFrame frame) {
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
        header.setPreferredSize(new Dimension(1000, 100));
        header.setLayout(new GridBagLayout());
        header.setBorder(new EmptyBorder(16, 24, 16, 24));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 4, 0);

        JLabel title = new JLabel("AI-Based Internship Recommendation Engine");
        title.setFont(AppFrame.APP_TITLE);
        title.setForeground(Color.WHITE);
        header.add(title, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        JLabel subtitle = new JLabel("PM Internship Scheme");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitle.setForeground(new Color(191, 210, 254));
        header.add(subtitle, gbc);

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
        gbc.insets = new Insets(20, 0, 20, 0);

        JPanel card = new JPanel();
        card.setBackground(AppFrame.SURFACE);
        card.setBorder(new CompoundBorder(
            new EmptyBorder(32, 40, 32, 40),
            new LineBorder(AppFrame.BORDER_COLOR)
        ));
        card.setLayout(new GridBagLayout());
        GridBagConstraints cgb = new GridBagConstraints();
        cgb.gridx = 0;
        cgb.fill = GridBagConstraints.HORIZONTAL;
        cgb.insets = new Insets(0, 0, 0, 0);
        cgb.weightx = 1.0;

        // Title
        JLabel title = new JLabel("Welcome to PM Internship Portal");
        title.setFont(AppFrame.PAGE_TITLE);
        title.setForeground(AppFrame.TEXT);
        cgb.gridy = 0;
        cgb.insets = new Insets(0, 0, 8, 0);
        card.add(title, cgb);

        // Description
        JLabel desc = new JLabel("<html>Find the right internship based on your skills, CGPA, and preferences.<br>Register as a student or login as an admin to get started.</html>");
        desc.setFont(AppFrame.DESC_FONT);
        desc.setForeground(AppFrame.TEXT_SEC);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        desc.setVerticalAlignment(SwingConstants.TOP);
        cgb.gridy = 1;
        cgb.insets = new Insets(0, 0, 24, 0);
        card.add(desc, cgb);

        // Buttons with proper spacing
        cgb.gridy = 2;
        cgb.insets = new Insets(0, 0, 10, 0);
        cgb.fill = GridBagConstraints.NONE;
        cgb.anchor = GridBagConstraints.CENTER;

        JButton studentLoginBtn = createButton("Student Login", AppFrame.PRIMARY, e -> frame.showStudentLogin());
        card.add(studentLoginBtn, cgb);

        cgb.gridy = 3;
        cgb.insets = new Insets(0, 0, 10, 0);
        card.add(studentLoginBtn, cgb); // placeholder, will replace

        // Actually let me fix this - use proper gridy values
        // Reset and redo buttons properly
        card.removeAll();

        // Redo: title
        cgb.gridy = 0;
        cgb.insets = new Insets(0, 0, 8, 0);
        cgb.fill = GridBagConstraints.HORIZONTAL;
        card.add(title, cgb);

        // Description
        cgb.gridy = 1;
        cgb.insets = new Insets(0, 0, 24, 0);
        card.add(desc, cgb);

        // Buttons - using a sub-panel for proper spacing
        JPanel btnSubPanel = new JPanel();
        btnSubPanel.setBackground(AppFrame.SURFACE);
        btnSubPanel.setLayout(new GridLayout(0, 1, 0, 10));
        btnSubPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton sLogin = createButton("Student Login", AppFrame.PRIMARY, e -> frame.showStudentLogin());
        JButton sReg = createButton("Student Registration", AppFrame.PRIMARY, e -> frame.showStudentReg());
        JButton aLogin = createButton("Admin Login", AppFrame.PRIMARY_DARK, e -> frame.showAdminLogin());

        // Set preferred sizes for buttons
        Dimension btnSize = new Dimension(300, 44);
        sLogin.setPreferredSize(btnSize);
        sReg.setPreferredSize(btnSize);
        aLogin.setPreferredSize(btnSize);
        sLogin.setMaximumSize(btnSize);
        sReg.setMaximumSize(btnSize);
        aLogin.setMaximumSize(btnSize);

        btnSubPanel.add(sLogin);
        btnSubPanel.add(sReg);
        btnSubPanel.add(aLogin);

        cgb.gridy = 2;
        cgb.insets = new Insets(0, 0, 0, 0);
        cgb.fill = GridBagConstraints.HORIZONTAL;
        card.add(btnSubPanel, cgb);

        cgb.gridx = 0;
        cgb.gridy = 0;
        center.add(card, cgb);
        return center;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel();
        footer.setBackground(AppFrame.BG);
        footer.setBorder(new EmptyBorder(8, 0, 8, 0));
        JLabel footerLabel = new JLabel("  \u00a9 2026 PM Internship Scheme. All rights reserved.");
        footerLabel.setFont(AppFrame.SMALL_FONT);
        footerLabel.setForeground(AppFrame.TEXT_SEC);
        footer.add(footerLabel);
        return footer;
    }

    private JButton createButton(String text, Color bg, ActionListener listener) {
        JButton btn = new JButton(text);
        AppFrame.stylePrimary(btn);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.addActionListener(listener);
        return btn;
    }
}
