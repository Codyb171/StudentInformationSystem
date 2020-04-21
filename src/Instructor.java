public class Instructor
{
    private String name;
    private String prefix;
    private String officeLocation; 
    private String department;
    private String email;
    private int instructorID;
    private int nextInstructID;

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
        this.email = email;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }
    public void setInstructorID(int ID)
    {
        instructorID = ID;
    }
    //public void getTitle()
    
    //toString method prints our
        //Name
        //Department
        //Office
        //Email
    
    public String toString()
    {
        String formattedString = "";
        return formattedString;
    }
}





