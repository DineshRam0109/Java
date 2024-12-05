import javax.swing.*;
import java.awt.*;

public class DirectionSwing {

    public static void main(String[] args) {
        // Create a new frame
        JFrame frame = new JFrame("BorderLayout Example");

        // Set the layout manager to BorderLayout
        frame.setLayout(new BorderLayout());

        // Add buttons or labels to the different regions of BorderLayout
        frame.add(new JButton("NORTH"), BorderLayout.NORTH);
        frame.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        frame.add(new JButton("EAST"), BorderLayout.EAST);
        frame.add(new JButton("WEST"), BorderLayout.WEST);
        frame.add(new JButton("CENTER"), BorderLayout.CENTER);

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame size
        frame.setSize(400, 300);

        // Make the frame visible
        frame.setVisible(true);
    }
}