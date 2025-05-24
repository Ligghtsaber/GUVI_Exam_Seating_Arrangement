// SeatingGeneratorGUI.java
// Generates seating automatically

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class SeatingGeneratorGUI extends JFrame {
    public SeatingGeneratorGUI() {
        setTitle("Generate Seating Plan");
        setSize(300, 150);
        setLayout(null);
        setLocationRelativeTo(null);

        JButton generateBtn = new JButton("Generate Seating");
        generateBtn.setBounds(70, 40, 150, 40);
        add(generateBtn);

        generateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DBConnection.getConnection()) {
                    // Clear previous seating
                    con.createStatement().executeUpdate("DELETE FROM Seating");

                    ResultSet studentRS = con.createStatement().executeQuery("SELECT reg_no FROM Students");
                    ResultSet classRS = con.createStatement().executeQuery("SELECT * FROM Classrooms");

                    List<String> students = new ArrayList<>();
                    while (studentRS.next()) {
                        students.add(studentRS.getString("reg_no"));
                    }

                    Map<String, Integer> classrooms = new LinkedHashMap<>();
                    while (classRS.next()) {
                        classrooms.put(classRS.getString("name"), classRS.getInt("seat_count"));
                    }

                    int studentIndex = 0;
                    for (Map.Entry<String, Integer> entry : classrooms.entrySet()) {
                        String room = entry.getKey();
                        int seats = entry.getValue();

                        for (int i = 1; i <= seats && studentIndex < students.size(); i++) {
                            String reg = students.get(studentIndex++);
                            PreparedStatement pst = con.prepareStatement(
                                "INSERT INTO Seating (reg_no, classroom, seat_number) VALUES (?, ?, ?)");
                            pst.setString(1, reg);
                            pst.setString(2, room);
                            pst.setInt(3, i);
                            pst.executeUpdate();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Seating plan generated!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error generating seating.");
                }
            }
        });
    }
}
