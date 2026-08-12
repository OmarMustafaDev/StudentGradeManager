package app;

import model.*;
import service.GradeService;
import storage.FileStorage;
import util.GpaCalculator;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void app(){
        GradeService gradeService = new GradeService();
        FileStorage fileStorage = new FileStorage();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("================================");
            System.out.println("      STUDENT GRADE MANAGER");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Add Grade");
            System.out.println("4. Remove Student");
            System.out.println("5. Remove Course");
            System.out.println("6. Calculate GPA");
            System.out.println("7. Show Transcript");
            System.out.println("8. Save Data");
            System.out.println("9. Load Data");
            System.out.println("10. Exit");
            System.out.println();
            System.out.println("Choose an option:");


            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }
            if(choice <= 0 || choice > 10){
                System.out.println("Invalid option!");
                continue;
            }


            switch (choice){
                case 1:
                    System.out.println("Enter the student Name: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter the student ID: ");
                    String studentId = scanner.nextLine();

                    try {
                        Student student = new Student(studentId, name);
                        gradeService.addStudent(student);
                        System.out.println("Student added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;
                case 2:
                    System.out.println("Enter the course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.println("Enter the course name: ");
                    String courseName = scanner.nextLine();
                    System.out.println("Enter the credit hours: ");
                    int creditHours;
                    try{
                        creditHours = Integer.parseInt(scanner.nextLine());
                    }
                    catch (NumberFormatException e){
                        System.out.println("Credit hours must be a number!");
                        break;
                    }


                    try {
                        Course course = new Course(courseCode, courseName, creditHours);
                        gradeService.addCourse(course);
                        System.out.println("Course added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;
                case 3:
                    System.out.println("Enter the Student Id: ");
                    studentId = scanner.nextLine();
                    System.out.println("Enter the course code: ");
                    courseCode = scanner.nextLine();
                    System.out.println("Enter the student's grade: ");
                    String grade = scanner.nextLine();

                    try {
                        gradeService.addGrade(studentId, courseCode, grade);
                        System.out.println("Grade added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 4:
                    try {
                        System.out.println("Enter the Student Id: ");
                        studentId = scanner.nextLine();

                        Student student = gradeService.findStudentById(studentId);

                        if (student == null) {
                            System.out.println("Student doesn't exist!");
                            break;
                        }

                        gradeService.removeStudent(student);
                        System.out.println("Student removed successfully!");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        System.out.println("Enter the course code: ");
                        courseCode = scanner.nextLine();

                        Course course = gradeService.findCourseByCode(courseCode);

                        if (course == null) {
                            System.out.println("Course doesn't exist!");
                            break;
                        }

                        gradeService.removeCourse(course);
                        System.out.println("Course removed successfully!");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        System.out.println("Enter the student id: ");
                        studentId = scanner.nextLine();

                        Student student = gradeService.findStudentById(studentId);

                        if (student == null) {
                            System.out.println("Student doesn't exist!");
                            break;
                        }

                        double gpa = GpaCalculator.calculateGpa(student);

                        System.out.println(
                                student.getName() + " : "
                                        + student.getStudentId() + " : "
                                        + gpa
                        );

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 7:
                    try{
                        System.out.println("Enter the student id: ");
                        studentId = scanner.nextLine();
                        gradeService.showTranscript(studentId);
                    }catch (IllegalArgumentException e){
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 8:
                    try {
                        Map<String, Student> savedStudents = gradeService.getStudents();
                        fileStorage.saveStudents(savedStudents);

                        Map<String, Course> savedCourses = gradeService.getCourses();
                        fileStorage.saveCourses(savedCourses);

                        fileStorage.saveGrades(savedStudents);

                        System.out.println("Data saved successfully!");

                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 9:
                    try {
                        Map<String, Student> loadedStudents = fileStorage.loadStudents();
                        Map<String, Course> loadedCourses = fileStorage.loadCourses();

                        fileStorage.loadGrades(loadedStudents, loadedCourses);

                        gradeService.loadStudents(loadedStudents);
                        gradeService.loadCourses(loadedCourses);

                        System.out.println("Data loaded successfully!");

                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;


                case 10:
                    scanner.close();
                    System.out.println("Goodbye!");
                    return;
            }

        }
    }

    public static void main(String[] args){
        Main.app();
    }
}