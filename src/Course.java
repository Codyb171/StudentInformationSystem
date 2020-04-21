import java.util.ArrayList;

public class Course
{
    private String courseName;
    private String building;
    private String roomNbr;
    private int courseCapacity;
    private int courseID;
    private ArrayList<Student> enrolledStudents = new ArrayList<>();
    private Instructor courseInstructor;
    private static int nextCourseID = 0;

    public Course(String courseName, String building, String roomNbr, int courseCapacity) {
        setCourseName(courseName);
        setBuilding(building);
        setRoomNbr(roomNbr);
        setCourseCapacity(courseCapacity);
        setCourseID(nextCourseID);
        nextCourseID++;
    }

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

    public void addStudent(Student newStudent) {
        enrolledStudents.add(newStudent);
    }

    public void setCourseInstructor(Instructor courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

}
