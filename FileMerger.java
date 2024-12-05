import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileMerger {
    public static void main(String[] args) {
        String[] inputFiles = {"file1.txt", "file2.txt"};
        String outputFile = "merge.txt";

        try (FileWriter writer = new FileWriter(outputFile)) {
            for (String inputFile : inputFiles) {
                mergeFiles(inputFile, writer);
                writer.write("\n"); // Add a new line between file contents
            }
            System.out.println("Files merged successfully into " + outputFile);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void mergeFiles(String inputFile, FileWriter writer) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Add a hyphen before each line (except the first line of the file)
                if (line.length() > 0) {
                    writer.write(line);
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading " + inputFile + ": " + e.getMessage());
        }
    }
}
