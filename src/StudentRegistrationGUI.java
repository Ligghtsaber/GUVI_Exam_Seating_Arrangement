// StudentRegistrationGUI.java
// GUI for student sign-up

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class StudentRegistrationGUI extends JFrame {
    private JTextField regNoField, nameField, sessionField, semesterField, yearField, programField;
    private JPasswordField passwordField;

    public StudentRegistrationGUI() {
        setTitle("Student Registration");
        setSize(350, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblRegNo = new JLabel("Reg No:");
        lblRegNo.setBounds(30, 30, 100, 25);
        add(lblRegNo);
        regNoField = new JTextField();
        regNoField.setBounds(150, 30, 150, 25);
        add(regNoField);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(30, 70, 100, 25);
        add(lblName);
        nameField = new JTextField();
        nameField.setBounds(150, 70, 150, 25);
        add(nameField);

        JLabel lblSession = new JLabel("Session:");
        lblSession.setBounds(30, 110, 100, 25);
        add(lblSession);
        sessionField = new JTextField();
        sessionField.setBounds(150, 110, 150, 25);
        add(sessionField);

        JLabel lblSemester = new JLabel("Semester:");
        lblSemester.setBounds(30, 150, 100, 25);
        add(lblSemester);
        semesterField = new JTextField();
        semesterField.setBounds(150, 150, 150, 25);
        add(semesterField);

        JLabel lblYear = new JLabel("Year:");
        lblYear.setBounds(30, 190, 100, 25);
        add(lblYear);
        yearField = new JTextField();
        yearField.setBounds(150, 190, 150, 25);
        add(yearField);

        JLabel lblProgram = new JLabel("Program:");
        lblProgram.setBounds(30, 230, 100, 25);
        add(lblProgram);
        programField = new JTextField();
        programField.setBounds(150, 230, 150, 25);
        add(programField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(30, 270, 100, 25);
        add(lblPassword);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 270, 150, 25);
        add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 310, 120, 30);
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DBConnection.getConnection()) {
                    String sql = "INSERT INTO Students (reg_no, name, session, semester, year, program, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, regNoField.getText());
                    pst.setString(2, nameField.getText());
                    pst.setString(3, sessionField.getText());
                    pst.setString(4, semesterField.getText());
                    pst.setString(5, yearField.getText());
                    pst.setString(6, programField.getText());
                    pst.setString(7, new String(passwordField.getPassword()));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Student registered successfully!");
                    dispose();
                    new StudentLoginGUI().setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Registration failed!");
                }
            }
        });
    }
}
