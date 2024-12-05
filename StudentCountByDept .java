import java.util.*;

class StudentCountByDept {
    private String regNumber;
    private String name;
    private String department;
    private double cgpa;

    public Student(String regNumber, String name, String department, double cgpa) {
        this.regNumber = regNumber;
        this.name = name;
        this.department = department;
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "RegNumber: " + regNumber + ", Name: " + name + ", Department: " + department + ", CGPA: " + cgpa;
    }
}

public class RegistrationManager {
    private Map<String, Student> studentMap = new HashMap<>();
    private Map<String, Integer> departmentCount = new HashMap<>();

    public void registerStudent(String regNumber, String name, String department, double cgpa) {
        if (cgpa >= 7.0) {
            if (!studentMap.containsKey(regNumber)) {
                Student student = new Student(regNumber, name, department, cgpa);
                studentMap.put(regNumber, student);
                departmentCount.put(department, departmentCount.getOrDefault(department, 0) + 1);
                System.out.println("Student registered successfully: " + student);
            } else {
                System.out.println("Registration number already exists: " + regNumber);
            }
        } else {
            System.out.println("Student does not meet the CGPA requirement: " + name);
        }
    }

    public void displayDepartmentCounts() {
        System.out.println("Registered Students Count by Department:");
        for (Map.Entry<String, Integer> entry : departmentCount.entrySet()) {
            System.out.println("Department: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        RegistrationManager manager = new RegistrationManager();
        manager.registerStudent("001", "Alice", "CSE", 8.5);
        manager.registerStudent("002", "Bob", "ECE", 7.2);
        manager.registerStudent("003", "Charlie", "CSE", 6.9); 
        manager.registerStudent("001", "David", "MECH", 7.5); 

        manager.displayDepartmentCounts();
    }
}