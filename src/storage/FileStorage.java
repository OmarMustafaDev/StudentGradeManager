package storage;

import java.io.*;

import model.*;
import java.util.Map;
import java.util.HashMap;


public class FileStorage {
    //Student
    public void saveStudents(Map<String, Student> students){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("data/students.csv"))){
            for(Map.Entry<String , Student> entry: students.entrySet()){
                String studentId = entry.getKey();
                Student student = entry.getValue();

                writer.write(studentId + "," + student.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save students",e);
        }

    }
    public Map<String, Student> loadStudents(){
        Map<String, Student> students = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("data/students.csv"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String studentId = parts[0];
                String name = parts[1];

                Student student = new Student(studentId , name);

                students.put(studentId , student);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load students",e);
        }

        return students;
    }

    //Course
    public void saveCourses(Map<String, Course> courses){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("data/courses.csv"))){
            for(Map.Entry<String , Course> entry: courses.entrySet()){
                String courseCode = entry.getKey();
                String courseName = entry.getValue().getCourseName();
                int creditHours = entry.getValue().getCreditHours();

                writer.write(courseCode + "," + courseName + "," + creditHours);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save courses",e);
        }
    }
    public Map<String, Course> loadCourses(){
        Map<String, Course> courses = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("data/courses.csv"))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String courseCode = parts[0];
                String courseName = parts[1];
                int creditHours = Integer.parseInt(parts[2]);

                Course course = new Course(courseCode , courseName , creditHours);

                courses.put(courseCode , course);
            }
        }
        catch(IOException e){
            throw new RuntimeException("Failed to load courses",e);
        }
        return courses;
    }

    //Grades
    public void saveGrades(Map<String, Student> students){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("data/grades.csv"))){
            for(Map.Entry<String , Student> entry: students.entrySet()){
                String studentId = entry.getKey();
                Student student = entry.getValue();
                Map<Course , String> courses = student.getCourses();

                for(Map.Entry<Course , String> courseEntry : courses.entrySet()){
                    Course course = courseEntry.getKey();
                    String grade = courseEntry.getValue();
                    String courseCode = course.getCourseCode();

                    writer.write(studentId + "," + courseCode + "," + grade);
                    writer.newLine();

                }
            }
        }catch (IOException e) {
            throw new RuntimeException("Failed to save grades",e);
        }
    }

    public Map<String, Student> loadGrades(Map<String, Student> students ,Map<String, Course> courses){
        try(BufferedReader reader = new BufferedReader(new FileReader("data/grades.csv"))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    throw new IllegalArgumentException(
                            "Invalid grade record: " + line
                    );
                }
                String studentId = parts[0];
                String courseCode = parts[1];
                String grade = parts[2];

                Student student = students.get(studentId);
                if (student == null) {
                    throw new IllegalArgumentException("Student not found: " + studentId);
                }
                Course course = courses.get(courseCode);
                if (course == null) {
                    throw new IllegalArgumentException("Course not found: " + courseCode);
                }


                student.addCourse(course , grade);
            }
        }
        catch(IOException e){
            throw new RuntimeException("Failed to load grades", e);
        }
        return  students;
    }



}