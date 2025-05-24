// Main.java
// Main class to run application

public class Main {
    public static void main(String[] args) {
        // Launch the Admin or Student login depending on user preference
        javax.swing.SwingUtilities.invokeLater(() -> {
            String[] options = {"Admin", "Student"};
            int choice = javax.swing.JOptionPane.showOptionDialog(
                null,
                "Who are you logging in as?",
                "Login Type",
                javax.swing.JOptionPane.DEFAULT_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
            );

            if (choice == 0) {
                new AdminLoginGUI().setVisible(true);
            } else if (choice == 1) {
                new StudentLoginGUI().setVisible(true);
            }
        });
    }
}
