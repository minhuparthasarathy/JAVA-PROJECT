import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

/**
 * Swing GUI - Admin Login Panel
 * BorderLayout - outer layout
 * GridBagLayout - form layout
 * Interface implementation - ActionListener
 */
public class AdminLoginPanel extends JPanel {

    private AppFrame frame;
    private JTextField userField;
    private JPasswordField passField;

    public AdminLoginPanel(AppFrame frame) {
        this.frame = frame;
        setBackground(AppFrame.BG);
        setLayout(new BorderLayout(0, 0));

        add(createHeader("Admin Login"), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
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

        JLabel title = new JLabel("Admin Sign In");
        title.setFont(AppFrame.PAGE_TITLE);
        title.setForeground(AppFrame.TEXT);
        cgb.gridy = 0;
        cgb.insets = new Insets(0, 0, 6, 0);
        card.add(title, cgb);

        JLabel desc = new JLabel("Sign in to manage internship postings.");
        desc.setFont(AppFrame.DESC_FONT);
        desc.setForeground(AppFrame.TEXT_SEC);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        cgb.gridy = 1;
        cgb.insets = new Insets(0, 0, 20, 0);
        card.add(desc, cgb);

        cgb = addField(card, cgb, "Username", userField = new JTextField(25));
        cgb = addField(card, cgb, "Password", passField = new JPasswordField(25));

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(AppFrame.SURFACE);
        btnPanel.setLayout(new GridLayout(1, 2, 10, 0));
        btnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPanel.setMaximumSize(new Dimension(400, 80));

        JButton loginBtn = new JButton("Login");
        AppFrame.stylePrimary(loginBtn);
        loginBtn.addActionListener(e -> doLogin());

        JButton backBtn = new JButton("Back");
        AppFrame.styleBack(backBtn);
        backBtn.addActionListener(e -> frame.showWelcome());

        btnPanel.add(loginBtn);
        btnPanel.add(backBtn);

        cgb.gridy++;
        cgb.insets = new Insets(20, 0, 0, 0);
        card.add(btnPanel, cgb);

        cgb.gridx = 0;
        cgb.gridy = 0;
        center.add(card, cgb);
        return center;
    }

    private GridBagConstraints addField(JPanel card, GridBagConstraints gbc, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(AppFrame.LABEL_FONT);
        label.setForeground(AppFrame.TEXT_SEC);
        label.setLabelFor(field);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 4, 0);
        card.add(label, gbc);

        field.setFont(AppFrame.BODY_FONT);
        field.setMaximumSize(new Dimension(400, 36));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 16, 0);
        card.add(field, gbc);
        return gbc;
    }

    /**
     * Uses UserManager.authenticateAdmin() for Swing GUI
     * Exception Handling - validates input fields
     */
    private void doLogin() {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword()).trim();

        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password is required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (frame.getUserManager().authenticateAdmin(user, pass)) {
            frame.setAdminLoggedIn(true);
            JOptionPane.showMessageDialog(this, "Admin Login Successful!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.showAdminDash();
        } else {
            JOptionPane.showMessageDialog(this, "Username is incorrect.",
                "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
