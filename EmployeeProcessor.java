/**
 * This program demonstrates the use of Java's Function interface and Streams API
 * to manage and process a list of employees. It performs the following operations:
 * 1. Creates a list of employees with their names, ages, and salaries.
 * 2. Uses a Function to calculate the average salary of all employees.
 * 3. Filters employees who are older than 30 and collects them into a new list.
 * 4. Displays the average salary and the list of employees over 30.
 */

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ", Salary: $" + salary + ")";
    }
}

public class EmployeeProcessor {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Aliyu", 25, 50000),
            new Employee("Bashir", 35, 65000),
            new Employee("Charles", 40, 70000),
            new Employee("Dom", 28, 48000),
            new Employee("Emmanuel", 32, 72000)
        );

        // Function to calculate the average salary
        Function<List<Employee>, Double> averageSalaryFunction = list ->
            list.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);

        double averageSalary = averageSalaryFunction.apply(employees);

        // Filter employees over 30
        List<Employee> employeesOver30 = employees.stream()
            .filter(emp -> emp.getAge() > 30)
            .collect(Collectors.toList());

        // Display results
        System.out.println("Average Salary: $" + averageSalary);
        System.out.println("Employees over 30:");
        employeesOver30.forEach(System.out::println);
    }
}
