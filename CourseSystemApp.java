/**
 * Course Enrollment and Grade Management System
 * 
 * This program allows administrators to:
 * - Add and update courses
 * - Enroll students in courses
 * - Assign grades to students
 * - Calculate overall course grades for students
 * 
 * The system demonstrates object-oriented principles, especially encapsulation, and effectively utilizes static methods
 * and variables to maintain shared data such as total enrolled students and course list. The application also provides
 * a command-line administrator interface with error handling.
 */

import java.util.*;

class Student {
    private String name;
    private String id;
    private Map<Course, Double> courseGrades;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.courseGrades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void enrollCourse(Course course) {
        if (course.getEnrolledCount() < course.getMaxCapacity()) {
            courseGrades.put(course, null);
            course.incrementEnrollment();
        } else {
            System.out.println("Course capacity reached. Cannot enroll.");
        }
    }

    public void assignGrade(Course course, double grade) {
        if (courseGrades.containsKey(course)) {
            courseGrades.put(course, grade);
        } else {
            System.out.println("Student not enrolled in course.");
        }
    }

    public Map<Course, Double> getCourseGrades() {
        return courseGrades;
    }
}

class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int enrolledCount = 0;
    private static int totalEnrolled = 0;

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public void incrementEnrollment() {
        enrolledCount++;
        totalEnrolled++;
    }

    public static int getTotalEnrolled() {
        return totalEnrolled;
    }
}

class CourseManagement {
    private static List<Course> courses = new ArrayList<>();

    public static void addCourse(String code, String name, int capacity) {
        courses.add(new Course(code, name, capacity));
    }

    public static Course getCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    public static void enrollStudent(Student student, Course course) {
        student.enrollCourse(course);
    }

    public static void assignGrade(Student student, Course course, double grade) {
        student.assignGrade(course, grade);
    }

    public static double calculateOverallGrade(Student student) {
        Map<Course, Double> grades = student.getCourseGrades();
        double total = 0;
        int count = 0;
        for (Double grade : grades.values()) {
            if (grade != null) {
                total += grade;
                count++;
            }
        }
        return count > 0 ? total / count : 0;
    }
}

public class CourseSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Student> students = new HashMap<>();
        boolean running = true;

        while (running) {
            System.out.println("\n--- University Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate Overall Grade");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter course code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter max capacity: ");
                    int cap = scanner.nextInt();
                    CourseManagement.addCourse(code, name, cap);
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    Student student = students.getOrDefault(studentId, new Student(studentName, studentId));
                    students.putIfAbsent(studentId, student);
                    System.out.print("Enter course code to enroll: ");
                    String enrollCode = scanner.nextLine();
                    Course course = CourseManagement.getCourseByCode(enrollCode);
                    if (course != null) {
                        CourseManagement.enrollStudent(student, course);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String sid = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String cc = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    double grade = scanner.nextDouble();
                    scanner.nextLine();
                    Student st = students.get(sid);
                    Course c = CourseManagement.getCourseByCode(cc);
                    if (st != null && c != null) {
                        CourseManagement.assignGrade(st, c, grade);
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    String sId = scanner.nextLine();
                    Student s = students.get(sId);
                    if (s != null) {
                        double overall = CourseManagement.calculateOverallGrade(s);
                        System.out.println("Overall Grade: " + overall);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
