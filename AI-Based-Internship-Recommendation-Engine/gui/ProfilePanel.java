import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

/**
 * Swing GUI - Profile Panel
 * BorderLayout - outer layout
 * GridBagLayout - form layout
 * Encapsulation - Student getters/setters
 */
public class ProfilePanel extends JPanel {

    private AppFrame frame;
    private JTextField skillsField, interestsField, locationField;

    public ProfilePanel(AppFrame frame) {
        this.frame = frame;
        setBackground(AppFrame.BG);
        setLayout(new BorderLayout(0, 0));

        add(createHeader("My Profile"), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
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
        JPanel center = new JPanel();
        center.setBackground(AppFrame.BG);
        center.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 24, 20, 24);
        gbc.weightx = 1.0;

        Student s = frame.getCurrentStudent();
        if (s == null) {
            JLabel msg = new JLabel("Please login to view profile.");
            msg.setFont(AppFrame.BODY_FONT);
            center.add(msg);
            return center;
        }

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

        // Section 1: View Profile
        JLabel viewTitle = new JLabel("View Profile");
        viewTitle.setFont(AppFrame.PAGE_TITLE);
        viewTitle.setForeground(AppFrame.TEXT);
        cgb.gridy = 0;
        cgb.insets = new Insets(0, 0, 16, 0);
        card.add(viewTitle, cgb);

        cgb = addLabelValue(cgb, card, "Student ID", String.valueOf(s.getStudentId()));
        cgb = addLabelValue(cgb, card, "Name", s.getName());
        cgb = addLabelValue(cgb, card, "Age", String.valueOf(s.getAge()));
        cgb = addLabelValue(cgb, card, "Degree", s.getDegree());
        cgb = addLabelValue(cgb, card, "CGPA", String.valueOf(s.getCgpa()));

        // Section 2: Edit Profile
        JLabel editTitle = new JLabel("Edit Profile");
        editTitle.setFont(AppFrame.SECTION_TITLE);
        editTitle.setForeground(AppFrame.PRIMARY);
        cgb.gridy++;
        cgb.insets = new Insets(24, 0, 8, 0);
        card.add(editTitle, cgb);

        JLabel editDesc = new JLabel("Update your skills, interests, and preferred location.");
        editDesc.setFont(AppFrame.SMALL_FONT);
        editDesc.setForeground(AppFrame.TEXT_SEC);
        cgb.gridy++;
        cgb.insets = new Insets(0, 0, 16, 0);
        card.add(editDesc, cgb);

        skillsField = new JTextField(25);
        skillsField.setText(s.getSkills());
        skillsField.setMaximumSize(new Dimension(400, 36));
        cgb = addEditField(cgb, card, "Skills", skillsField);

        interestsField = new JTextField(25);
        interestsField.setText(s.getInterests());
        interestsField.setMaximumSize(new Dimension(400, 36));
        cgb = addEditField(cgb, card, "Interests", interestsField);

        locationField = new JTextField(25);
        locationField.setText(s.getPreferredLocation());
        locationField.setMaximumSize(new Dimension(400, 36));
        cgb = addEditField(cgb, card, "Preferred Location", locationField);

        // Buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(AppFrame.SURFACE);
        btnPanel.setLayout(new GridLayout(1, 2, 10, 0));
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton saveBtn = new JButton("Save Changes");
        AppFrame.stylePrimary(saveBtn);
        saveBtn.addActionListener(e -> doSave());

        JButton logoutBtn = new JButton("Logout");
        AppFrame.styleDelete(logoutBtn);
        logoutBtn.addActionListener(e -> frame.logout());

        btnPanel.add(saveBtn);
        btnPanel.add(logoutBtn);

        cgb.gridy++;
        cgb.insets = new Insets(24, 0, 0, 0);
        card.add(btnPanel, cgb);

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

    /**
     * Encapsulation - displays a labeled data field
     */
    private GridBagConstraints addLabelValue(GridBagConstraints gbc, JPanel card, String label, String value) {
        JLabel l = new JLabel(label + ":");
        l.setFont(AppFrame.LABEL_FONT);
        l.setForeground(AppFrame.TEXT_SEC);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 4, 0);
        card.add(l, gbc);

        JLabel v = new JLabel(value);
        v.setFont(AppFrame.BODY_FONT);
        v.setForeground(AppFrame.TEXT);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 16, 0);
        card.add(v, gbc);
        return gbc;
    }

    /**
     * Encapsulation - editable field with label
     */
    private GridBagConstraints addEditField(GridBagConstraints gbc, JPanel card, String label, JTextField field) {
        JLabel l = new JLabel(label);
        l.setFont(AppFrame.LABEL_FONT);
        l.setForeground(AppFrame.TEXT_SEC);
        l.setLabelFor(field);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 4, 0);
        card.add(l, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 16, 0);
        card.add(field, gbc);
        return gbc;
    }

    /**
     * Encapsulation - uses Student setter methods
     */
    private void doSave() {
        Student s = frame.getCurrentStudent();
        if (s == null) {
            JOptionPane.showMessageDialog(this, "Please login first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String skills = skillsField.getText().trim();
        String interests = interestsField.getText().trim();
        String location = locationField.getText().trim();

        if (skills.isEmpty() || interests.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All editable fields are required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        s.setSkills(skills);
        s.setInterests(interests);
        s.setPreferredLocation(location);
        frame.showProfile();
        JOptionPane.showMessageDialog(this, "Profile Updated Successfully!",
            "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
