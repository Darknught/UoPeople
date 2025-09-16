/*
 * Student Record Management System in Java
 * 
 * Description:
 * This Java program allows an administrator to manage student records by
 * performing operations such as adding new students, updating existing
 * student information, and viewing student details.
 * 
 * Key Features:
 * - Uses static variables and methods for storing and managing student records.
 * - Menu-driven interface for administrator interactions.
 * - Input validation and error handling for robustness.
 * 
 * Instructions:
 * - Run the program.
 * - Choose an option from the menu to perform actions.
 * - Follow on-screen prompts to enter and update student details.
 */

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int id;
    int age;
    String grade;

    Student(String name, int id, int age, String grade) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.grade = grade;
    }
}

public class StudentManagement {
    static ArrayList<Student> studentList = new ArrayList<>();
    static int totalStudents = 0;

    public static void addStudent(Scanner scanner) {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();

        int id;
        while (true) {
            System.out.print("Enter Student ID: ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a number.");
            }
        }

        int age;
        while (true) {
            System.out.print("Enter Student Age: ");
            try {
                age = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Age. Please enter a number.");
            }
        }

        System.out.print("Enter Student Grade: ");
        String grade = scanner.nextLine().trim();

        studentList.add(new Student(name, id, age, grade));
        totalStudents++;
        System.out.println("Student added successfully.");
    }

    public static void updateStudent(Scanner scanner) {
        System.out.print("Enter Student ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Student student = null;

        for (Student s : studentList) {
            if (s.id == id) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student ID not found.");
            return;
        }

        System.out.println("Leave a field blank to keep current value.");
        System.out.print("Enter new name (" + student.name + "): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) student.name = name;

        System.out.print("Enter new age (" + student.age + "): ");
        String ageInput = scanner.nextLine().trim();
        if (!ageInput.isEmpty()) {
            try {
                student.age = Integer.parseInt(ageInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input. Age not updated.");
            }
        }

        System.out.print("Enter new grade (" + student.grade + "): ");
        String grade = scanner.nextLine().trim();
        if (!grade.isEmpty()) student.grade = grade;

        System.out.println("Student information updated successfully.");
    }

    public static void viewStudent(Scanner scanner) {
        System.out.print("Enter Student ID to view: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (Student s : studentList) {
            if (s.id == id) {
                System.out.println("\n--- Student Details ---");
                System.out.println("Name: " + s.name);
                System.out.println("ID: " + s.id);
                System.out.println("Age: " + s.age);
                System.out.println("Grade: " + s.grade);
                return;
            }
        }

        System.out.println("Student ID not found.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Record Management ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. View Student Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    addStudent(scanner);
                    break;
                case "2":
                    updateStudent(scanner);
                    break;
                case "3":
                    viewStudent(scanner);
                    break;
                case "4":
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1-4.");
            }
        }
    }
}
