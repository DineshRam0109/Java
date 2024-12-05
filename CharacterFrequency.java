import java.util.HashMap;
import java.util.Map;

public class CharacterFrequency {

    // Function to count and print character frequencies
    public static void countCharacterFrequency(String str) {
        // Create a HashMap to store character frequencies
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Iterate over each character in the string
        for (char ch : str.toCharArray()) {
            // If the character is already in the map, increment its count
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        // Print each character and its frequency
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        String input = "character";
        countCharacterFrequency(input);
    }
}
