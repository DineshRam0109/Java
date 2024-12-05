public class IPv4Validator {

    // Function to check if the given string is a valid IPv4 address
    public static boolean isValidIPv4(String ip) {
        // Split the string by dots
        String[] octets = ip.split("\\.");

        // An IPv4 address must contain exactly four octets
        if (octets.length != 4) {
            return false;
        }

        // Check each octet
        for (String octet : octets) {
            // Each octet must be a valid number between 0 and 255, and cannot contain leading zeros
            if (!isValidOctet(octet)) {
                return false;
            }
        }

        return true;
    }

    // Helper function to check if a string is a valid octet
    private static boolean isValidOctet(String octet) {
        // An octet must not be empty
        if (octet.isEmpty()) {
            return false;
        }

        // An octet must be numeric and contain only digits
        for (char ch : octet.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }

        // Convert the octet to an integer
        int num;
        try {
            num = Integer.parseInt(octet);
        } catch (NumberFormatException e) {
            return false;
        }

        // Each octet must be in the range of 0 to 255
        if (num < 0 || num > 255) {
            return false;
        }

        // Octets must not contain leading zeros, unless the octet is "0"
        if (octet.length() > 1 && octet.startsWith("0")) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        // Test cases
        String[] testIPs = {
            "192.168.1.1",       // valid
            "0.0.0.0",           // valid
            "255.255.255.255",   // valid
            "172.16.254.1",      // valid
            "256.100.50.25",     // invalid: first octet out of range
            "192.168.01.1",      // invalid: leading zero in third octet
            "192.168.1",         // invalid: only three octets
            "192.168.1.1.1",     // invalid: five octets
            "192.168.1.a"        // invalid: non-numeric character in fourth octet
        };

        // Check each test case
        for (String ip : testIPs) {
            System.out.println(ip + ": " + (isValidIPv4(ip) ? "Valid" : "Invalid"));
        }
    }
}
