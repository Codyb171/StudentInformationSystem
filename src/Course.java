import java.util.ArrayList;
public class Course
{
    //Initialize and invoke 
    private String courseName;
    private String building;
    private String roomNbr;
    private int courseCapacity;
    private int courseID;
    private final ArrayList<Student> enrolledStudents = new ArrayList<>();
    private Instructor courseInstructor; 
    private static int nextCourseID = 0;
    
    //Create class    
    public Course(String courseName, String building, String roomNbr, int courseCapacity) {
        setCourseName(courseName);
        setBuilding(building);
        setRoomNbr(roomNbr);
        setCourseCapacity(courseCapacity);
        setCourseID(nextCourseID);
        courseInstructor = null;
        nextCourseID++;//Increment courseID up
    }
    //Member methods - setters
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
    
    //Member methods - adders and removers
    public void addStudent(Student newStudent) {
        enrolledStudents.add(newStudent);
    }

    public void removeStudent(int studentToRemove) {
        for (int i = 0; i < enrolledStudents.size(); i++) {
            if (enrolledStudents.get(i).getStudentID() == studentToRemove) {
                enrolledStudents.remove(i);
            }
        }
    }//End of removeStudent() 

    //Member method - set Course Instructor
    public void setCourseInstructor(Instructor courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    //Member method - 
    public String toString() {
        String formattedString;
        formattedString = String.format("Course # %-6d Course Name:%-10s Location: %-10s Room: %-4s Capacity: %-3d",
                this.courseID, this.courseName, this.building, this.roomNbr, this.courseCapacity);
        if (courseInstructor == null) {
            formattedString += ("\nCourse Instructor: None");
        } else {
            formattedString += String.format("\nCourse Instructor: %-3s %-20s", this.courseInstructor.getTitle(), this.courseInstructor.getName());
        }
        return formattedString;
    }//End of toString()

    public String getRoster() {
        String roster = "";
        if (enrolledStudents.isEmpty()) {
            roster = "No Students in course";
        } else {
            for (Student student : enrolledStudents) {
                roster += student.toString() + "\n";
            }
        }
        return roster;
    }//End of getRoster()
}//End of class 
