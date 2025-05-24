// ViewCourseUnitsGUI.java
// Display student course units

import java.sql.*;
import javax.swing.*;

public class ViewCourseUnitsGUI extends JFrame {
    private JTextArea courseUnitsArea;
    private String regNo;

    public ViewCourseUnitsGUI(String regNo) {
        this.regNo = regNo;
        setTitle("View Course Units");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Your Course Units:");
        label.setBounds(20, 20, 200, 25);
        add(label);

        courseUnitsArea = new JTextArea();
        courseUnitsArea.setEditable(false);
        courseUnitsArea.setBounds(20, 50, 350, 150);
        add(courseUnitsArea);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(150, 210, 100, 30);
        add(closeButton);

        closeButton.addActionListener(e -> dispose());

        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT course_units FROM Students WHERE reg_no = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, regNo);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                courseUnitsArea.setText(rs.getString("course_units"));
            } else {
                courseUnitsArea.setText("No course units found.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            courseUnitsArea.setText("Error loading course units.");
        }
    }
}
