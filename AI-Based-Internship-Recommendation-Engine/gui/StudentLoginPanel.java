import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

/**
 * Swing GUI - Student Login Panel
 * BorderLayout - outer layout
 * GridBagLayout - form layout
 * Encapsulation - uses AppFrame reference
 * Interface implementation - ActionListener
 * Exception Handling - input validation
 */
public class StudentLoginPanel extends JPanel {

    private AppFrame frame;
    private JTextField idField;
    private JPasswordField passField;

    public StudentLoginPanel(AppFrame frame) {
        this.frame = frame;
        setBackground(AppFrame.BG);
        setLayout(new BorderLayout(0, 0));

        add(createHeader("Student Login"), BorderLayout.NORTH);
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
        gbc.weighty = 0;

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

        // Page title
        JLabel title = new JLabel("Student Login");
        title.setFont(AppFrame.PAGE_TITLE);
        title.setForeground(AppFrame.TEXT);
        cgb.gridy = 0;
        cgb.insets = new Insets(0, 0, 6, 0);
        card.add(title, cgb);

        // Description
        JLabel desc = new JLabel("Login to access your internship recommendations.");
        desc.setFont(AppFrame.DESC_FONT);
        desc.setForeground(AppFrame.TEXT_SEC);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        cgb.gridy = 1;
        cgb.insets = new Insets(0, 0, 20, 0);
        card.add(desc, cgb);

        // Form fields
        cgb = addField(card, cgb, "Student ID", idField = new JTextField(25));
        cgb = addField(card, cgb, "Password", passField = new JPasswordField(25));

        // Buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(AppFrame.SURFACE);
        btnPanel.setLayout(new GridLayout(1, 2, 10, 0));
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

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

    /**
     * Encapsulation - labeled form field pair
     */
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
     * Encapsulation - uses UserManager.authenticateStudent()
     * Exception Handling - validates input fields
     */
    private void doLogin() {
        String idText = idField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        if (idText.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Student ID and Password.",
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

        Student student = frame.getUserManager().authenticateStudent(id, password);

        if (student != null) {
            frame.setCurrentStudent(student);
            JOptionPane.showMessageDialog(this, "Login Successful!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.showStudentDash();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials.",
                "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
