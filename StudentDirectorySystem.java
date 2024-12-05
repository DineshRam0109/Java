import java.util.*;
class StudentDirectorySystem {
    HashMap<Integer, String> students=new HashMap<>();

    public void addStudent(int id, String name) {
        if(students.containsKey(id)) {
            System.out.println("ID already exists. Try with a unique ID.");
        } else {
            students.put(id, name);
            System.out.println("Student added successfully!");
        }
    }

    public void removeStudent(int id) {
        if(students.containsKey(id)) {
            students.remove(id);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    public void displayStudents() {
        if(students.isEmpty()) {
            System.out.println("No students in the directory.");
        } else {
            System.out.println("Student Directory:");
            for(Map.Entry<Integer, String> entry : students.entrySet()) {
                System.out.println("ID: " + entry.getKey() + ", Name: " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        StudentDirectory directory = new StudentDirectory();
        Scanner scanner = new Scanner(System.in);

        directory.addStudent(101, "Alice");
        directory.addStudent(102, "Bob");
        directory.addStudent(103, "Charlie");
        directory.displayStudents();

        int choice;
        do {
            System.out.println("\n--- Student Directory Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Display Students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    directory.addStudent(id, name);
                    break;
                case 2:
                    System.out.print("Enter Student ID to remove: ");
                    int removeId = scanner.nextInt();
                    directory.removeStudent(removeId);
                    break;
                case 3:
                    directory.displayStudents();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}