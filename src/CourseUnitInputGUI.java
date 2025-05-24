// CourseUnitInputGUI.java
// Input GUI for course units

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class CourseUnitInputGUI extends JFrame {
    private JTextField courseUnitsField;
    private String regNo;

    public CourseUnitInputGUI(String regNo) {
        this.regNo = regNo;
        setTitle("Course Unit Entry");
        setSize(350, 180);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblUnits = new JLabel("Course Units (comma-separated):");
        lblUnits.setBounds(30, 30, 250, 25);
        add(lblUnits);

        courseUnitsField = new JTextField();
        courseUnitsField.setBounds(30, 60, 270, 25);
        add(courseUnitsField);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(110, 100, 100, 30);
        add(saveBtn);

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String units = courseUnitsField.getText().trim();
                if (units.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter at least one course unit.");
                    return;
                }

                try (Connection con = DBConnection.getConnection()) {
                    String sql = "UPDATE Students SET course_units = ? WHERE reg_no = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, units);
                    pst.setString(2, regNo);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Course units saved.");
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error saving course units.");
                }
            }
        });
    }
}
