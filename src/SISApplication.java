import java.util.ArrayList;
import java.util.Scanner;

public class SISApplication
{
    public static ArrayList<Student> studentArray = new ArrayList<>();
    public static ArrayList<Course> courseArray = new ArrayList<>();
    public static ArrayList<Instructor> instructorArray = new ArrayList<>();
    public static int studentID = 1000;
    public static int courseNbr = 0;
    public static int instructorID =100000;
    public static void main(String[] args)
    {     int inUse = 0;
          int choice;
          String courseName;
          String building;
          String roomNumber;
          String roomCap;
          //Add info holders for student
          // Add Info holders for instructor
          Scanner input = new Scanner(System.in);
          while (inUse == 0)
          {
              printMenu();
              choice = input.nextInt();
              if (choice == 1)
              {
                  System.out.println("Creating a Course...");
                  System.out.print("Enter The Course Name: ");
                  courseName = input.nextLine();
                  System.out.println();
              }
              if(choice == 2)
              {

              }
              if(choice == 3)
              {

              }
              if(choice == 4)
              {

              }
              if (choice == 5)
              {

              }
              if(choice == 6)
              {

              }
              if(choice == 7)
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
                "3: Add a Student to a Course \n" +
                "4: Remove a Student from a Course \n" +
                "5: Assign an Instructor to a Course \n" +
                "6: Print a Roster for a Course \n" +
                "7: Exit System");
        System.out.print("Choice: ");
    }
    public static void courseCreate(int courseID)
    {

    }

}

