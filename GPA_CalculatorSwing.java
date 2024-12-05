import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GPA_CalculatorSwing extends JFrame {
    // Declare text fields for grades, credits, and GPA
    private JTextField grade1, grade2, grade3, grade4, grade5;
    private JTextField credit1, credit2, credit3, credit4, credit5;
    private JTextField gpaField;
    private JButton calculateButton, resetButton;

    public GPA_Calculator() {
        // Set up the JFrame
        setTitle("GPA Calculator");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 3, 10, 10));

        // Add components to the JFrame
        add(new JLabel("Subject 1 Grade:"));
        grade1 = new JTextField();
        add(grade1);
        add(new JLabel("Credits (2/3/4):"));
        credit1 = new JTextField();
        add(credit1);

        add(new JLabel("Subject 2 Grade:"));
        grade2 = new JTextField();
        add(grade2);
        add(new JLabel("Credits (2/3/4):"));
        credit2 = new JTextField();
        add(credit2);

        add(new JLabel("Subject 3 Grade:"));
        grade3 = new JTextField();
        add(grade3);
        add(new JLabel("Credits (2/3/4):"));
        credit3 = new JTextField();
        add(credit3);

        add(new JLabel("Subject 4 Grade:"));
        grade4 = new JTextField();
        add(grade4);
        add(new JLabel("Credits (2/3/4):"));
        credit4 = new JTextField();
        add(credit4);

        add(new JLabel("Subject 5 Grade:"));
        grade5 = new JTextField();
        add(grade5);
        add(new JLabel("Credits (2/3/4):"));
        credit5 = new JTextField();
        add(credit5);

        // Non-editable GPA text field
        add(new JLabel("GPA:"));
        gpaField = new JTextField();
        gpaField.setEditable(false); // Make this field non-editable
        add(gpaField);

        // Add Calculate and Reset buttons
        calculateButton = new JButton("Calculate");
        add(calculateButton);
        resetButton = new JButton("Reset");
        add(resetButton);

        // Add event listeners to the buttons
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGPA();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
    }

    // Method to calculate GPA
    private void calculateGPA() {
        try {
            // Array for grade points
            double gradePoints[] = new double[5];
            double credits[] = new double[5];

            // Get the grade points for each subject
            gradePoints[0] = getGradePoints(grade1.getText());
            gradePoints[1] = getGradePoints(grade2.getText());
            gradePoints[2] = getGradePoints(grade3.getText());
            gradePoints[3] = getGradePoints(grade4.getText());
            gradePoints[4] = getGradePoints(grade5.getText());

            // Get the credits for each subject
            credits[0] = Double.parseDouble(credit1.getText());
            credits[1] = Double.parseDouble(credit2.getText());
            credits[2] = Double.parseDouble(credit3.getText());
            credits[3] = Double.parseDouble(credit4.getText());
            credits[4] = Double.parseDouble(credit5.getText());

            // Calculate total points and total credits
            double totalPoints = 0;
            double totalCredits = 0;

            for (int i = 0; i < 5; i++) {
                totalPoints += gradePoints[i] * credits[i];
                totalCredits += credits[i];
            }

            // GPA calculation
            double gpa = totalPoints / totalCredits;
            gpaField.setText(String.format("%.2f", gpa)); // Display the GPA with 2 decimal places

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid input for all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to convert grade into grade points
    private double getGradePoints(String grade) {
        switch (grade.toUpperCase()) {
            case "O":
                return 10.0;
            case "A+":
                return 9.0;
            case "A":
                return 8.0;
            case "B+":
                return 7.0;
            case "B":
                return 6.0;
            default:
                return 0.0; // Invalid grade
        }
    }

    // Method to reset the fields
    private void resetFields() {
        grade1.setText("");
        grade2.setText("");
        grade3.setText("");
        grade4.setText("");
        grade5.setText("");
        credit1.setText("");
        credit2.setText("");
        credit3.setText("");
        credit4.setText("");
        credit5.setText("");
        gpaField.setText("");
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Directly create and display the window
        GPA_Calculator gpaCalculator = new GPA_Calculator();
        gpaCalculator.setVisible(true); // Display the JFrame
    }
}