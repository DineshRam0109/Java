import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StatisticalAnalysis {
    public static void main(String[] args) {
        String fileName = "numbers.txt";
        List<Integer> numbers = new ArrayList<>();

        // Read numbers from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                for (String part : parts) {
                    numbers.add(Integer.parseInt(part));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return;
        }

        // Calculate mean
        double mean = calculateMean(numbers);
        // Calculate median
        double median = calculateMedian(numbers);
        // Calculate mode
        List<Integer> mode = calculateMode(numbers);

        // Print results
        System.out.println("Mean: " + mean);
        System.out.println("Median: " + median);
        System.out.println("Mode: " + mode);
    }

    private static double calculateMean(List<Integer> numbers) {
        return numbers.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }

    private static double calculateMedian(List<Integer> numbers) {
        Collections.sort(numbers);
        int size = numbers.size();
        if (size % 2 == 0) {
            return (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2.0;
        } else {
            return numbers.get(size / 2);
        }
    }

    private static List<Integer> calculateMode(List<Integer> numbers) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int number : numbers) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }

        int maxFrequency = Collections.max(frequencyMap.values());
        List<Integer> modes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modes.add(entry.getKey());
            }
        }
        return modes;
    }
}
