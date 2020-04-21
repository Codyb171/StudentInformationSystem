import java.util.ArrayList;

public class Course
{
    private String courseName;
    private String building;
    private String roomNbr;
    private int courseCapacity;
    private int courseID = 0;
    private ArrayList<Student> enrolledStudents = new ArrayList<>();
    private Instructor courseInstructor;
    private int nextCourseID = 1;

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setRoomNbr(String roomNbr) {
        this.roomNbr = roomNbr;
    }

    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setEnrolledStudents(Student newStudent) {
        enrolledStudents.add(newStudent);
    }

    public void setCourseInstructor(Instructor courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public void setNextCourseID(int nextCourseID) {
        this.nextCourseID = nextCourseID;
    }
}
