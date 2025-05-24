import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLoginGUI() {
        setTitle("Admin Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 160, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 160, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(110, 110, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText().trim();
                String pass = new String(passwordField.getPassword()).trim();

                System.out.println("🔍 Entered Username: " + user);
                System.out.println("🔍 Entered Password: " + pass);

                try (Connection con = DBConnection.getConnection()) {
                    if (con == null) {
                        System.err.println("❌ Connection to DB failed.");
                        JOptionPane.showMessageDialog(null, "Failed to connect to database.");
                        return;
                    }

                    // Use LTRIM/RTRIM in case of extra spaces
                    String query = "SELECT * FROM Admins WHERE LTRIM(RTRIM(username)) = ? AND LTRIM(RTRIM(password)) = ?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, user);
                    pst.setString(2, pass);

                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        System.out.println("✅ Login successful!");
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        new AdminDashboardGUI().setVisible(true);
                        dispose();
                    } else {
                        System.out.println("❌ Invalid login attempt.");
                        JOptionPane.showMessageDialog(null, "Invalid credentials!");
                    }
                } catch (Exception ex) {
                    System.err.println("❗ Exception during login:");
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Login failed due to error.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminLoginGUI().setVisible(true);
        });
    }
}
