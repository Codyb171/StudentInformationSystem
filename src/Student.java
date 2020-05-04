// Authors: Cody Bishop, Scott Baker, Tanner Elphee, Harry Brown, and Austin Lindsey
// Dr. Ezell | CIS 331
// Purpose: Student CDF
import java.util.Scanner;


// Main contributor was Scott Baker in this CDF
// All authors cohesively worked on this file
public class Student {
    Scanner errorCorrect = new Scanner(System.in);
    private String firstName;
    private String lastName;
    private String studentYear;
    private String studentMajor;
    private double GPA;
    private String studentEmail;
    private int studentID;
    //static data field to be used to track student id
    private static int nextStudentID = 1000;

    public Student(String name, int year, String major, double GPA, String email) {
        //fill constructor with member methods and increment student id
        int space = splitName(name);
        setFirstName(name.substring(0, space));
        setLastName(name.substring(space + 1));
        setStudentYear(year);
        setStudentMajor(major);
        setGPA(GPA);
        setStudentEmail(email);
        setStudentID(nextStudentID);
        nextStudentID++;
    }

    public Student(int ID, String name, int year, String major, double GPA, String email) {
        int space = splitName(name);
        setFirstName(name.substring(0, space));
        setLastName(name.substring(space + 1));
        setStudentYear(year);
        setStudentMajor(major);
        setGPA(GPA);
        setStudentEmail(email);
        nextStudentID = ID;
        setStudentID(nextStudentID);
        nextStudentID++;
    }

    public int splitName(String name) {
        //added member method to split the first and last names of any entry
        int where = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.startsWith(" ", i)) {
                where = i;
            }
        }

        return where;
    }

    public void setFirstName(String first) {
        firstName = first;
    }

    public void setLastName(String last) {
        lastName=last;
    }

    public void setGPA(double NewGPA) {
        //test that GPA is between 0-5 to be valid
        int test = 0;
        while (test == 0) {
            if (NewGPA >= 0.0) {
                if (NewGPA <= 5.0) {
                    test = 1;
                }

            }
            if (test == 0) {
                System.out.print("Invalid GPA, Must between 0.0 - 5.0, Please Re-enter: ");
                NewGPA = errorCorrect.nextDouble();
            }
        }
        this.GPA = NewGPA;
    }

    public double getGPA() {
        return this.GPA;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }


    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentEmail(String email) {
        //test that emails have an @ symbol
        int test = 0;
        while (test == 0) {
            for (int i = 0; i < email.length(); i++) {
                if (email.startsWith("@", i)) {
                    test = 1;
                }
            }
            if (test == 0) {
                System.out.print("Invalid Email, Missing an '@', Please Re-enter: ");
                email = errorCorrect.next();
            }
        }
        studentEmail = email;
    }

    public void setStudentYear(int year) {
        //if else to match student year integer to student year string
        if(year == 1) {
            studentYear = "Freshman";
        } else if(year == 2) {
            studentYear = "Sophomore";
        } else if(year == 3) {
            studentYear = "Junior";
        } else if(year == 4) {
            studentYear = "Senior";
        }
    }

    public String getStudentYear() {
        return this.studentYear;
    }

    public String getStudentMajor() {
        return this.studentMajor;
    }

    public void setStudentMajor(String major) {
        studentMajor = major;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getFormatName() {
        return this.lastName + ", " + this.firstName;
    }

    public String toString() {
        //formatted string output for students printed within output
        String printOut;
        printOut = String.format("Student ID:%-6d Student Name: %-10s, %-15s Major: %-10s Year: %-15s",
                this.studentID, this.lastName, this.firstName, this.studentMajor, this.studentYear);
        return printOut;
    }
}

