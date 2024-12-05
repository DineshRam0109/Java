import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FactorialSwingApp extends JFrame {

    private JTextField numberInputField;
    private JLabel resultLabel;

    public FactorialSwingApp() {
        setTitle("Factorial Calculator");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        numberInputField = new JTextField(10);
        JButton calculateButton = new JButton("Calculate Factorial");
        resultLabel = new JLabel("Result: ");
        
        panel.add(new JLabel("Enter a number:"));  
        panel.add(numberInputField);              
        panel.add(calculateButton);               
        panel.add(resultLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int number = Integer.parseInt(numberInputField.getText());
                    if (number < 0) {
                        resultLabel.setText("Enter a positive number.");
                    } else {
                        int factorial = calculateFactorial(number);
                        resultLabel.setText("Factorial: " + factorial);
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number.");
                }
            }
        });
        add(panel);
    }

    private int calculateFactorial(int num) {
        if (num == 0 || num == 1) {
            return 1;
        } else {
            int result = 1;
            for (int i = 2; i <= num; i++) {
                result *= i;
            }
            return result;
        }
    }
    public static void main(String[] args) {
    FactorialSwingApp app = new FactorialSwingApp();
    app.setVisible(true);
}

}