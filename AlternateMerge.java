public class AlternateMerge {

    // Function to merge two strings alternately
    public static String mergeAlternately(String str1, String str2) {
        StringBuilder mergedString = new StringBuilder();
        int i = 0, j = 0;

        // Loop through both strings until one of them is exhausted
        while (i < str1.length() && j < str2.length()) {
            mergedString.append(str1.charAt(i++)); // Add character from the first string
            mergedString.append(str2.charAt(j++)); // Add character from the second string
        }

        // If str1 has remaining characters, append them
        while (i < str1.length()) {
            mergedString.append(str1.charAt(i++));
        }

        // If str2 has remaining characters, append them
        while (j < str2.length()) {
            mergedString.append(str2.charAt(j++));
        }

        return mergedString.toString();
    }

    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "world";
        String result = mergeAlternately(str1, str2);
        System.out.println(result); // Output: hweolrllod
    }
}
