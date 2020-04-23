import java.util.Scanner;

public class Instructor {
    Scanner errorCorrect = new Scanner(System.in);
    private String name;
    private String prefix;
    private String officeLocation;
    private String department;
    private String email;
    private int instructorID;
    private static int nextInstructID = 100000;

    public Instructor(String name, String prefix, String officeLocation, String department, String email) {
        setName(name);
        setPrefix(prefix);
        setDepartment(department);
        setEmail(email);
        setOfficeLocation(officeLocation);
        setInstructorID(nextInstructID);
        nextInstructID++;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmail(String email) {
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
        this.email = email;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public void setInstructorID(int ID) {
        instructorID = ID;
    }

    public String getTitle() {
        return this.prefix;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String formattedString;
        formattedString = String.format("InstructorID: %-9s %nInstructor Name: %-2s %-20s %nDepartment: %-20s %nOffice: %-20s %nEmail: %-30s",
                this.instructorID, this.prefix, this.name, this.department, this.officeLocation, this.email);
        return formattedString;
    }
}





