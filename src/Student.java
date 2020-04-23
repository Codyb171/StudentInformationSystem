import java.util.Scanner;

public class Student {
    Scanner errorCorrect = new Scanner(System.in);
    private String firstName;
    private String lastName;
    private String studentYear;
    private String studentMajor;
    private double GPA;
    private String studentEmail;
    private int studentID;
    private static int nextStudentID = 1000;

    public Student(String name, int year, String major, double GPA, String email) {
        int space = splitName(name);
        setFirstName(name.substring(0,space));
        setLastName(name.substring(space));
        setStudentYear(year);
        setStudentMajor(major);
        setGPA(GPA);
        setStudentEmail(email);
        setStudentID(nextStudentID);
        nextStudentID++;
    }

    public int splitName(String name) {
        int where =0;
        for(int i =0; i < name.length(); i++) {
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

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public double getGPA() {
        return this.GPA;
    }

    public String getName() {
        return this.firstName + this.lastName;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentEmail(String email) {
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

    public String toString() {
        String printOut;
        printOut = String.format("Student ID:%-6d Student Name: %-10s, %-15s Major: %-10s Year: %-15s",
                this.studentID, this.lastName, this.firstName, this.studentMajor, this.studentYear);
        return printOut;
    }
}

