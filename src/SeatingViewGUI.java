// SeatingViewGUI.java
// Overloaded constructors for admin and student

import java.sql.*;
import javax.swing.*;

public class SeatingViewGUI extends JFrame {

    public SeatingViewGUI() {
        this(null); // call the regNo constructor with null to view all
    }

    public SeatingViewGUI(String regNo) {
        setTitle(regNo == null ? "All Seating Arrangements" : "Seating for " + regNo);
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setBounds(20, 20, 340, 200);
        add(area);

        JButton pdfBtn = new JButton("Export to PDF");
        pdfBtn.setBounds(120, 230, 150, 30);
        add(pdfBtn);

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps;
            if (regNo != null) {
                ps = con.prepareStatement("SELECT * FROM Seating WHERE reg_no = ?");
                ps.setString(1, regNo);
            } else {
                ps = con.prepareStatement("SELECT * FROM Seating");
            }

            ResultSet rs = ps.executeQuery();
            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                sb.append("Reg No: ").append(rs.getString("reg_no"))
                  .append(" | Room: ").append(rs.getString("classroom"))
                  .append(" | Seat: ").append(rs.getInt("seat_number")).append("\n");
            }

            if (sb.length() == 0) {
                sb.append("No seating records found.");
            }

            area.setText(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
            area.setText("Error loading seating data.");
        }

        // You can hook up pdfBtn later for export
    }
}
