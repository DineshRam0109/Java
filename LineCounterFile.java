import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LineCounterFile {
    public static void main(String[] args) {
        String fileName = "example.txt";
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        System.out.println("Number of lines: " + lineCount);
    }
}
