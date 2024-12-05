import java.io.*;
import java.util.ArrayList;
import java.util.List;

class EmployeeFile {
    private String name;
    private double salary;

    // Constructor
    public EmployeeFile(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

public class EmployeeSalaryManager {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        String inputFileName = "employees.txt";
        String outputFileName = "employee_results.txt";

        // Read employee records from file
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                double salary = Double.parseDouble(parts[1].trim());
                employees.add(new Employee(name, salary));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.err.println("Error parsing salary: " + e.getMessage());
            return;
        }

        // Calculate average salary
        double totalSalary = 0;
        int count = employees.size();
        for (Employee employee : employees) {
            totalSalary += employee.getSalary();
        }
        double averageSalary = count > 0 ? totalSalary / count : 0;

        // Identify employees with salary greater than 300,000
        List<Employee> highSalaryEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary() > 300000) {
                highSalaryEmployees.add(employee);
            }
        }

        // Display results
        System.out.println("Average Salary: " + String.format("%.2f", averageSalary));
        System.out.println("Employees with Salary greater than 300,000:");
        for (Employee employee : highSalaryEmployees) {
            System.out.println(employee.getName() + ": " + employee.getSalary());
        }

        // Write results to output file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            bw.write("Average Salary: " + String.format("%.2f", averageSalary));
            bw.newLine();
            bw.write("Employees with Salary greater than 300,000:");
            bw.newLine();
            for (Employee employee : highSalaryEmployees) {
                bw.write(employee.getName() + ": " + employee.getSalary());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
