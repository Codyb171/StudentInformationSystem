import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class SISApplication
{
    public static ArrayList<Student> studentArray = new ArrayList<>();
    public static ArrayList<Course> courseArray = new ArrayList<>();
    public static ArrayList<Instructor> instructorArray = new ArrayList<>();
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
        System.out.print("\nMajor: ");
        major = input.nextLine();
        System.out.print("\nYear: ");
        year = input.nextInt();
        System.out.print("\nGPA: ");
        GPA = input.nextDouble();
        System.out.print("\nEmail: ");
        email = input.nextLine();
        studentArray.add(new Student(name,year,major,GPA,email));
    }
    public static void createInstructor()
    {
        String name;
        String prefix;
        String office;
        String depart;
        String email;
        System.out.println("Enter Instructor Info: ");
        System.out.print("Name: ");
        name = input.nextLine();
        System.out.print("\nPrefix: ");
        prefix = input.nextLine();
        System.out.print("\nOffice: ");
        office = input.nextLine();
        System.out.print("\n Department: ");
        depart = input.nextLine();
        System.out.print("\nEmail: ");
        email = input.nextLine();
        instructorArray.add(new Instructor(name,prefix,office,depart,email));
    }
    public static void addStudent()
    {
        printClasses();
        int classID = input.nextInt();
        System.out.println("Pick a student: ");
        System.out.println("-------------------");
        for (Student student : studentArray)
        {
            System.out.println(student.toString());
        }
        System.out.print("Enter Student ID: ");
        int studentID = input.nextInt();

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
    //Uncomment next 2 lines and assign the correct array reference location.    
        //return String;
        //String.format("%-8s %-14s %-12s %-12s %-12s %-12s", "Day: " + this.student");
    }
    public static void printClasses()
    {
        System.out.println("Please Choose a Class:");
        for (Course course : courseArray)
        {
            System.out.println(course.toString());
        }
        System.out.println("-------------------");
        System.out.print("Choose Class: ");
    }
}

