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
}
