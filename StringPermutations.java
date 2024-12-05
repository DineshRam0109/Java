public class StringPermutations {

    // Function to generate and print all permutations of the string
    public static void permute(String str, String result) {
        // Base case: if the string is empty, print the result
        if (str.isEmpty()) {
            System.out.print(result + " ");
            return;
        }

        // Recursive case: Generate permutations by choosing each character
        for (int i = 0; i < str.length(); i++) {
            // Take one character at index i
            char chosenChar = str.charAt(i);
            // Form the remaining string after removing the chosen character
            String remainingString = str.substring(0, i) + str.substring(i + 1);
            // Recursively permute the remaining string
            permute(remainingString, result + chosenChar);
        }
    }

    public static void main(String[] args) {
        String input = "abc";
        permute(input, "");
    }
}
