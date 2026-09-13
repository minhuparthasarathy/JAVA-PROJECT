import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Swing GUI - Internship Management Panel
 * BorderLayout - outer layout
 * Encapsulation - accesses AppFrame and InternshipManager
 * ArrayList - stores internships
 * File I/O - references internships.txt
 */
public class InternshipMgmtPanel extends JPanel {

    private AppFrame frame;
    private JTable internshipTable;
    private DefaultTableModel tableModel;
    private JTextField companyField, roleField, skillsField, cgpaField, locationField, stipendField, durationField;

    public InternshipMgmtPanel(AppFrame frame) {
        this.frame = frame;
        setBackground(AppFrame.BG);
        setLayout(new BorderLayout(0, 0));

        add(createHeader(), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(AppFrame.PRIMARY_DARK);
        header.setPreferredSize(new Dimension(1000, 64));
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(0, 24, 0, 24));

        JLabel label = new JLabel("Internship Management");
        label.setFont(AppFrame.PAGE_TITLE);
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(0, 0, 0, 16));
        header.add(label, BorderLayout.CENTER);

        JButton logoutBtn = new JButton("Logout");
        AppFrame.styleDelete(logoutBtn);
        logoutBtn.addActionListener(e -> frame.logout());

        JPanel right = new JPanel();
        right.setBackground(AppFrame.PRIMARY_DARK);
        right.add(logoutBtn);
        header.add(right, BorderLayout.EAST);
        return header;
    }

    private JPanel createContent() {
        JPanel center = new JPanel();
        center.setBackground(AppFrame.BG);
        center.setLayout(new BorderLayout(0, 12));
        center.setBorder(new EmptyBorder(16, 16, 16, 16));

        // Table section
        tableModel = createTableModel();
        internshipTable = new JTable(tableModel);
        internshipTable.setFont(AppFrame.BODY_FONT);
        internshipTable.setRowHeight(30);
        internshipTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Column widths
        int[] widths = {50, 100, 100, 130, 80, 100, 80, 80};
        for (int i = 0; i < widths.length; i++) {
            internshipTable.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        JScrollPane scrollPane = new JScrollPane(internshipTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(AppFrame.BORDER_COLOR),
            "All Internships",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 14),
            AppFrame.TEXT
        ));
        center.add(scrollPane, BorderLayout.NORTH);

        // Form section
        JPanel formPanel = createFormPanel();
        center.add(formPanel, BorderLayout.CENTER);

        refreshTable();
        return center;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setBackground(AppFrame.SURFACE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(16, 16, 16, 16),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(AppFrame.BORDER_COLOR),
                "Add / Update Internship",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                AppFrame.TEXT
            )
        ));
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(4, 4, 4, 4);

        // Create fields with labels using 2-column grid
        // Row 0: Company + Role
        addFormPair(formPanel, gbc, "Company", companyField = new JTextField(25));
        addFormPair(formPanel, gbc, "Role", roleField = new JTextField(25));

        // Row 1: Required Skills + Required CGPA
        addFormPair(formPanel, gbc, "Required Skills", skillsField = new JTextField(25));
        addFormPair(formPanel, gbc, "Required CGPA", cgpaField = new JTextField(12));

        // Row 2: Location + Stipend
        addFormPair(formPanel, gbc, "Location", locationField = new JTextField(20));
        addFormPair(formPanel, gbc, "Stipend ($)", stipendField = new JTextField(12));

        // Row 3: Duration (full width)
        addFormFull(formPanel, gbc, "Duration (months)", durationField = new JTextField(12));

        // Buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(AppFrame.SURFACE);
        btnPanel.setLayout(new GridLayout(1, 3, 10, 0));

        JButton addBtn = new JButton("Add Internship");
        AppFrame.stylePrimary(addBtn);
        addBtn.addActionListener(e -> doAdd());

        JButton updateBtn = new JButton("Update Selected");
        AppFrame.stylePrimary(updateBtn);
        updateBtn.addActionListener(e -> doUpdate());

        JButton deleteBtn = new JButton("Delete Selected");
        AppFrame.styleDelete(deleteBtn);
        deleteBtn.addActionListener(e -> doDelete());

        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);

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

    /**
     * Adds a label + field pair in 2-column grid layout
     */
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

    private void styleBtn(JButton btn, Color bg) {
        if (bg.equals(new Color(220, 38, 38))) {
            AppFrame.styleDelete(btn);
        } else {
            AppFrame.stylePrimary(btn);
        }
    }

    private DefaultTableModel createTableModel() {
        String[] columns = {"ID", "Company", "Role", "Required Skills", "Required CGPA", "Location", "Stipend", "Duration"};
        return new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        ArrayList<Internship> internships = frame.getInternshipManager().getAllInternships();
        for (Internship intern : internships) {
            tableModel.addRow(new Object[]{
                intern.getId(),
                intern.getCompany(),
                intern.getRole(),
                intern.getRequiredSkills(),
                intern.getRequiredCGPA(),
                intern.getLocation(),
                String.format("$%,.0f", intern.getStipend()),
                intern.getDuration() + " mo"
            });
        }
    }

    /**
     * ArrayList - adds new Internship via InternshipManager
     * Exception Handling - validates input
     */
    private void doAdd() {
        try {
            String company = companyField.getText().trim();
            String role = roleField.getText().trim();
            String skills = skillsField.getText().trim();
            double cgpa = Double.parseDouble(cgpaField.getText().trim());
            String location = locationField.getText().trim();
            double stipend = Double.parseDouble(stipendField.getText().trim());
            int duration = Integer.parseInt(durationField.getText().trim());

            if (company.isEmpty() || role.isEmpty() || skills.isEmpty() || location.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Internship newIntern = new Internship(
                frame.getInternshipManager().getAllInternships().size() + 1,
                company, role, skills, cgpa, location, stipend, duration
            );
            frame.getInternshipManager().addInternship(newIntern);
            refreshTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Internship Added Successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for CGPA, Stipend, and Duration.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * ArrayList - updates existing internship
     * Exception Handling - validates selection and input
     */
    private void doUpdate() {
        int selectedRow = internshipTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an internship to update.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String company = companyField.getText().trim();
            String role = roleField.getText().trim();
            String skills = skillsField.getText().trim();
            double cgpa = Double.parseDouble(cgpaField.getText().trim());
            String location = locationField.getText().trim();
            double stipend = Double.parseDouble(stipendField.getText().trim());
            int duration = Integer.parseInt(durationField.getText().trim());

            if (company.isEmpty() || role.isEmpty() || skills.isEmpty() || location.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = (int) internshipTable.getValueAt(selectedRow, 0);
            Internship updated = new Internship(id, company, role, skills, cgpa, location, stipend, duration);
            frame.getInternshipManager().updateInternship(id, updated);
            refreshTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Internship Updated Successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for CGPA, Stipend, and Duration.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * ArrayList - deletes internship with confirmation
     * Exception Handling - validates selection
     */
    private void doDelete() {
        int selectedRow = internshipTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an internship to delete.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) internshipTable.getValueAt(selectedRow, 0);
        String company = (String) internshipTable.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete " + company + " (ID: " + id + ")?",
            "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            frame.getInternshipManager().deleteInternship(id);
            refreshTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Internship Deleted Successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearForm() {
        companyField.setText("");
        roleField.setText("");
        skillsField.setText("");
        cgpaField.setText("");
        locationField.setText("");
        stipendField.setText("");
        durationField.setText("");
    }
}
