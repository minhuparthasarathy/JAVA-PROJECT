import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ViewInternshipsPanel extends JPanel {

    private AppFrame frame;
    private JTable internshipTable;
    private DefaultTableModel tableModel;

    public ViewInternshipsPanel(AppFrame frame) {
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

        JLabel label = new JLabel("View Internships");
        label.setFont(AppFrame.PAGE_TITLE);
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(0, 0, 0, 16));
        header.add(label, BorderLayout.CENTER);

        JButton backBtn = new JButton("Back to Dashboard");
        AppFrame.styleBack(backBtn);
        backBtn.setMaximumSize(new Dimension(160, 34));
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

        tableModel = createTableModel();
        internshipTable = new JTable(tableModel);
        internshipTable.setFont(AppFrame.BODY_FONT);
        internshipTable.setRowHeight(30);
        internshipTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        int[] widths = {50, 100, 100, 130, 80, 100, 80, 80};
        for (int i = 0; i < widths.length; i++) {
            internshipTable.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }
        internshipTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(internshipTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(AppFrame.BORDER_COLOR),
            "All Internships",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 14),
            AppFrame.TEXT
        ));
        center.add(scrollPane, BorderLayout.CENTER);

        refreshTable();
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

    public void refresh() {
        refreshTable();
    }
}
