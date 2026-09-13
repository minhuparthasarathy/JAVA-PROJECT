import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SearchPanel extends JPanel {

    private AppFrame frame;
    private JTextField companyField, roleField, locationField, skillsField, cgpaField;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private SearchFilter searchFilter;
    private JPanel resultsContainer;

    public SearchPanel(AppFrame frame) {
        this.frame = frame;
        setBackground(AppFrame.BG);
        setLayout(new BorderLayout(0, 0));
        add(createHeader(), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
        searchFilter = new SearchFilter(frame.getInternshipManager().getAllInternships());
        refreshTable(searchFilter.clearFilters());
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(AppFrame.PRIMARY);
        header.setPreferredSize(new Dimension(1000, 64));
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(0, 24, 0, 24));
        JLabel label = new JLabel("Search & Filter");
        label.setFont(AppFrame.PAGE_TITLE);
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(0, 0, 0, 16));
        header.add(label, BorderLayout.CENTER);
        return header;
    }

    private JPanel createContent() {
        JPanel center = new JPanel();
        center.setBackground(AppFrame.BG);
        center.setLayout(new BorderLayout(0, 12));
        center.setBorder(new EmptyBorder(16, 16, 16, 16));
        center.add(createFilterPanel(), BorderLayout.NORTH);
        center.add(createResultsPanel(), BorderLayout.CENTER);
        return center;
    }

    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(AppFrame.SURFACE);
        filterPanel.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(16, 16, 16, 16),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(AppFrame.BORDER_COLOR),
                "Search Criteria",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                AppFrame.TEXT
            )
        ));
        filterPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(4, 4, 4, 4);

        JLabel title = new JLabel("Search Internships");
        title.setFont(AppFrame.PAGE_TITLE);
        title.setForeground(AppFrame.TEXT);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 6, 0);
        filterPanel.add(title, gbc);

        JLabel desc = new JLabel("Search by company or role, filter by location, skills, and minimum CGPA.");
        desc.setFont(AppFrame.DESC_FONT);
        desc.setForeground(AppFrame.TEXT_SEC);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 16, 0);
        filterPanel.add(desc, gbc);

        gbc.gridwidth = 1;
        gbc = addField(filterPanel, gbc, "Company", companyField = new JTextField(25));
        gbc = addField(filterPanel, gbc, "Role", roleField = new JTextField(25));
        gbc = addField(filterPanel, gbc, "Location", locationField = new JTextField(25));
        gbc = addField(filterPanel, gbc, "Skills", skillsField = new JTextField(25));
        gbc = addField(filterPanel, gbc, "Min CGPA", cgpaField = new JTextField(12));

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(AppFrame.SURFACE);
        btnPanel.setLayout(new GridLayout(1, 3, 10, 0));

        JButton searchBtn = new JButton("Search");
        AppFrame.stylePrimary(searchBtn);
        searchBtn.addActionListener(e -> applyFilters());

        JButton clearBtn = new JButton("Clear");
        AppFrame.styleBack(clearBtn);
        clearBtn.addActionListener(e -> clearFilters());

        JButton backBtn = new JButton("Back");
        AppFrame.styleBack(backBtn);
        backBtn.addActionListener(e -> frame.showStudentDash());

        btnPanel.add(searchBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(backBtn);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(16, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        filterPanel.add(btnPanel, gbc);

        return filterPanel;
    }

    private JPanel createResultsPanel() {
        resultsContainer = new JPanel();
        resultsContainer.setBackground(AppFrame.BG);
        resultsContainer.setLayout(new BorderLayout(0, 8));

        JScrollPane scrollPane = createScrollPane();
        resultsContainer.add(scrollPane, BorderLayout.CENTER);
        return resultsContainer;
    }

    private JScrollPane createScrollPane() {
        tableModel = createTableModel();
        resultTable = new JTable(tableModel);
        resultTable.setFont(AppFrame.BODY_FONT);
        resultTable.setRowHeight(30);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        int[] widths = {50, 100, 100, 130, 80, 100, 80, 80};
        for (int i = 0; i < widths.length; i++) {
            resultTable.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(AppFrame.BORDER_COLOR),
            "Results",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 14),
            AppFrame.TEXT
        ));
        return scrollPane;
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

    private GridBagConstraints addField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(AppFrame.LABEL_FONT);
        label.setForeground(AppFrame.TEXT_SEC);
        label.setLabelFor(field);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.insets = new Insets(4, 4, 0, 4);
        panel.add(label, gbc);

        field.setFont(AppFrame.BODY_FONT);
        field.setMaximumSize(new Dimension(400, 36));

        gbc.gridx = 1;
        gbc.insets = new Insets(4, 0, 0, 4);
        panel.add(field, gbc);
        return gbc;
    }

    private DefaultTableModel createTableModel() {
        String[] columns = {"ID", "Company", "Role", "Required Skills", "Required CGPA", "Location", "Stipend", "Duration"};
        return new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
    }

    private void refreshTable(ArrayList<Internship> internships) {
        tableModel.setRowCount(0);
        if (internships.isEmpty()) {
            showNoResultsMessage();
        } else {
            if (resultsContainer.getComponentCount() == 0 || !(resultsContainer.getComponent(0) instanceof JScrollPane)) {
                resultsContainer.removeAll();
                resultsContainer.add(createScrollPane(), BorderLayout.CENTER);
            }
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
            resultsContainer.revalidate();
            resultsContainer.repaint();
        }
    }

    private void showNoResultsMessage() {
        resultsContainer.removeAll();
        JLabel msg = new JLabel("No matching internships found. Try adjusting your search criteria.", SwingConstants.CENTER);
        msg.setFont(AppFrame.BODY_FONT);
        msg.setForeground(AppFrame.TEXT_SEC);
        resultsContainer.add(msg, BorderLayout.CENTER);
        resultsContainer.revalidate();
        resultsContainer.repaint();
    }

    private void applyFilters() {
        String company = companyField.getText().trim();
        String role = roleField.getText().trim();
        String location = locationField.getText().trim();
        String skills = skillsField.getText().trim();
        double minCGPA = 0;
        try {
            String cgpaText = cgpaField.getText().trim();
            if (!cgpaText.isEmpty()) {
                minCGPA = Double.parseDouble(cgpaText);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for Min CGPA.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        ArrayList<Internship> results = searchFilter.applyFilters(company, role, location, skills, minCGPA);
        refreshTable(results);
    }

    private void clearFilters() {
        companyField.setText("");
        roleField.setText("");
        locationField.setText("");
        skillsField.setText("");
        cgpaField.setText("");
        ArrayList<Internship> all = searchFilter.clearFilters();
        refreshTable(all);
    }

    public void refresh() {
        searchFilter = new SearchFilter(frame.getInternshipManager().getAllInternships());
        companyField.setText("");
        roleField.setText("");
        locationField.setText("");
        skillsField.setText("");
        cgpaField.setText("");
        refreshTable(searchFilter.clearFilters());
    }
}
