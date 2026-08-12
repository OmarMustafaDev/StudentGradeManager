package model;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private final String studentId;
    private String name;
    private final Map<Course , String> studentCourses = new HashMap<>();

    public Student(String studentId , String name){
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be null or blank!");
        };
        this.studentId = studentId;
        setName(name);
    }


    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be null or blank!");
        }
        if (name.contains(",")) {
            throw new IllegalArgumentException("Student name cannot contain commas!");
        }
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void addCourse(Course course , String grade){
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null!");
        }
        if (grade == null || grade.isBlank()) {
            throw new IllegalArgumentException("Grade cannot be null or blank!");
        }
        studentCourses.put(course, grade);
    }

    public Map<Course , String> getCourses(){
        return Map.copyOf(studentCourses);
    }

}