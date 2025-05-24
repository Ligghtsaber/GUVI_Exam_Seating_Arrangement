// AdminDashboardGUI.java
// Admin dashboard for managing exam setup

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboardGUI extends JFrame {
    public AdminDashboardGUI() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnAddClassroom = new JButton("Add Classrooms");
        btnAddClassroom.setBounds(100, 30, 200, 30);
        add(btnAddClassroom);

        JButton btnGenerateSeating = new JButton("Generate Seating");
        btnGenerateSeating.setBounds(100, 80, 200, 30);
        add(btnGenerateSeating);

        JButton btnViewSeating = new JButton("View Seating");
        btnViewSeating.setBounds(100, 130, 200, 30);
        add(btnViewSeating);

        JButton btnExportPDF = new JButton("Export Seating to PDF");
        btnExportPDF.setBounds(100, 180, 200, 30);
        add(btnExportPDF);

        // Button Listeners
        btnAddClassroom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClassroomInputGUI().setVisible(true);
            }
        });

        btnGenerateSeating.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SeatingGeneratorGUI().setVisible(true);
            }
        });

        btnViewSeating.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SeatingViewGUI().setVisible(true);
            }
        });

       
    }
}
