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
        setStudentYear(year);
        this.studentMajor = major;
        setGPA(GPA);
        setStudentEmail(email);
        setStudentID(nextStudentID);
        nextStudentID++;
    }
    
    public void setGPA(double GPA)
    {
        this.GPA = GPA;
    }
    
    public double getGPA()
    {
        return this.GPA;
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
    
    public String getStudentYear()
    {
        return this.studentYear;
    }
    
    public void setStudentYear(int year)
    {
        if(year == 1)
        {
            this.studentYear = "Freshman";
        }
        else if(year == 2)
        {
            this.studentYear = "Sophomore";
        }
        else if(year == 3)
        {
            this.studentYear = "Junior";
        }
        else if(year == 4)
        {
            this.studentYear = "Senior";
        }
        getStudentYear();
    }

    public String getStudentYear()
    {
        return this.StudentYear;
    }

    public String getStudentMajor()
    {
        return this.studentMajor;
    }

    public void setStudentMajor(String major)
    {
        this.major = major;
        return major;
    }
    
    public String getStudentMajor()
    {
        return this.studentMajor;
    }
    
    public void setStudentMajor(String major)
    {
        this.studentMajor = major;
    }
    
}

