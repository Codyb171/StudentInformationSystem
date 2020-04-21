public class Student
{
    private String firstName;
    private String lastName;
    private String studentYear;
    private String studentMajor;
    private double GPA;
    private String studentEmail;
    private int studentID;
    private static int nextStudentID = 1000;
    
    public Student(String name, int year, String major, double GPA, String email)
    {
        getName();
        setGPA(GPA);
        setStudentEmail(email);
        setStudentID(nextStudentID);
        nextStudentID++;
    }
    
    public void setGPA(double GPA)
    {
        this.GPA = GPA;
    }

    public String getName()
    {
        String name = this.firstName + this.lastName;
        return name;
    }
    
    public void setStudentID(int studentID)
    {
        this.studentID= studentID;
    }
    
    public void setStudentEmail(String email)
    {
        for(int i=0; i<email.length(); i++)
        {
            if (email.charAt(i) == '@')
            {
                System.out.println("Error: No @ has been included with the email address!");
                break;
            }
            
            this.studentEmail = email;
            //fix this possible
        }
    }
    
    public void setStudentYear(int year)
    {

    }
    
}

