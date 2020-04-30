// Authors: Cody Bishop, Scott Baker, Tanner Elphee, Harry Brown, and Austin Lindsey
// Dr. Ezell | CIS 331
// Purpose: Main Method for the Applicaiton 
//  Link to JMU OneDrive: https://dukesjmuedu-my.sharepoint.com/:f:/g/personal/elpheeti_dukes_jmu_edu/Er7HS_yJyotJnm-GCDn661sBVS7mJo0PJ3uKvEESinPfwA?e=t1WDck
//Please connect to database SHENU to work with this application. See OneDrive for details.
//Please make sure you get the new ddl for the database and drop all your old tables

// Use MenuOrder example as reference

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;


public class SMSApp_v4 extends Application {

    //Controls for only student, course, and instructor
    //Still need add student, course, and instructor buttons, and entire bottom half controls for UI
    //Observable List for ComboBox Options
    ObservableList<String> prefix =
            FXCollections.observableArrayList("Dr.", "Ms.", "Mrs.", "Mr.");
    ObservableList<String> year =
            FXCollections.observableArrayList("Freshman", "Sophomore", "Junior", "Senior");
    ObservableList<String> buildingName =
            FXCollections.observableArrayList("Showker", "Chandler", "Burruss Hall");
    ObservableList<String> courseList =
            FXCollections.observableArrayList();
    ObservableList<String> studentList =
            FXCollections.observableArrayList();
    ObservableList<String> instructorList =
            FXCollections.observableArrayList();
    //Student UI Elements
    TextField txtStudentName = new TextField();
    ComboBox combStudentYear = new ComboBox(year);
    TextField txtStudentMajor = new TextField();
    TextField txtStudentGPA = new TextField();
    TextField txtStudentEmail = new TextField();
    Label lblAddStudent = new Label("Create a new Student:");
    Label lblStudentName = new Label("Name: ");
    Label lblStudentYear = new Label("Year: ");
    Label lblStudentMajor = new Label("Major: ");
    Label lblStudentGPA = new Label("GPA: ");
    Label lblStudentEmail = new Label("Email: ");
    Button rdoAddStudent = new Button("Add This Student");
    //Course UI Elements
    TextField txtCourseName = new TextField();
    ComboBox combCourseBuilding = new ComboBox(buildingName);
    TextField txtCourseRoom = new TextField();
    TextField txtCourseCapacity = new TextField();
    Label lblAddCourse = new Label("Create a New Course:");
    Label lblCourseName = new Label("Name: ");
    Label lblCourseBuilding = new Label("Building: ");
    Label lblCourseRoom = new Label("Room: ");
    Label lblCourseCapacity = new Label("Max Capacity: ");
    Button rdoAddCourse = new Button("Add This Course");
    //Instructor UI Elements
    TextField txtInstructorName = new TextField();
    ComboBox combInstructorPrefix = new ComboBox(prefix);
    TextField txtInstructorOffice = new TextField();
    TextField txtInstructorDepartment = new TextField();
    TextField txtInstructorEmail = new TextField();
    Label lblAddInstructor = new Label("Create a New Instructor:");
    Label lblInstructorName = new Label("Name: ");
    Label lblInstructorPrefix = new Label("Prefix: ");
    Label lblInstructorOffice = new Label("Office: ");
    Label lblInstructorDepartment = new Label("Department: ");
    Label lblInstructorEmail = new Label("Email: ");
    Button rdoAddInstructor = new Button("Add This Instructor");

    //Build Course UI Elements
    ToggleGroup addOrRemove = new ToggleGroup();
    Label lblCourseEdit = new Label("Modify a Course:");
    RadioButton togAddStudent = new RadioButton("Add Student");
    RadioButton togRemoveStudent = new RadioButton("Remove Student");
    Label lblChooseStudent = new Label("Choose a Student:");
    ComboBox combStudentList = new ComboBox(studentList);
    Label lblChooseCourse = new Label("Choose a Course:");
    ComboBox combCourseList = new ComboBox(courseList);
    CheckBox checkInstructor = new CheckBox("New Instructor?");
    Label lblInstructorWho = new Label("Instructor is:");
    ComboBox combInstructorList = new ComboBox(instructorList);
    Button butCourseEdit = new Button("Run Changes");
    TextArea outputBox = new TextArea();
    public static int courseSpot = 0;
    public static int studentSpot = 0;
    public static int instructorSpot = 0;

    // Access course Roster
    RadioButton printRoster = new RadioButton("Print Roster");
    // Our Database Connection method needs these objects.
    // We declare them here and point them to instance objects below.
    public static ArrayList<Student> studentArray = new ArrayList<>();
    public static ArrayList<Course> courseArray = new ArrayList<>();
    public static ArrayList<Instructor> instructorArray = new ArrayList<>();
    Connection dbConn;
    Statement commStmt;
    ResultSet dbResults;


    //Declaring Database table name variables (Tanner)
        String studentTable = "STUDENT"; // MAKE SURE THIS IS THE TABLE YOUR ARE STORING IN 
        String instructorTable = "INSTRUCTOR"; // MAKE SURE THIS IS THE TABLE YOUR ARE STORING IN 
        String courseTable = "COURSE"; // MAKE SURE THIS IS THE TABLE YOUR ARE STORING IN 
        String studentEnrollmentTable = "STUDENTENROLLMENT"; // MAKE SURE THIS IS THE TABLE YOUR ARE STORING IN 

    @Override
    public void start(Stage primaryStage) {
        // first we assign the Add and remove student buttons into a group to ensure they work properly
        togAddStudent.setToggleGroup(addOrRemove);
        togRemoveStudent.setToggleGroup(addOrRemove);
        printRoster.setToggleGroup(addOrRemove);

        //Create Panes for control layout
        GridPane addStudentPane = new GridPane();
        GridPane addCoursePane = new GridPane();
        GridPane addInstructorPane = new GridPane();
        GridPane overallPane = new GridPane();
        GridPane outputPane = new GridPane();
        GridPane editCoursePane = new GridPane();

        //Edit the addCoursePane
        addCoursePane.setAlignment(Pos.CENTER);
        addCoursePane.setVgap(5);
        addCoursePane.add(lblAddCourse, 0, 0);
        addCoursePane.add(lblCourseName, 0, 1);
        addCoursePane.add(txtCourseName, 1, 1);
        addCoursePane.add(lblCourseBuilding, 0, 2);
        addCoursePane.add(combCourseBuilding, 1, 2);
        addCoursePane.add(lblCourseRoom, 0, 3);
        addCoursePane.add(txtCourseRoom, 1, 3);
        addCoursePane.add(lblCourseCapacity, 0, 4);
        addCoursePane.add(txtCourseCapacity, 1, 4);
        addCoursePane.add(rdoAddCourse, 0, 5);

        //Edit the addStudentPane
        addStudentPane.setAlignment(Pos.CENTER);
        addStudentPane.setVgap(5);
        addStudentPane.add(lblAddStudent, 0, 0);
        addStudentPane.add(lblStudentName, 0, 1);
        addStudentPane.add(txtStudentName, 1, 1);
        addStudentPane.add(lblStudentYear, 0, 2);
        addStudentPane.add(combStudentYear, 1, 2);
        addStudentPane.add(lblStudentMajor, 0, 3);
        addStudentPane.add(txtStudentMajor, 1, 3);
        addStudentPane.add(lblStudentGPA, 0, 4);
        addStudentPane.add(txtStudentGPA, 1, 4);
        addStudentPane.add(lblStudentEmail, 0, 5);
        addStudentPane.add(txtStudentEmail, 1, 5);
        addStudentPane.add(rdoAddStudent, 0, 6);

        //Edit the addInstructorPane
        addInstructorPane.setAlignment(Pos.CENTER);
        addInstructorPane.setVgap(5);
        addInstructorPane.add(lblAddInstructor, 0, 0);
        addInstructorPane.add(lblInstructorName, 0, 1);
        addInstructorPane.add(txtInstructorName, 1, 1);
        addInstructorPane.add(lblInstructorPrefix, 0, 2);
        addInstructorPane.add(combInstructorPrefix, 1, 2);
        addInstructorPane.add(lblInstructorOffice, 0, 3);
        addInstructorPane.add(txtInstructorOffice, 1, 3);
        addInstructorPane.add(lblInstructorDepartment, 0, 4);
        addInstructorPane.add(txtInstructorDepartment, 1, 4);
        addInstructorPane.add(lblInstructorEmail, 0, 5);
        addInstructorPane.add(txtInstructorEmail, 1, 5);
        addInstructorPane.add(rdoAddInstructor, 0, 6);

        //Edit the editCoursePane
        editCoursePane.setAlignment(Pos.CENTER);
        editCoursePane.setVgap(5);
        editCoursePane.setMaxWidth(700);
        editCoursePane.add(lblCourseEdit, 0, 0);
        editCoursePane.add(togAddStudent, 0, 1);
        editCoursePane.add(togRemoveStudent, 1, 1);
        editCoursePane.add(printRoster, 0, 2);
        editCoursePane.add(lblChooseStudent, 0, 3);
        editCoursePane.add(combStudentList, 1, 3);
        editCoursePane.add(lblChooseCourse, 0, 4);
        editCoursePane.add(combCourseList, 1, 4);
        editCoursePane.add(checkInstructor, 0, 5);
        editCoursePane.add(lblInstructorWho, 0, 6);
        editCoursePane.add(combInstructorList, 1, 6);
        combInstructorList.setDisable(true);
        editCoursePane.add(butCourseEdit, 1, 7);
        //Edit the outputPane
        outputPane.setAlignment(Pos.CENTER);
        outputBox.setMaxWidth(500);
        outputPane.add(outputBox, 0, 0);
        //add my course roster stuff

        //set the overallPane
        overallPane.setAlignment(Pos.CENTER);
        overallPane.setVgap(15);
        overallPane.add(addCoursePane, 0, 0);
        overallPane.add(addStudentPane, 1, 0);
        overallPane.add(addInstructorPane, 2, 0);
        overallPane.add(editCoursePane, 0, 2);
        overallPane.add(outputPane, 1, 2);

        Scene primaryScene = new Scene(overallPane, 1100, 500);
        primaryStage.setTitle("System Management System v0.4");
        primaryStage.setScene(primaryScene);
        primaryStage.show();

        //Lambda controls for the buttons 
        checkInstructor.setOnAction(e -> {
            combInstructorList.setDisable(checkInstructor.isSelected() != true);
        });
        rdoAddCourse.setOnAction(e -> {
            createCourse();
            clearCourseForm();
            courseList.add(courseArray.get(courseSpot).getCourseName());
            courseSpot++;
        });
        rdoAddStudent.setOnAction(e -> {
            createStudent();
            clearStudentForm();
            showStudent();
            studentList.add(studentArray.get(studentSpot).getFormatName());
            studentSpot++;
        });
        rdoAddInstructor.setOnAction(e -> {
            createInstructor();
            clearInstructorForm();
            instructorList.add(instructorArray.get(instructorSpot).instructorNameFormat());
            instructorSpot++;
        });
         
    }// END OF START() 


    public static void main(String[] args) {
        launch(args);
    }

    public void sendDBCommand(String sqlQuery) {//DATABASE BRIDGE NETBEANS FREE FROM DR.EZELL - Refer to InventoryUI Example.

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
        try {
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
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void createStudent() {
        String name = txtStudentName.getText();
        String major = txtStudentMajor.getText();
        String email = checkEmail(txtStudentEmail.getText());
        double GPA = Double.parseDouble(txtStudentGPA.getText());
        int year = checkYear(combStudentYear.getValue());
        studentArray.add(new Student(name, year, major, GPA, email));
    }

    public String checkEmail(String email) {
        TextInputDialog alert = new TextInputDialog();
        alert.setTitle("ERROR!");
        alert.setHeaderText("Invalid Email Address found!");
        alert.setContentText("Please reenter the email and make sure to include an \"@\" symbol!");
        int test = 0;
        while (test == 0) {
            for (int i = 0; i < email.length(); i++) {
                if (email.startsWith("@", i)) {
                    test = 1;
                    break;
                }
            }
            if (test == 0) {
                Optional<String> result = alert.showAndWait();
                if (result.isPresent()) {
                    email = result.get();
                }
            }
        }
        return email;
    }

    public int checkYear(Object value) {
        String year = String.valueOf(value);
        year.toLowerCase();
        year = year.substring(0, 2);
        int grade = 0;
        if (year.equals("fr")) {
            grade = 1;
        } else if (year.equals("so")) {
            grade = 2;
        } else if (year.equals("ju")) {
            grade = 3;
        } else if (year.equals("se")) {
            grade = 4;
        }
        return grade;
    }

    public void clearStudentForm() {
        txtStudentName.clear();
        txtStudentEmail.clear();
        txtStudentMajor.clear();
        txtStudentGPA.clear();
        combStudentYear.valueProperty().set(null);
    }

    public void createCourse() {
        String courseName = txtCourseName.getText();
        String building = String.valueOf(combCourseBuilding.getValue());
        String roomNumber = txtCourseRoom.getText();
        int roomCap = Integer.parseInt(txtCourseCapacity.getText());
        courseArray.add(new Course(courseName, building, roomNumber, roomCap));
    }

    public void clearCourseForm() {
        txtCourseName.clear();
        combCourseBuilding.valueProperty().set(null);
        txtCourseRoom.clear();
        txtCourseCapacity.clear();
    }

    public void createInstructor() {
        String name = txtInstructorName.getText();
        String prefix = String.valueOf(combInstructorPrefix.getValue());
        String office = txtInstructorOffice.getText();
        String depart = txtInstructorDepartment.getText();
        String email = checkEmail(txtInstructorEmail.getText());
        instructorArray.add(new Instructor(name, prefix, office, depart, email));
    }

    public void clearInstructorForm() {
        txtInstructorName.clear();
        combInstructorPrefix.valueProperty().set(null);
        txtInstructorOffice.clear();
        txtInstructorDepartment.clear();
        txtInstructorEmail.clear();
    }
    
    public void showStudent() // NEEDS INFORMATION IN DATABSE TO REFERENCE. 
    {
        String sqlQuery = "SELECT * FROM SHENU." + studentTable; //This query can be build from text box outputs 
        sendDBCommand(sqlQuery);
        
        String outputString = "";
        try
        {
            // While there is more rows of results from the SELECT
            // query, loop on each row (.next() moves to the next
            // row each time its called)
            while (dbResults.next())
            {
                // Clear out the TextArea's previous contents
                outputBox.clear();
                // Traverse the current row of the ResultSet object
                // and extract each column, appending to our String
                outputString += dbResults.getString(1) + "\t" //get contents from the first row of the result set object. First column is 1
                        + dbResults.getString(2) + "\t" 
                        + dbResults.getString(3) + "\t"
                        + dbResults.getString(4) + "\n";
                
                // Append the outputString to the TextArea's contents.
                outputBox.appendText(outputString);
            }
        }
        catch (SQLException sqle)
        {
            outputBox.setText(sqle.toString());
        }
    }//End of showStudent()
    
    
}//End of SMSAPP_v4()
