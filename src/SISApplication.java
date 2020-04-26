import java.util.ArrayList;
import java.util.Scanner;

public class SISApplication
{
    public static ArrayList<Student> studentArray = new ArrayList<>();
    public static ArrayList<Course> courseArray = new ArrayList<>();
    public static ArrayList<Instructor> instructorArray = new ArrayList<>();

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
    {
        System.out.println("Student Management System \n" +
                "Please make a menu choice below:");
        for (int i = 0; i < 33; i++) {
            System.out.print("-");
        }
        System.out.println(
                "\n1: Create a Course \n" +
                        "2: Create a Student \n" +
                        "3: Create Instructor \n" +
                        "4: Add a Student to a Course \n" +
                        "5: Remove a Student from a Course \n" +
                        "6: Assign an Instructor to a Course \n" +
                        "7: Print a Roster for a Course \n" +
                        "8: Exit System");
        System.out.print("Choice: ");
    }
    public static void courseCreate() {
        String courseName;
        String building;
        String roomNumber;
        int roomCap;
        System.out.println("Creating a Course...");
        System.out.print("Enter The Course Name: ");
        courseName = input.next();
        System.out.print("Enter the Building Name: ");
        building = input.next();
        System.out.print("Enter the Room Number: ");
        roomNumber = input.next();
        System.out.print("Enter the Room Capacity : ");
        roomCap = input.nextInt();
        courseArray.add(new Course(courseName, building, roomNumber, roomCap));
    }
    public static void createStudent() {
        String name;
        int year;
        String major;
        double GPA;
        String email;
        System.out.println("Enter Student Info: ");
        System.out.print("Name (FirstName LastName): ");
        name = input.next();
        name += " " + input.next();
        input.hasNextLine();
        System.out.print("Major: ");
        input.hasNextLine();
        major = input.next();
        System.out.print("Year: ");
        year = input.nextInt();
        System.out.print("GPA: ");
        GPA = input.nextDouble();
        System.out.print("Email: ");
        email = input.next();
        studentArray.add(new Student(name, year, major, GPA, email));
    }
    public static void createInstructor() {
        String name;
        String prefix;
        String office;
        String depart;
        String email;
        System.out.println("Enter Instructor Info: ");
        System.out.print("Name: ");
        name = input.next();
        name += " " + input.next();
        System.out.print("Prefix: ");
        prefix = input.next();
        System.out.print("Office: ");
        office = input.next();
        office += " " + input.next();
        System.out.print("Department: ");
        depart = input.next();
        System.out.print("Email: ");
        email = input.next();
        instructorArray.add(new Instructor(name, prefix, office, depart, email));
    }
    public static void addStudent()
    {
        int test = 0;
        printClasses();
        int classID = input.nextInt();
        System.out.println("Pick a student: ");
        System.out.println("-------------------");
        for (Student student : studentArray) {
            System.out.println(student.toString());
        }
        System.out.print("Enter Student ID: ");
        int studentID = input.nextInt();
        studentID -= 1000;
        while (test == 0)
            try {
                courseArray.get(classID).addStudent(studentArray.get(studentID));
                test = 1;
            } catch (Exception e) {
                System.out.print("Invalid Student ID, Please Try again: ");
                studentID = input.nextInt() - 1000;
            }
    }
    public static void removeStudent() {
        int test = 0;
        printClasses();
        int classID = input.nextInt();
        System.out.println("Pick a student: ");
        System.out.println("-------------------");
        System.out.println(courseArray.get(classID).getRoster());
        System.out.print("Enter Student ID: ");
        int studentID = input.nextInt();
        try {
            courseArray.get(classID).removeStudent(studentID);
            test = 1;
        } catch (Exception e) {
            System.out.print("Invalid Student ID, Please Try again: ");
            studentID = input.nextInt();
        }

    }
    public static void assignInstructor() {
        int test = 0;
        printClasses();
        int classID = input.nextInt();
        for (Instructor instructor : instructorArray) {
            System.out.println(instructor.toString());
        }
        System.out.print("Enter Instructor ID: ");
        int instructorID = input.nextInt();
        instructorID -= 100000;
        while (test == 0)
            try {
                courseArray.get(classID).setCourseInstructor(instructorArray.get(instructorID));
                test = 1;
            } catch (Exception e) {
                System.out.print("Invalid Instructor ID, Please Try again: ");
                instructorID = input.nextInt();
            }
    }

    public static void printRoster() {
        printClasses();
        int classID = input.nextInt();
        System.out.println(courseArray.get(classID).getRoster());
    }

    public static void printClasses() {
        System.out.println("Please Choose a Class:");
        for (Course course : courseArray) {
            System.out.println(course.toString());
        }
        System.out.println("-------------------");
        System.out.print("Choose Class: ");
    }
}
//newest Version
