package util;
import  java.util.Map;
import  java.util.HashMap;
import model.*;

 public class GpaCalculator {
    private static final Map<String , Double> gradeMap = new HashMap<>();

     static {
         // Populate A grades
         gradeMap.put("A+", 4.0);
         gradeMap.put("A", 4.0);
         gradeMap.put("A-", 3.7);

         // Populate B grades
         gradeMap.put("B+", 3.3);
         gradeMap.put("B", 3.0);
         gradeMap.put("B-", 2.7);

         // Populate C grades
         gradeMap.put("C+", 2.3);
         gradeMap.put("C", 2.0);
         gradeMap.put("C-", 1.7);

         // Populate D grades
         gradeMap.put("D+", 1.3);
         gradeMap.put("D", 1.0);
         gradeMap.put("D-", 0.7);

         // Populate F grade
         gradeMap.put("F" , 0.0);

     }

     public static double calculateGpa(Student student){
         if (student == null) {
             throw new IllegalArgumentException("Student cannot be null!");
         }
         Map<Course, String> courses =  student.getCourses();

         if (courses.isEmpty()) {
             throw new IllegalArgumentException("No Courses are Found!");
         }

         double totalGradePoints = 0.0;
         int totalCreditHours = 0;

         for(Map.Entry<Course , String> entry: courses.entrySet()){
             Course course = entry.getKey();
             String grade = entry.getValue();

             double gradeValue = gradeMap.get(grade);
             int creditHours = course.getCreditHours();

             totalGradePoints += gradeValue * creditHours;
             totalCreditHours += creditHours;
         }

         return totalGradePoints/totalCreditHours;

     }
}