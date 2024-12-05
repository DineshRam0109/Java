import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleLoginFormSwing extends JFrame {
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    private final String correctUsername = "admin";
    private final String correctPassword = "password123";

    public SimpleLoginForm() {
        setTitle("Login Form");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField(10);
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(10);
        panel.add(passwordField);
        
        messageLabel = new JLabel("");
        panel.add(messageLabel);

        JButton loginButton = new JButton("Login");
        JButton resetButton = new JButton("Reset");

        loginButton.addActionListener(new LoginButtonListener());
        resetButton.addActionListener(new ResetButtonListener());
        panel.add(loginButton);
        panel.add(resetButton);

        add(panel);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                messageLabel.setText("Login successful!");
            } else {
                messageLabel.setText("Invalid username or password.");
            }
        }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            usernameField.setText("");
            passwordField.setText("");
            messageLabel.setText("");
        }
    }

    public static void main(String[] args) {
            SimpleLoginForm loginForm = new SimpleLoginForm();
            loginForm.setVisible(true);
    }
}