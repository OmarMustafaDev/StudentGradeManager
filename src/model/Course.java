package model;


public class Course {
    private final String courseCode;
    private String courseName;
    private int creditHours;

    public Course(String courseCode , String courseName , int creditHours){
        if(courseCode == null || courseCode.isBlank()){
            throw new IllegalArgumentException("Can't set the Course Code emtpy!");
        }
        if(courseName == null || courseName.isBlank()){
            throw new IllegalArgumentException("Can't set the Course Name emtpy!");
        }
        if(creditHours <= 0 || creditHours > 3){
            throw new IllegalArgumentException("Can't set the Credit Hours less than or equal 0 or more than 3");
        }
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }




    public void setCourseName(String courseName) {
        if(courseName == null || courseName.isBlank()){
            throw new IllegalArgumentException("Can't set the Course Name emtpy!");
        }
        this.courseName = courseName;
    }

    public void setCreditHours(int creditHours) {
        if(creditHours <= 0 || creditHours > 3){
            throw new IllegalArgumentException("Can't set the Credit Hours less than or equal 0 or more than 3");
        }
        this.creditHours = creditHours;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }


    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if (obj instanceof Course){
            Course other = (Course) obj;
            return this.courseCode.equals(other.courseCode);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return courseCode != null ? courseCode.hashCode() : 0;
    }


}