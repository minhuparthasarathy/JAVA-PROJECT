import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UpdateInternshipPanel extends JPanel {

    private AppFrame frame;
    private JTextField idField, companyField, roleField, skillsField, cgpaField, locationField, stipendField, durationField;

    public UpdateInternshipPanel(AppFrame frame) {
        this.frame = frame;
        setBackground(AppFrame.BG);
        setLayout(new BorderLayout(0, 0));

        add(createHeader("Update Internship"), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createHeader(String title) {
        JPanel header = new JPanel();
        header.setBackground(AppFrame.PRIMARY_DARK);
        header.setPreferredSize(new Dimension(1000, 64));
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(0, 24, 0, 24));

        JLabel label = new JLabel(title);
        label.setFont(AppFrame.PAGE_TITLE);
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(0, 0, 0, 16));
        header.add(label, BorderLayout.CENTER);

        JButton backBtn = new JButton("Back to Dashboard");
        AppFrame.styleBack(backBtn);
        backBtn.addActionListener(e -> frame.showAdminDash());

        JPanel right = new JPanel();
        right.setBackground(AppFrame.PRIMARY_DARK);
        right.add(backBtn);
        header.add(right, BorderLayout.EAST);
        return header;
    }

    private JPanel createContent() {
        JPanel center = new JPanel();
        center.setBackground(AppFrame.BG);
        center.setLayout(new BorderLayout(0, 12));
        center.setBorder(new EmptyBorder(16, 16, 16, 16));

        JPanel formPanel = createFormPanel();
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(AppFrame.BORDER_COLOR),
            "Update Internship Details",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 14),
            AppFrame.TEXT
        ));
        center.add(scrollPane, BorderLayout.CENTER);

        return center;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setBackground(AppFrame.SURFACE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(4, 4, 4, 4);

        idField = new JTextField(25);
        idField.setMaximumSize(new Dimension(500, 36));
        addFormPair(formPanel, gbc, "Internship ID", idField);

        companyField = new JTextField(25);
        companyField.setMaximumSize(new Dimension(500, 36));
        addFormPair(formPanel, gbc, "Company", companyField);

        roleField = new JTextField(25);
        roleField.setMaximumSize(new Dimension(500, 36));
        addFormPair(formPanel, gbc, "Role", roleField);

        skillsField = new JTextField(25);
        skillsField.setMaximumSize(new Dimension(500, 36));
        addFormPair(formPanel, gbc, "Required Skills", skillsField);

        cgpaField = new JTextField(12);
        cgpaField.setMaximumSize(new Dimension(500, 36));
        addFormPair(formPanel, gbc, "Required CGPA", cgpaField);

        locationField = new JTextField(20);
        locationField.setMaximumSize(new Dimension(500, 36));
        addFormPair(formPanel, gbc, "Location", locationField);

        stipendField = new JTextField(12);
        stipendField.setMaximumSize(new Dimension(500, 36));
        addFormPair(formPanel, gbc, "Stipend ($)", stipendField);

        durationField = new JTextField(12);
        durationField.setMaximumSize(new Dimension(500, 36));
        addFormFull(formPanel, gbc, "Duration", durationField);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(AppFrame.SURFACE);
        btnPanel.setLayout(new GridLayout(1, 3, 10, 0));

        JButton loadBtn = new JButton("Load");
        AppFrame.stylePrimary(loadBtn);
        loadBtn.addActionListener(e -> doLoad());

        JButton updateBtn = new JButton("Update");
        AppFrame.stylePrimary(updateBtn);
        updateBtn.addActionListener(e -> doUpdate());

        JButton clearBtn = new JButton("Clear");
        AppFrame.styleBack(clearBtn);
        clearBtn.addActionListener(e -> clearForm());

        btnPanel.add(loadBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(clearBtn);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(16, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnPanel, gbc);

        return formPanel;
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

    private void addFormPair(JPanel panel, GridBagConstraints gbc, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(AppFrame.LABEL_FONT);
        label.setForeground(AppFrame.TEXT_SEC);
        label.setLabelFor(field);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.insets = new Insets(4, 4, 0, 4);
        panel.add(label, gbc);

        field.setFont(AppFrame.BODY_FONT);
        field.setMaximumSize(new Dimension(500, 36));

        gbc.gridx = 1;
        gbc.insets = new Insets(4, 0, 0, 4);
        panel.add(field, gbc);
    }

    private void addFormFull(JPanel panel, GridBagConstraints gbc, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(AppFrame.LABEL_FONT);
        label.setForeground(AppFrame.TEXT_SEC);
        label.setLabelFor(field);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(4, 4, 0, 4);
        panel.add(label, gbc);

        field.setFont(AppFrame.BODY_FONT);
        field.setMaximumSize(new Dimension(500, 36));

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(4, 4, 12, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);
    }

    private void doLoad() {
        clearForm();
        String idText = idField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Internship ID is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Internship ID must be a number.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ArrayList<Internship> internships = frame.getInternshipManager().getAllInternships();
        for (Internship intern : internships) {
            if (intern.getId() == id) {
                companyField.setText(intern.getCompany());
                roleField.setText(intern.getRole());
                skillsField.setText(intern.getRequiredSkills());
                cgpaField.setText(String.valueOf(intern.getRequiredCGPA()));
                locationField.setText(intern.getLocation());
                stipendField.setText(String.valueOf(intern.getStipend()));
                durationField.setText(intern.getDuration());
                JOptionPane.showMessageDialog(this, "Internship loaded successfully.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Internship not found with ID: " + id,
            "Not Found", JOptionPane.WARNING_MESSAGE);
    }

    private void doUpdate() {
        String idText = idField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Internship ID is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Internship ID must be a number.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String company = companyField.getText().trim();
        String role = roleField.getText().trim();
        String skills = skillsField.getText().trim();
        String durationStr = durationField.getText().trim();

        if (company.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Company is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Role is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (skills.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Required Skills are required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (durationStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Duration is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double cgpa;
        try {
            cgpa = Double.parseDouble(cgpaField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Required CGPA is invalid.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String location = locationField.getText().trim();
        if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Location is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double stipend;
        try {
            stipend = Double.parseDouble(stipendField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stipend is invalid.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (stipend < 0) {
            JOptionPane.showMessageDialog(this, "Stipend cannot be negative.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ArrayList<Internship> internships = frame.getInternshipManager().getAllInternships();
        boolean found = false;
        for (int i = 0; i < internships.size(); i++) {
            if (internships.get(i).getId() == id) {
                Internship updated = new Internship(id, company, role, skills, cgpa, location, stipend, durationStr);
                internships.set(i, updated);
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Internship not found with ID: " + id,
                "Not Found", JOptionPane.WARNING_MESSAGE);
            return;
        }

        frame.getInternshipManager().saveInternshipsToFile();
        clearForm();
        JOptionPane.showMessageDialog(this, "Internship Updated Successfully!",
            "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearForm() {
        idField.setText("");
        companyField.setText("");
        roleField.setText("");
        skillsField.setText("");
        cgpaField.setText("");
        locationField.setText("");
        stipendField.setText("");
        durationField.setText("");
    }

    public void refresh() {
        clearForm();
    }
}
