// StudentDashboardGUI.java
// Dashboard with course input/view options

import javax.swing.*;

public class StudentDashboardGUI extends JFrame {
    private String regNo;

    public StudentDashboardGUI(String regNo) {
        this.regNo = regNo;
        setTitle("Student Dashboard - " + regNo);
        setSize(350, 200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton courseButton = new JButton("Enter Course Units");
        courseButton.setBounds(90, 30, 160, 30);
        add(courseButton);

        JButton viewButton = new JButton("View My Seating");
        viewButton.setBounds(90, 80, 160, 30);
        add(viewButton);

        courseButton.addActionListener(e -> {
            new CourseUnitInputGUI(regNo).setVisible(true);
        });

        viewButton.addActionListener(e -> {
            new SeatingViewGUI(regNo).setVisible(true);

        });
    }
}
