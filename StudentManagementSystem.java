import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentManagementSystem extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel studentPanel, coursePanel, gradePanel;
    private JTextField nameField, ageField;
    private JTable studentTable;
    private DefaultTableModel studentTableModel;
    private JComboBox<String> courseDropdown, studentDropdown, enrollmentDropdown;
    private JTextArea enrollmentDisplay;
    private JComboBox<String> gradeStudentDropdown, gradeCourseDropdown;
    private JTextField gradeField;
    private JTextArea gradeDisplay;

    private ArrayList<Student> students = new ArrayList<>();
    private String[] courses = {"Math", "Science", "History"};

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        studentPanel = new JPanel(new BorderLayout());
        coursePanel = new JPanel(new BorderLayout());
        gradePanel = new JPanel(new BorderLayout());

        setupStudentTab();
        setupCourseTab();
        setupGradeTab();

        tabbedPane.add("Student Management", studentPanel);
        tabbedPane.add("Course Enrollment", coursePanel);
        tabbedPane.add("Grade Management", gradePanel);

        add(tabbedPane);
        setVisible(true);
    }

    private void setupStudentTab() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField(10);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField(3);
        inputPanel.add(ageField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be empty.");
                }
                Student student = new Student(name, age);
                students.add(student);
                updateStudentTable();
                updateDropdowns();
                nameField.setText("");
                ageField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for age.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        inputPanel.add(addButton);

        studentTableModel = new DefaultTableModel(new String[]{"Name", "Age"}, 0);
        studentTable = new JTable(studentTableModel);

        studentPanel.add(inputPanel, BorderLayout.NORTH);
        studentPanel.add(new JScrollPane(studentTable), BorderLayout.CENTER);
    }

    private void setupCourseTab() {
        JPanel topPanel = new JPanel();
        courseDropdown = new JComboBox<>(courses);
        studentDropdown = new JComboBox<>();

        topPanel.add(new JLabel("Select Course:"));
        topPanel.add(courseDropdown);
        topPanel.add(new JLabel("Select Student:"));
        topPanel.add(studentDropdown);

        JButton enrollButton = new JButton("Enroll");
        enrollButton.addActionListener(e -> {
            try {
                String selectedStudent = (String) studentDropdown.getSelectedItem();
                String selectedCourse = (String) courseDropdown.getSelectedItem();
                if (selectedStudent == null || selectedCourse == null) {
                    throw new IllegalArgumentException("Please select both student and course.");
                }
                for (Student s : students) {
                    if (s.getName().equals(selectedStudent)) {
                        s.enrollInCourse(selectedCourse);
                        updateEnrollmentDisplay();
                        return;
                    }
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Enrollment Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        topPanel.add(enrollButton);

        enrollmentDisplay = new JTextArea(10, 50);
        enrollmentDisplay.setEditable(false);

        coursePanel.add(topPanel, BorderLayout.NORTH);
        coursePanel.add(new JScrollPane(enrollmentDisplay), BorderLayout.CENTER);
    }

    private void setupGradeTab() {
        JPanel topPanel = new JPanel();
        gradeStudentDropdown = new JComboBox<>();
        gradeCourseDropdown = new JComboBox<>(courses);
        gradeField = new JTextField(3);

        topPanel.add(new JLabel("Select Student:"));
        topPanel.add(gradeStudentDropdown);
        topPanel.add(new JLabel("Select Course:"));
        topPanel.add(gradeCourseDropdown);
        topPanel.add(new JLabel("Grade:"));
        topPanel.add(gradeField);

        JButton assignGradeButton = new JButton("Assign Grade");
        assignGradeButton.addActionListener(e -> {
            try {
                String studentName = (String) gradeStudentDropdown.getSelectedItem();
                String courseName = (String) gradeCourseDropdown.getSelectedItem();
                String grade = gradeField.getText();
                if (studentName == null || courseName == null || grade.isEmpty()) {
                    throw new IllegalArgumentException("Please fill all fields to assign a grade.");
                }
                for (Student s : students) {
                    if (s.getName().equals(studentName)) {
                        if (!s.getCourses().contains(courseName)) {
                            throw new IllegalArgumentException("Student is not enrolled in the selected course.");
                        }
                        s.assignGrade(courseName, grade);
                        updateGradeDisplay();
                        return;
                    }
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Grade Assignment Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        topPanel.add(assignGradeButton);

        gradeDisplay = new JTextArea(10, 50);
        gradeDisplay.setEditable(false);

        gradePanel.add(topPanel, BorderLayout.NORTH);
        gradePanel.add(new JScrollPane(gradeDisplay), BorderLayout.CENTER);
    }

    private void updateStudentTable() {
        studentTableModel.setRowCount(0);
        for (Student s : students) {
            studentTableModel.addRow(new Object[]{s.getName(), s.getAge()});
        }
    }

    private void updateDropdowns() {
        studentDropdown.removeAllItems();
        gradeStudentDropdown.removeAllItems();
        for (Student s : students) {
            studentDropdown.addItem(s.getName());
            gradeStudentDropdown.addItem(s.getName());
        }
    }

    private void updateEnrollmentDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            sb.append(s.getName()).append(" enrolled in: ").append(s.getCourses()).append("\n");
        }
        enrollmentDisplay.setText(sb.toString());
    }

    private void updateGradeDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            sb.append(s.getName()).append(" grades: ").append(s.getGrades()).append("\n");
        }
        gradeDisplay.setText(sb.toString());
    }

    public static void main(String[] args) {
        new StudentManagementSystem();
    }
}

class Student {
    private String name;
    private int age;
    private ArrayList<String> courses = new ArrayList<>();
    private java.util.HashMap<String, String> grades = new java.util.HashMap<>();

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void enrollInCourse(String course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void assignGrade(String course, String grade) {
        grades.put(course, grade);
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public java.util.HashMap<String, String> getGrades() {
        return grades;
    }
}
