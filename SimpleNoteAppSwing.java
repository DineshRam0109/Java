import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class SimpleNoteAppSwing {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Simple Note Taking Tool");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the text area
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        frame.setJMenuBar(menuBar);

        // Action listeners for File menu
        newItem.addActionListener(e -> textArea.setText("")); // Clears the text area
        openItem.addActionListener(e -> openFile(textArea)); // Opens a file
        saveItem.addActionListener(e -> saveFile(textArea)); // Saves the file

        // Action listeners for Edit menu
        cutItem.addActionListener(e -> textArea.cut()); // Cuts selected text
        copyItem.addActionListener(e -> textArea.copy()); // Copies selected text
        pasteItem.addActionListener(e -> textArea.paste()); // Pastes from clipboard

        // Create Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> System.exit(0)); // Exits the program
        frame.add(closeButton, "South");

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to open a file and load its content into the text area
    private static void openFile(JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                textArea.setText(content);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error opening file.");
            }
        }
    }

    // Method to save the content of the text area to a file
    private static void saveFile(JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Files.write(file.toPath(), textArea.getText().getBytes());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving file.");
            }
        }
    }
}