import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WordReplacerFile {
    public static void main(String[] args) {
        String fileName = "example.txt";
        String wordToReplace = "old";
        String replacementWord = "new";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder content = new StringBuilder();
            String line;

            // Read the file and replace the word
            while ((line = reader.readLine()) != null) {
                content.append(line.replace(wordToReplace, replacementWord)).append("\n");
            }
            reader.close();

            // Write the modified content back to the file
            FileWriter writer = new FileWriter(fileName);
            writer.write(content.toString());
            writer.close();

            System.out.println("Replacement complete.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
