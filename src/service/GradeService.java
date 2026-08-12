package service;
import model.*;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import util.GpaCalculator;

public class GradeService {
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private static final Pattern GRADE_PATTERN = Pattern.compile("(?i)^([A-D][+-]?|F)$");

    //Student Management
    public void addStudent(Student student){
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null!");
        }
        if(findStudentById(student.getStudentId()) != null){
            throw new IllegalArgumentException("Student already exists!");
        }
        students.put(student.getStudentId(),student);
    }

    public void removeStudent(Student student){
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null!");
        }
        students.remove(student.getStudentId());
    }

    public Student findStudentById(String id){
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("ID shouldn't be blank or null!");
        }

        return students.get(id);
    }

    public Map<String, Student> getStudents(){
        return Map.copyOf(students);
    }


    //Course management
    public void addCourse(Course course){
        if(course == null){
            throw new IllegalArgumentException("Course cannot be null!");
        }
        if(findCourseByCode(course.getCourseCode()) != null){
            throw new IllegalArgumentException("Course already exists!");
        }
        courses.put(course.getCourseCode() , course);
    }

    public void removeCourse(Course course){
        if(course == null){
            throw new IllegalArgumentException("Course cannot be null!");
        }
        courses.remove(course.getCourseCode());
    }
    public Course findCourseByCode(String courseCode){
        if(courseCode == null || courseCode.isBlank()){
            throw new IllegalArgumentException("code shouldn't be blank or null!");
        }
        return courses.get(courseCode);
    }

    public Map<String, Course> getCourses(){
        return Map.copyOf(courses);
    }

    //Grade management
    public void addGrade(String studentId , String courseCode , String grade){
        if(studentId == null || studentId.isBlank()){
            throw new IllegalArgumentException("Student Id cannot be null! or blank");
        }
        Student student = findStudentById(studentId);
        if(student == null){
            throw new IllegalArgumentException("Student doesn't exist!");
        }
        if(courseCode == null || courseCode.isBlank()){
            throw new IllegalArgumentException("Course code cannot be null! or blank");
        }
        Course course = findCourseByCode(courseCode);
        if(course == null){
            throw new IllegalArgumentException("Course doesn't exist!");
        }
        if(grade == null || grade.isBlank()){
            throw new IllegalArgumentException("Grade cannot be null or blank!");
        }
        if (!GRADE_PATTERN.matcher(grade.trim()).matches()) {
            throw new IllegalArgumentException("Invalid grade format! Allowed: A to D (with optional +/-) or F.");
        }

        grade = grade.trim().toUpperCase();

        student.addCourse(course , grade);

    }

    public void showTranscript(String studentId){
        if(studentId == null || studentId.isBlank()){
            throw new IllegalArgumentException("Student Id cannot be null or blank!");
        }
        Student student = findStudentById(studentId);
        if(student == null){
            throw new IllegalArgumentException("Student doesn't exist!");
        }
        Map<Course, String> courses =  student.getCourses();

        if (courses.isEmpty()) {
            throw new IllegalArgumentException("No Courses are Found!");
        }

        double gpa = GpaCalculator.calculateGpa(student);

        System.out.println("================================");
        System.out.println("         TRANSCRIPT");
        System.out.println("================================");
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Name: " + student.getName());
        System.out.println();
        System.out.println("Course       Credits    Grade");
        System.out.println("--------------------------------");
        for(Map.Entry<Course , String> entry : courses.entrySet()){

            Course course = entry.getKey();
            String grade = entry.getValue();
            int creditHours = course.getCreditHours();

            System.out.println(course.getCourseCode() + "         " + course.getCourseName() + "         " + creditHours + "         " + grade);
        }

        System.out.println("GPA: " + gpa);
        System.out.println("================================");
    }

    public void loadStudents(Map<String, Student> students) {
        if(students == null){
            throw new IllegalArgumentException("students can't be null!");
        }
        this.students.clear();
        this.students.putAll(students);
    }

    public void loadCourses(Map<String, Course> courses) {
        if(courses == null){
            throw new IllegalArgumentException("courses can't be null!");
        }
        this.courses.clear();
        this.courses.putAll(courses);
    }


}