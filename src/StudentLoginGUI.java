// StudentLoginGUI.java
// GUI for login

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class StudentLoginGUI extends JFrame {
    private JTextField regNoField;
    private JPasswordField passwordField;

    public StudentLoginGUI() {
        setTitle("Student Login");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblRegNo = new JLabel("Reg No:");
        lblRegNo.setBounds(30, 30, 80, 25);
        add(lblRegNo);

        regNoField = new JTextField();
        regNoField.setBounds(110, 30, 140, 25);
        add(regNoField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(30, 70, 80, 25);
        add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 70, 140, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(90, 110, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DBConnection.getConnection()) {
                    String sql = "SELECT * FROM Students WHERE reg_no = ? AND password = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, regNoField.getText());
                    pst.setString(2, new String(passwordField.getPassword()));
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        dispose();
                        new StudentDashboardGUI(rs.getString("reg_no")).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
