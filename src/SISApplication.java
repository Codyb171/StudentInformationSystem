import java.util.ArrayList;
import java.util.Scanner;

public class SISApplication
{
    public static ArrayList<Student> studentArray = new ArrayList<>();
    public static ArrayList<Course> courseArray = new ArrayList<>();
    public static ArrayList<Instructor> instructorArray = new ArrayList<>();
    public static int studentID = 1000;
    public static int instructorID =100000;
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args)
    {     int inUse = 0;
          int choice;
          while (inUse == 0)
          {
              printMenu();
              choice = input.nextInt();
              if (choice == 1)
              {
                  courseCreate();
              }
              if(choice == 2)
              {
                  createStudent();
              }
              if(choice == 3)
              {
                  createInstructor();
              }
              if(choice == 4)
              {
                  addStudent();
              }
              if (choice == 5)
              {
                  removeStudent();
              }
              if(choice == 6)
              {
                  assignInstructor();
              }
              if(choice == 7)
              {
                  printRoster();
              }
              if(choice == 8)
              {
                  inUse = 1;
                  System.out.println("Exiting. . . \n" +
                          "Goodbye" );
              }
          }
    }
    public static void printMenu()
    { System.out.println("Student Management System \n " +
            "Please make a menu choice below:");
        for(int i = 0; i< 33; i++)
        {
            System.out.print("-");
        }
        System.out.println(
                "1: Create a Course \n" +
                "2: Create a Student \n" +
                "3: Create Instructor \n" +
                "4: Add a Student to a Course \n" +
                "5: Remove a Student from a Course \n" +
                "6: Assign an Instructor to a Course \n" +
                "7: Print a Roster for a Course \n" +
                "8: Exit System");
        System.out.print("Choice: ");
    }
    public static void courseCreate()
    {
        String courseName;
        String building;
        String roomNumber;
        int roomCap;
        System.out.println("Creating a Course...");
        System.out.print("Enter The Course Name: ");
        courseName = input.nextLine();
        System.out.print("\nEnter the Building Name: ");
        building = input.nextLine();
        System.out.print("\nEnter the Room Number: ");
        roomNumber = input.nextLine();
        System.out.print("\nEnter the Room Capacity : ");
        roomCap = input.nextInt();
        courseArray.add(new Course(courseName, building, roomNumber, roomCap));
    }
    public static void createStudent()
    {
        String name;
        int year;
        String major;
        double GPA;
        String email;
        System.out.println("Enter Student Info: ");
        System.out.print("Name (FirstName LastName): ");
        name = input.nextLine();
        System.out.print("\nEnter Student Major: ");
        major = input.nextLine();
        System.out.print("\n");
    }
    public static void createInstructor()
    {
        //add instructor info
    }
    public static void addStudent()
    {
        //add student to course
    }
    public static void removeStudent()
    {
        //remove student from course
    }
    public static void assignInstructor()
    {
        //add instructor assignment
    }
    public static void printRoster()
    {
        //add printout of roster
    }
}

