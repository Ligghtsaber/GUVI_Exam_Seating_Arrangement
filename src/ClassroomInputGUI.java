// ClassroomInputGUI.java
// Admin input for rooms and capacities

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ClassroomInputGUI extends JFrame {
    private JTextField classNameField, seatCountField;

    public ClassroomInputGUI() {
        setTitle("Add Classroom");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblName = new JLabel("Classroom Name:");
        lblName.setBounds(30, 30, 120, 25);
        add(lblName);

        classNameField = new JTextField();
        classNameField.setBounds(150, 30, 100, 25);
        add(classNameField);

        JLabel lblSeats = new JLabel("Seat Count:");
        lblSeats.setBounds(30, 70, 120, 25);
        add(lblSeats);

        seatCountField = new JTextField();
        seatCountField.setBounds(150, 70, 100, 25);
        add(seatCountField);

        JButton saveButton = new JButton("Add");
        saveButton.setBounds(90, 110, 100, 30);
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DBConnection.getConnection()) {
                    String sql = "INSERT INTO Classrooms (name, seat_count) VALUES (?, ?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, classNameField.getText());
                    pst.setInt(2, Integer.parseInt(seatCountField.getText()));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Classroom added!");
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding classroom.");
                }
            }
        });
    }
}
