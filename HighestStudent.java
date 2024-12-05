import java.util.*;

class HighestStudent {
    private String id;
    private String name;
    private String department;
    private List<Integer> marks;

    // Constructor
    public HighestStudent(String id, String name, String department, List<Integer> marks) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    // Method to calculate average marks
    public double calculateAverage() {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return sum / (double) marks.size();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getAverageMarks() {
        return calculateAverage();
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Average Marks: " + String.format("%.2f", calculateAverage());
    }
}

public class StudentManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        // Input number of students
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        // Collect student data
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");

            System.out.print("Student ID: ");
            String id = scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Department: ");
            String department = scanner.nextLine();

            List<Integer> marks = new ArrayList<>();
            System.out.println("Enter marks for 5 subjects:");
            for (int j = 0; j < 5; j++) {
                System.out.print("Subject " + (j + 1) + ": ");
                marks.add(scanner.nextInt());
            }
            scanner.nextLine(); // Consume newline

            // Create student object and add to list
            students.add(new HighestStudent(id, name, department, marks));
        }

        // Calculate averages and display results
        double highestAverage = Double.NEGATIVE_INFINITY;
        double lowestAverage = Double.POSITIVE_INFINITY;
        Student highestStudent = null;
        Student lowestStudent = null;

        System.out.println("\nStudent Details and Average Marks:");
        for (Student student : students) {
            System.out.println(student);
            double average = student.getAverageMarks();

            if (average > highestAverage) {
                highestAverage = average;
                highestStudent = student;
            }
            if (average < lowestAverage) {
                lowestAverage = average;
                lowestStudent = student;
            }
        }

        // Display highest and lowest averages
        System.out.println("\nHighest Average Marks: " + String.format("%.2f", highestAverage) + " by " + highestStudent.getName());
        System.out.println("Lowest Average Marks: " + String.format("%.2f", lowestAverage) + " by " + lowestStudent.getName());

        scanner.close();
    }
}
