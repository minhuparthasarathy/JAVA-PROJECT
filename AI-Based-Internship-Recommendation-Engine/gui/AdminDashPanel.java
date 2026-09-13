import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

/**
 * Swing GUI - Admin Dashboard Panel
 * BorderLayout - outer layout
 * GridBagLayout - content layout
 * Encapsulation - accesses AppFrame and InternshipManager
 */
public class AdminDashPanel extends JPanel {

    private AppFrame frame;

    public AdminDashPanel(AppFrame frame) {
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

        JPanel left = new JPanel();
        left.setBackground(AppFrame.PRIMARY_DARK);
        left.setLayout(new GridLayout(2, 1));
        left.setBorder(new EmptyBorder(4, 0, 4, 0));

        JLabel name = new JLabel("Admin Dashboard");
        name.setFont(AppFrame.PAGE_TITLE);
        name.setForeground(Color.WHITE);
        left.add(name);

        JLabel sub = new JLabel("Manage internship postings and oversee the platform");
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

        // Title
        JLabel title = new JLabel("Manage Internships");
        title.setFont(AppFrame.PAGE_TITLE);
        title.setForeground(AppFrame.TEXT);
        cgb.gridy = 0;
        cgb.insets = new Insets(0, 0, 6, 0);
        card.add(title, cgb);

        // Description
        JLabel desc = new JLabel("Post and manage internship opportunities for students.");
        desc.setFont(AppFrame.DESC_FONT);
        desc.setForeground(AppFrame.TEXT_SEC);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        cgb.gridy = 1;
        cgb.insets = new Insets(0, 0, 20, 0);
        card.add(desc, cgb);

        // Stats
        JLabel statsLabel = new JLabel("Total Internships: " + frame.getInternshipManager().getAllInternships().size());
        statsLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        statsLabel.setForeground(AppFrame.PRIMARY);
        cgb.gridy = 2;
        cgb.insets = new Insets(0, 0, 24, 0);
        card.add(statsLabel, cgb);

        // Action buttons
        JLabel actionLabel = new JLabel("Actions");
        actionLabel.setFont(AppFrame.SECTION_TITLE);
        actionLabel.setForeground(AppFrame.TEXT);
        cgb.gridy = 3;
        cgb.insets = new Insets(0, 0, 10, 0);
        card.add(actionLabel, cgb);

        JPanel btnRow = new JPanel();
        btnRow.setBackground(AppFrame.SURFACE);
        btnRow.setLayout(new GridLayout(1, 2, 10, 10));

        JButton addBtn = createActionBtn("Add Internship", AppFrame.PRIMARY, e -> frame.showInternshipMgmt());
        JButton viewBtn = createActionBtn("View All Internships", AppFrame.PRIMARY, e -> frame.showInternshipMgmt());
        JButton logoutBtn = createActionBtn("Logout", new Color(220, 38, 38), e -> frame.logout());

        btnRow.add(addBtn);
        btnRow.add(viewBtn);
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
}
