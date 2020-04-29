// Authors: Cody Bishop, Scott Baker, Tanner Elphee, Harry Brown, and Austin Lindsey
// Dr. Ezell | CIS 331
// Purpose: Main Method for the Applicaiton 
//  Link to JMU OneDrive: https://dukesjmuedu-my.sharepoint.com/:f:/g/personal/elpheeti_dukes_jmu_edu/Er7HS_yJyotJnm-GCDn661sBVS7mJo0PJ3uKvEESinPfwA?e=t1WDck
//Please connect to database SHENU to work with this application. See OneDrive for details. 

// Use MenuOrder example as reference

import javafx.application.Application;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


// For ArrayList
// These enable ComboBoxes

//These are needed for Oracle SQL database bridge provided by Dr.Ezell


public class SMSApp_v4 extends Application {
    
    //Controls for only student, course, and instructor
    //Still need add student, course, and instructor buttons, and entire bottom half controls for UI
    //Student UI Elements
    TextField txtStudentName = new TextField();
    ComboBox combStudentYear = new ComboBox();
    TextField txtStudentMajor = new TextField();
    TextField txtStudentGPA = new TextField();
    TextField txtStudentEmail = new TextField();
    Label lblAddStudent = new Label("Add Student:");
    Label lblStudentName = new Label("Name: ");
    Label lblStudentYear = new Label("Year: ");
    Label lblStudentMajor = new Label("Major: ");
    Label lblStudentGPA = new Label("GPA: ");
    Label lblStudentEmail = new Label("Email: ");
    //Course UI Elements
    TextField txtCourseName = new TextField();
    ComboBox combCourseBuilding = new ComboBox();
    TextField txtCourseRoom = new TextField();
    TextField txtCourseCapacity = new TextField();
    Label lblAddCourse = new Label("Add Course:");
    Label lblCourseName = new Label("Name: ");
    Label lblCourseBuilding = new Label("Building: ");
    Label lblCourseRoom = new Label("Room: ");
    Label lblCourseCapacity = new Label("Max Capacity: ");
    //Instructor UI Elements
    TextField txtInstructorName = new TextField();
    ComboBox combInstructorPrefix = new ComboBox();
    TextField txtInstructorOffice = new TextField();
    TextField txtInstructorDepartment = new TextField();
    TextField txtInstructorEmail = new TextField();
    Label lblAddInstructor = new Label("Add Instructor");
    Label lblInstructorName = new Label("Name: ");
    Label lblInstructorPrefix = new Label("Prefix: ");
    Label lblInstructorOffice = new Label("Office: ");
    Label lblInstructorDepartment = new Label("Department: ");
    Label lblInstructorEmail = new Label("Email: ");

    
    // Our Database Connection method needs these objects.
    // We declare them here and point them to instance objects below.
    public static ArrayList<Student> studentArray = new ArrayList<>();
    public static ArrayList<Course> courseArray = new ArrayList<>();
    public static ArrayList<Instructor> instructorArray = new ArrayList<>();
    Connection dbConn;
    Statement commStmt;
    ResultSet dbResults;


    @Override
    public void start(Stage primaryStage) {

    }
    
    
    
    public static void main(String[] args) {
    launch(args);
    }
    
    
    public void sendDBCommand(String sqlQuery)
        {//DATABASE BRIDGE NETBEANS FREE FROM DR.EZELL - Refer to InventoryUI Example. 

            // Set up your connection strings
            // IF YOU ARE IN CIS330 NOW: use YOUR Oracle Username/Password
            String URL = "jdbc:oracle:thin:@localhost:1521:XE";
            String userID = "shenu"; // Change to YOUR Oracle username
            String userPASS = "shenu"; // Change to YOUR Oracle password
            OracleDataSource ds;

            // Clear Box Testing - Print each query to check SQL syntax
            //  sent to this method.
            // You can comment this line out when your program is finished
            System.out.println(sqlQuery);

            // Lets try to connect
            try
            {
                // instantiate a new data source object
                ds = new OracleDataSource();
                // Where is the database located? Web? Local?
                ds.setURL(URL);
                // Send the user/pass and get an open connection.
                dbConn = ds.getConnection(userID,userPASS);
                // When we get results
                //  -TYPE_SCROLL_SENSITIVE means if the database data changes we
                //   will see our resultset update in real time.
                //  -CONCUR_READ_ONLY means that we cannot accidentally change the
                //   data in our database by using the .update____() methods of
                //   the ResultSet class - TableView controls are impacted by
                //   this setting as well.
                commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                // We send the query to the DB. A ResultSet object is instantiated
                //  and we are returned a reference to it, that we point to from
                // dbResults.
                // Because we declared dbResults at the datafield level
                // we can see the results from anywhere in our Class.
                dbResults = commStmt.executeQuery(sqlQuery); // Sends the Query to the DB
                // The results are stored in a ResultSet structure object
                // pointed to by the reference variable dbResults
                // Because we declared this variable globally above, we can use
                // the results in any method in the class.
            }
            catch (SQLException e)
            {
                System.out.println(e.toString());
            }
        }
}//END OF CLASS
