import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 * Author: Ishanvi Kaur and Akshdeep Kaur
 * Date: December 13, 2024
 * Final Project
 * Description: Veterinary clinic application to register new patients with proper validation and file handling.
 */
public class VetClinicApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(VetClinicApp::createGUI);
    }

    private static void createGUI() {
        JFrame frame = new JFrame("Vet Clinic - Patient Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setLayout(new FlowLayout());

        // Input Fields
        JTextField patientField = new JTextField(20);
        JTextField ownerField = new JTextField(20);
        JTextField emailField = new JTextField(20);

        // Radio Buttons
        JRadioButton vet1 = new JRadioButton("Dr. Smith", true);
        JRadioButton vet2 = new JRadioButton("Dr. Lee");
        JRadioButton vet3 = new JRadioButton("Dr. Johnson");
        ButtonGroup vetGroup = new ButtonGroup();
        vetGroup.add(vet1);
        vetGroup.add(vet2);
        vetGroup.add(vet3);

        // Status Label
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);

        // Buttons
        JButton registerButton = new JButton("Register");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        // Add Components to Frame
        frame.add(new JLabel("Patient Name:"));
        frame.add(patientField);
        frame.add(new JLabel("Owner Name:"));
        frame.add(ownerField);
        frame.add(new JLabel("Email Address:"));
        frame.add(emailField);
        frame.add(new JLabel("Select Vet:"));
        frame.add(vet1);
        frame.add(vet2);
        frame.add(vet3);
        frame.add(registerButton);
        frame.add(clearButton);
        frame.add(exitButton);
        frame.add(statusLabel);

        // Button Actions
        registerButton.addActionListener(e -> registerPatient(patientField, ownerField, emailField, vet1, vet2, vet3, statusLabel));
        clearButton.addActionListener(e -> clearFields(patientField, ownerField, emailField, vet1, statusLabel));
        exitButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    private static void registerPatient(JTextField patientField, JTextField ownerField, JTextField emailField, 
                                        JRadioButton vet1, JRadioButton vet2, JRadioButton vet3, JLabel statusLabel) {
        String patientName = patientField.getText().trim();
        String ownerName = ownerField.getText().trim();
        String email = emailField.getText().trim();

        if (patientName.isEmpty() || ownerName.isEmpty() || email.isEmpty()) {
            statusLabel.setText("All fields must be filled.");
            return;
        }

        if (!isValidEmail(email)) {
            statusLabel.setText("Invalid email address.");
            return;
        }

        String vetName = vet1.isSelected() ? "Dr. Smith" : vet2.isSelected() ? "Dr. Lee" : "Dr. Johnson";

        try {
            saveToFile(patientName, ownerName, email, vetName);
            statusLabel.setText("Registration successful!");
        } catch (IOException ex) {
            statusLabel.setText("Error saving file.");
        }
    }

    private static void clearFields(JTextField patientField, JTextField ownerField, JTextField emailField, 
                                    JRadioButton vet1, JLabel statusLabel) {
        patientField.setText("");
        ownerField.setText("");
        emailField.setText("");
        vet1.setSelected(true);
        statusLabel.setText(" ");
    }

    private static boolean isValidEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return Pattern.matches(regex, email);
    }

    private static void saveToFile(String patientName, String ownerName, String email, String vetName) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Patient Registration");
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {
                writer.write("**Patient Registration Document**\n");
                writer.write("Patient Name: " + patientName + "\n");
                writer.write("Owner Name: " + ownerName + "\n");
                writer.write("Email: " + email + "\n");
                writer.write("Assigned Vet: " + vetName + "\n");
                writer.write("Registration Date: " + LocalDate.now() + "\n");
            }
        }
    }
}
