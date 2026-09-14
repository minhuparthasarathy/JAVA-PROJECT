import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Swing GUI - Student Registration Panel
 * BorderLayout - outer layout
 * GridBagLayout - form layout
 * Object Creation - creates Student objects
 * ArrayList - stores students via UserManager
 * Exception Handling - validates all input fields
 */
public class StudentRegPanel extends JPanel {

    private AppFrame frame;
    private JTextField idField, nameField, ageField, degreeField, cgpaField, skillsField, interestsField, locationField;
    private JPasswordField passField;
    private JTextArea skillsArea, interestsArea;

    public StudentRegPanel(AppFrame frame) {
        this.frame = frame;
        setBackground(AppFrame.BG);
        setLayout(new BorderLayout(0, 0));

        add(createHeader("Student Registration"), BorderLayout.NORTH);
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

    private JComponent createContent() {
        JPanel center = new JPanel();
        center.setBackground(AppFrame.BG);
        center.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(24, 24, 24, 24);
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

        // Title
        JLabel title = new JLabel("Student Registration");
        title.setFont(AppFrame.PAGE_TITLE);
        title.setForeground(AppFrame.TEXT);
        cgb.gridy = 0;
        cgb.insets = new Insets(0, 0, 6, 0);
        card.add(title, cgb);

        // Description
        JLabel desc = new JLabel("Create your student profile to receive personalized internship recommendations.");
        desc.setFont(AppFrame.DESC_FONT);
        desc.setForeground(AppFrame.TEXT_SEC);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        desc.setVerticalAlignment(SwingConstants.TOP);
        cgb.gridy = 1;
        cgb.insets = new Insets(0, 0, 20, 0);
        card.add(desc, cgb);

        // Form fields
        cgb = addField(card, cgb, "Student ID", idField = new JTextField(25));
        cgb = addField(card, cgb, "Name", nameField = new JTextField(25));
        cgb = addField(card, cgb, "Age", ageField = new JTextField(25));
        cgb = addField(card, cgb, "Degree", degreeField = new JTextField(25));
        cgb = addField(card, cgb, "CGPA", cgpaField = new JTextField(25));

        // Skills - JTextArea for multiline
        cgb = addTextAreaField(card, cgb, "Skills", skillsArea = new JTextArea(3, 25));

        // Interests - JTextArea for multiline
        cgb = addTextAreaField(card, cgb, "Interests", interestsArea = new JTextArea(3, 25));

        cgb = addField(card, cgb, "Preferred Location", locationField = new JTextField(25));
        cgb = addField(card, cgb, "Password", passField = new JPasswordField(25));

        // Buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(AppFrame.SURFACE);
        btnPanel.setLayout(new GridLayout(1, 2, 10, 0));
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton registerBtn = new JButton("Register");
        AppFrame.stylePrimary(registerBtn);
        registerBtn.addActionListener(e -> doRegister());

        JButton backBtn = new JButton("Back");
        AppFrame.styleBack(backBtn);
        backBtn.addActionListener(e -> frame.showWelcome());

        btnPanel.add(registerBtn);
        btnPanel.add(backBtn);

        cgb.gridy++;
        cgb.insets = new Insets(20, 0, 0, 0);
        card.add(btnPanel, cgb);

        cgb.gridx = 0;
        cgb.gridy = 0;
        center.add(card, cgb);

        JScrollPane scroll = new JScrollPane(center);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    private GridBagConstraints addField(JPanel card, GridBagConstraints gbc, String labelText, JTextField field) {
        field.setFont(AppFrame.BODY_FONT);
        field.setMaximumSize(new Dimension(400, 36));

        JLabel label = new JLabel(labelText);
        label.setFont(AppFrame.LABEL_FONT);
        label.setForeground(AppFrame.TEXT_SEC);
        label.setLabelFor(field);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 4, 0);
        card.add(label, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 16, 0);
        card.add(field, gbc);
        return gbc;
    }

    /**
     * JTextArea for long text fields with scroll
     */
    private GridBagConstraints addTextAreaField(JPanel card, GridBagConstraints gbc, String labelText, JTextArea area) {
        JLabel label = new JLabel(labelText);
        label.setFont(AppFrame.LABEL_FONT);
        label.setForeground(AppFrame.TEXT_SEC);
        label.setLabelFor(area);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 4, 0);
        card.add(label, gbc);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setMaximumSize(new Dimension(400, 100));
        scroll.setPreferredSize(new Dimension(400, 72));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 16, 0);
        card.add(scroll, gbc);
        return gbc;
    }

    /**
     * JTextArea for long text fields with scroll
     */
    private void doRegister() {
        String idText = idField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student ID is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Student ID must be a number.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String name = nameField.getText().trim();
        if (name.isEmpty() || !name.matches(".*[a-zA-Z].*")) {
            JOptionPane.showMessageDialog(this, "Name is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String ageText = ageField.getText().trim();
        if (ageText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Age is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a valid number.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String degree = degreeField.getText().trim();
        if (degree.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Degree is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cgpaText = cgpaField.getText().trim();
        if (cgpaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "CGPA is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double cgpa;
        try {
            cgpa = Double.parseDouble(cgpaText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "CGPA must be a number.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String skills = skillsArea.getText().trim();
        if (skills.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Skills are required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String interests = interestsArea.getText().trim();
        if (interests.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Interests are required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String location = locationField.getText().trim();
        if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preferred location is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String password = new String(passField.getPassword()).trim();
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (age < 16 || age > 60) {
            JOptionPane.showMessageDialog(this, "Age must be between 16 and 60.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cgpa < 0 || cgpa > 10) {
            JOptionPane.showMessageDialog(this, "CGPA must be between 0 and 10.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Check for duplicate Student ID
        for (int i = 0; i < frame.getUserManager().count; i++) {
            if (frame.getUserManager().students[i].getStudentId() == id) {
                JOptionPane.showMessageDialog(this, "Student ID already exists.", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        Student student = new Student(id, name, age, degree, cgpa, skills, interests, location, password);
        UserManager um = frame.getUserManager();
        um.students[um.count++] = student;
        StudentStorage.saveStudent(student);

        JOptionPane.showMessageDialog(this, "Registration Successful! Please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
        frame.showStudentLogin();
    }
}
