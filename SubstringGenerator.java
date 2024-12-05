public class SubstringGenerator {

    // Function to generate and print all substrings of a given string
    public static void printSubstrings(String str) {
        int length = str.length();

        // Loop to pick the starting point of the substring
        for (int i = 0; i < length; i++) {
            // Loop to pick the ending point of the substring
            for (int j = i + 1; j <= length; j++) {
                // Print the substring from index i to j
                System.out.print(str.substring(i, j) + " ");
            }
        }
    }

    public static void main(String[] args) {
        String input = "abc";
        printSubstrings(input);
    }
}
