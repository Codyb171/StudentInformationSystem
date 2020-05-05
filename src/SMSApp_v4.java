// Authors: Cody Bishop, Scott Baker, Tanner Elphee, Harry Brown, and Austin Lindsey
// Dr. Ezell | CIS 331
// Purpose: Main Method for the Application
//  Link to JMU OneDrive: https://dukesjmuedu-my.sharepoint.com/:f:/g/personal/elpheeti_dukes_jmu_edu/Er7HS_yJyotJnm-GCDn661sBVS7mJo0PJ3uKvEESinPfwA?e=t1WDck


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


@SuppressWarnings("ALL")
public class SMSApp_v4 extends Application {

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

    //Student UI Elements-Harry
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

    //Course UI Elements-Harry
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

    //Instructor UI Elements-Harry
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
    Button butCourseEdit = new Button("Execute?");
    TextArea outputBox = new TextArea();
    public static int courseSpot = 0;
    public static int studentSpot = 0;
    public static int instructorSpot = 0;

    // Access course Roster
    RadioButton printRoster = new RadioButton("Print Roster");

    // Our Database Connection method needs these objects.
    // We declare them here and point them to instance objects below.
    public Connection dbConn;
    public Statement commStmt;
    public ResultSet dbResults;
    public String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    public OracleDataSource ds;

    //ArrayLists for all class objects
    public static ArrayList<Student> studentArray = new ArrayList<>();
    public static ArrayList<Course> courseArray = new ArrayList<>();
    public static ArrayList<Instructor> instructorArray = new ArrayList<>();

    //Declaring MASTER Database User, Password (Tanner)
    String dataBaseUser = "shenu"; //THESE ARE CASE SENSITIVE!!!!
    String dataBasePassWord = "shenu";//THESE ARE CASE SENSITIVE!!!!

    //Declaring MASTER Table name variables (Tanner)
    String studentTable = "STUDENT"; // MAKE SURE THIS IS THE TABLE YOUR ARE STORING IN
    String instructorTable = "INSTRUCTOR"; // MAKE SURE THIS IS THE TABLE YOUR ARE STORING IN
    String courseTable = "COURSE"; // MAKE SURE THIS IS THE TABLE YOUR ARE STORING IN
    String studentEnrollmentTable = "STUDENTENROLLMENT"; // MAKE SURE THIS IS THE TABLE YOUR ARE STORING IN

    // start method - setting and formatting the UI
    //      (Scott, Cody, Harry)
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

        //set the overallPane
        overallPane.setAlignment(Pos.CENTER);
        overallPane.setVgap(15);
        overallPane.add(addCoursePane, 0, 0);
        overallPane.add(addStudentPane, 1, 0);
        overallPane.add(addInstructorPane, 2, 0);
        overallPane.add(editCoursePane, 0, 2);
        overallPane.add(outputPane, 1, 2);

        //Methods for recieving data from the DataBase
        try {
            updateStudentFromDatabase();
        } catch (SQLException throwables) {
            System.out.println("error");
        }
        try {
            updateInstructorFromDatabase();
        } catch (SQLException throwables) {
            System.out.println("error");
        }
        try {
            updateCourseFromDatabase();
        } catch (SQLException throwables) {
            System.out.println("error");
        }
        try {
            updateEnrollmentFromDatabase();
        } catch (SQLException throwables) {
            System.out.println("error");
        }
        if (!courseArray.isEmpty()) {
            for (Course course : courseArray) {
                courseList.add(course.getCourseName());
                courseSpot++;
            }
        }
        if (!studentArray.isEmpty()) {
            for (Student student : studentArray) {
                studentList.add(student.getFormatName());
                studentSpot++;
            }
        }
        if (!instructorArray.isEmpty()) {
            for (Instructor instructor : instructorArray) {
                instructorList.add(instructor.instructorNameFormat());
                instructorSpot++;
            }
        }
        //end of DataBase Read in

        Scene primaryScene = new Scene(overallPane, 1100, 500);
        primaryStage.setTitle("System Management System v4.0");
        primaryStage.setScene(primaryScene);
        primaryStage.show();

        //Lambda controls for the buttons
        checkInstructor.setOnAction(e -> {
            combInstructorList.setDisable(!checkInstructor.isSelected());
        });
        rdoAddCourse.setOnAction(e ->
        {
            if (checkCourseBoxes() == 0) {
                createCourse();
                clearCourseForm();
                insertCourse(courseArray.get(courseSpot));
                courseList.add(courseSpot, courseArray.get(courseSpot).getCourseName());
                courseSpot++;
            }
        });
        rdoAddStudent.setOnAction(e -> {
            if (checkStudentBoxes() == 0) {
                createStudent();
                clearStudentForm();
                insertStudent(studentArray.get(studentSpot));
                studentList.add(studentSpot, studentArray.get(studentSpot).getFormatName());
                studentSpot++;
            }
        });
        rdoAddInstructor.setOnAction(e -> {
            if (checkInstructorBoxes() == 0) {
                createInstructor();
                clearInstructorForm();
                insertInstructor(instructorArray.get(instructorSpot));
                instructorList.add(instructorSpot, instructorArray.get(instructorSpot).instructorNameFormat());
                instructorSpot++;
            }

        });
        //Disable Choose a course drop-down if print roster is selected (Tanner and Scott)
        addOrRemove.selectedToggleProperty().addListener((observableValue, toggle, t1)
                -> combStudentList.setDisable(printRoster.isSelected()));

        butCourseEdit.setOnAction(e -> {
                if (togAddStudent.isSelected()) {
                    if (checkEditBoxes(1) == 0) {
                        int send = addStudentToCourse();
                        if (send == 0) {
                            insertEnrollment();
                        }
                    }
                    if (checkInstructor.isSelected()) {
                        if (checkEditBoxes(0) == 0) {

                            setCourseInstructor();
                        }
                    }
                    printCourseData();
                    resetEditCourseForm();
                }
                if (togRemoveStudent.isSelected()) {
                    if (checkEditBoxes(2) == 0) {
                        removeStudentFromCourse();
                        removeEnrollment();

                    }
                    if (checkInstructor.isSelected()) {
                        if (checkEditBoxes(0) == 0) {

                            setCourseInstructor();
                        }
                    }
                    printCourseData();
                    resetEditCourseForm();
                }
                if (printRoster.isSelected()) {
                    if (checkEditBoxes(3) == 0) {
                        if (checkInstructor.isSelected()) {
                            if (checkEditBoxes(0) == 0) {

                                setCourseInstructor();
                            }
                        }
                        printCourseData();
                        resetEditCourseForm();
                    }
                }
            resetEditCourseForm();
        });

    }// END OF START()


    public static void main(String[] args) {
        launch(args);
    }

    public void sendDBCommand(String sqlQuery) {
        try {
            // instantiate a new data source object
            ds = new OracleDataSource();
            ds.setURL(URL);
            dbConn = ds.getConnection(dataBaseUser, dataBasePassWord);
            commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            dbResults = commStmt.executeQuery(sqlQuery);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void createStudent() {
        //add the values of course to the array list
        String name = txtStudentName.getText();
        String major = txtStudentMajor.getText();
        String email = txtStudentEmail.getText();
        double GPA = Double.valueOf(txtStudentGPA.getText());
        int year = checkYear(String.valueOf(combStudentYear.getValue()));
        studentArray.add(new Student(name, year, major, GPA, email));
    }

    public int checkStudentBoxes() {
        //complete error checking for student
        int error = 0;
        String where = "";
        double GPA = 0.0;
        if (!txtStudentGPA.getText().equals("")) {
            GPA = Double.parseDouble(txtStudentGPA.getText());
        }
        if (txtStudentName.getText().equals("")) {
            where = "Student Name";
            error = 1;
        }
        if (combStudentYear.getValue() == null) {
            if (where.equals("")) {
                where = "Student Year";
            } else {
                where += ", Student Year";
            }
            error = 1;
        }
        if (txtStudentMajor.getText().equals("")) {
            if (where.equals("")) {
                where = "Student Major";
            } else {
                where += ", Student Major";
            }
            error = 1;
        }
        if (txtStudentGPA.getText().equals("")) {
            if (where.equals("")) {
                where = "Student GPA";
            } else {
                where += ", Student GPA";
            }
            error = 1;
        }
        if (GPA < 0.0 || GPA > 5.0) {
            if (where.equals("")) {
                where = "Invalid GPA";
            } else {
                where += ", Invalid GPA";
            }
            error = 1;
        }
        if (txtStudentEmail.getText().equals("")) {
            if (where.equals("")) {
                where = "Student email";
            } else {
                where += ", Student Email";
            }
            error = 1;
        }
        if (checkEmail(txtStudentEmail.getText()) == 0) {
            if (where.equals("")) {
                where = "Bad Email";
            } else {
                where += " and Bad Email";
            }
            error = 1;

        }
        if (error == 1) {
            outputBox.clear();
            outputBox.setText("Error Found!\n");
            outputBox.appendText("Error at " + where);
        }
        return error;
    }

    public int checkInstructorBoxes() {
        //completete error checking for instructor
        int error = 0;
        String where = "";
        if (txtInstructorName.getText().equals("")) {
            where = "Instructor Name";
            error = 1;
        }
        if (combInstructorPrefix.getValue() == null) {
            if (where.equals("")) {
                where = "Instructor Prefix";
            } else {
                where += ", Instructor Prefix";
            }
            error = 1;
        }
        if (txtInstructorOffice.getText().equals("")) {
            if (where.equals("")) {
                where = "Instructor Office";
            } else {
                where += ", Instructor Office";
            }
            error = 1;
        }
        if (txtInstructorDepartment.getText().equals("")) {
            if (where.equals("")) {
                where = "Instructor Department";
            } else {
                where += ", Instructor Department";
            }
            error = 1;
        }
        if (txtInstructorEmail.getText().equals("")) {
            if (where.equals("")) {
                where = "Instructor email";
            } else {
                where += ", Instructor Email";
            }
            error = 1;
        }
        if (checkEmail(txtInstructorEmail.getText()) == 0) {
            if (where.equals("")) {
                where = "Bad Email";
            } else {
                where += " and Bad Email";
            }
            error = 1;

        }
        if (error == 1) {
            outputBox.clear();
            outputBox.setText("Error Found!\n");
            outputBox.appendText("Errors at these locations: " + where);
        }
        return error;

    }

    public int checkCourseBoxes() {
        //complete error checking for course
        int error = 0;
        String where = "";
        if (txtCourseName.getText().equals("")) {
            where = "Course Name";
            error = 1;
        }
        if (combCourseBuilding.getValue() == null) {
            if (where.equals("")) {
                where = "Course Building";
            } else {
                where += ", Course Building";
            }
            error = 1;
        }
        if (txtCourseRoom.getText().equals("")) {
            if (where.equals("")) {
                where = "Course Room";
            } else {
                where += ", Course Room";
            }
            error = 1;
        }
        if (txtCourseCapacity.getText().equals("")) {
            if (where.equals("")) {
                where = "Course Capacity";
            } else {
                where += "And Course Capacity";
            }
            error = 1;
        }
        if (error == 1) {
            outputBox.clear();
            outputBox.setText("Error Found!\n");
            outputBox.appendText("Error at " + where);
        }
        return error;
    }

    public int checkEditBoxes(int function) {
        int error = 0;
        String where = "";
        if (function == 0) {
            if (combCourseList.getValue() == null) {
                where += " No Course Selected";
                error = 1;
            }
            if (combInstructorList.getValue() == null) {
                where += " No Instructor Selected";
                error = 1;
            }
        }
        if (function == 1 || function == 2) {
            if (combCourseList.getValue() == null) {
                where += " No Course Selected";
                error = 1;
            }
            if (combStudentList.getValue() == null) {
                where += " No Student Slected";
                error = 1;
            }
        }
        if (function == 3) {
            if (combCourseList.getValue() == null) {
                where += " No Course Selected";
                error = 1;
            }
        }
        if (error == 1) {
            outputBox.clear();
            outputBox.setText("Error Found!\n");
            outputBox.appendText("Error at the following location(s) : " + where);
        }
        return error;
    }

    public int checkEmail(String email) {
        //check that email does not start with an @
        int test = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.startsWith("@", i)) {
                test = 1;
                break;
            }
        }
        return test;
    }

    public int checkYear(String year) {
        //get the integer value for the year of the student from the string value
        int grade = 0;
        year = year.toLowerCase();
        if ("freshman".equals(year)) {
            grade = 1;
        } else if ("sophomore".equals(year)) {
            grade = 2;
        } else if ("junior".equals(year)) {
            grade = 3;
        } else if ("senior".equals(year)) {
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
        //add values of course to its array list
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
        //add the values of instructor to its array list
        String name = txtInstructorName.getText();
        String prefix = String.valueOf(combInstructorPrefix.getValue());
        String office = txtInstructorOffice.getText();
        String depart = txtInstructorDepartment.getText();
        String email = txtInstructorEmail.getText();
        instructorArray.add(new Instructor(name, prefix, office, depart, email));
    }

    public void clearInstructorForm() {
        txtInstructorName.clear();
        combInstructorPrefix.valueProperty().set(null);
        txtInstructorOffice.clear();
        txtInstructorDepartment.clear();
        txtInstructorEmail.clear();
    }

    public void insertStudent(Student newStudent) {
        // Insert student info into DB (Tanner & Cody)
        String sqlQuery = "INSERT INTO " + dataBaseUser + "." + studentTable +
                " (STUDENTID,STUFIRSTNAME,STULASTNAME,STUDENTYEAR,STUDENTMAJOR,STUDENTGPA,STUDENTEMAIL)"
                + " VALUES (";
        sqlQuery += newStudent.getStudentID() + ",";
        sqlQuery += "'" + newStudent.getFirstName() + "',";
        sqlQuery += "'" + newStudent.getLastName() + "',";
        sqlQuery += "'" + newStudent.getStudentYear() + "',";
        sqlQuery += "'" + newStudent.getStudentMajor() + "',";
        sqlQuery += newStudent.getGPA() + ",";
        sqlQuery += "'" + newStudent.getStudentEmail() + "')";

        System.out.println(sqlQuery);
        sendDBCommand(sqlQuery);
    }

    public void insertInstructor(Instructor newInstructor) {
        //send sql command to insert a row into instructor-Harry
        String sqlQuery = "INSERT INTO " + dataBaseUser + "." + instructorTable +
                " (INSTRID, INSTRNAME, INSTRPREFIX, INSTROFFICE, INSTRDEPT, INSTREMAIL)"
                + " VALUES (";
        sqlQuery += newInstructor.getInstructorID() + ",";
        sqlQuery += "'" + newInstructor.getName() + "',";
        sqlQuery += "'" + newInstructor.getPrefix() + "',";
        sqlQuery += "'" + newInstructor.getOfficeLocation() + "',";
        sqlQuery += "'" + newInstructor.getDepartment() + "',";
        sqlQuery += "'" + newInstructor.getEmail() + "')";

        sendDBCommand(sqlQuery);
    }

    public void insertCourse(Course newCourse) {
        //send sql command to insert a row into course-Harry
        String sqlQuery = "INSERT INTO " + dataBaseUser + "." + courseTable +
                " (COURSEID, COURSENAME, COURSEBLDG, COURSEROOM, COURSECAPACITY, COURSEINSTRUCTOR)"
                + " VALUES (";
        sqlQuery += newCourse.getCourseID() + ",";
        sqlQuery += "'" + newCourse.getCourseName() + "',";
        sqlQuery += "'" + newCourse.getBuilding() + "',";
        sqlQuery += "'" + newCourse.getRoomNbr() + "',";
        sqlQuery += newCourse.getCourseCapacity() + ",";
        sqlQuery += "null)";
        sendDBCommand(sqlQuery);
    }

    public void insertEnrollment() {
        String sqlQuery = "INSERT INTO " + dataBaseUser + "." + studentEnrollmentTable +
                " (COURSEID, STUDENTID)" + " VALUES (";
        sqlQuery += courseIDToEdit() + ",";
        sqlQuery += studentIDToEdit() + ")";
        sendDBCommand(sqlQuery);
    }

    public void removeEnrollment() {
        String sqlQuery = "Delete " + dataBaseUser + "." + studentEnrollmentTable + " where ";
        sqlQuery += " COURSEID = " + courseIDToEdit();
        sqlQuery += " AND STUDENTID = " + studentIDToEdit();
        sendDBCommand(sqlQuery);
    }

    public int addStudentToCourse() {
        int studentID = studentIDToEdit();
        int courseID = courseIDToEdit();
        int where = 0;
        int add = 0;
        for (int i = 0; i < studentArray.size(); i++) {
            if (studentArray.get(i).getStudentID() == studentID) {
                where = i;
            }
        }
        ArrayList<Student> courseStudents = new ArrayList<>(courseArray.get(courseID).getEnrolledStudents());
        for (int j = 0; j < courseStudents.size(); j++) {
            if (courseStudents.get(j).getStudentID() == studentID) {
                add = 1;
            }
        }
        if (add == 0) {
            courseArray.get(courseID).addStudent(studentArray.get(where));
        }
        return add;
    }

    public void removeStudentFromCourse() {
        int studentID = studentIDToEdit();
        int courseID = courseIDToEdit();
        courseArray.get(courseID).removeStudent(studentID);
    }

    public void printCourseData() {
        int courseID = courseIDToEdit();
        outputBox.clear();
        outputBox.setText(courseArray.get(courseID).toString() + "\n");
        outputBox.appendText(courseArray.get(courseID).getRoster());
    }

    public int studentIDToEdit() {
        String studentName = String.valueOf(combStudentList.getValue());
        int studentID = 1000000000;
        for (int s = 0; s < studentArray.size(); s++) {
            if (studentArray.get(s).getFormatName().equals(studentName)) {
                studentID = studentArray.get(s).getStudentID();
                break;
            }
        }
        return studentID;
    }

    public int courseIDToEdit() {
        String courseName = String.valueOf(combCourseList.getValue());
        int courseID = 1000000000;
        for (int i = 0; i < courseArray.size(); i++) {
            if (courseArray.get(i).getCourseName().equals(courseName)) {
                courseID = courseArray.get(i).getCourseID();
                break;
            }
        }
        return courseID;
    }

    public void setCourseInstructor() {
        int courseID = courseIDToEdit();
        int instructorID = instructorToUse();
        instructorID -= 100000;
        courseArray.get(courseID).setCourseInstructor(instructorArray.get(instructorID));
        instructorID += 100000;
        String sqlQuery = "UPDATE " + dataBaseUser + "." + courseTable + " SET COURSEINSTRUCTOR = ";
        sqlQuery += instructorID + " where COURSEID = " + courseID;
        sendDBCommand(sqlQuery);
    }

    public int instructorToUse() {
        String instructorName = String.valueOf(combInstructorList.getValue());
        int instructorID = 1000000;
        for (int i = 0; i < instructorArray.size(); i++) {
            if (instructorArray.get(i).instructorNameFormat().equals(instructorName)) {
                instructorID = instructorArray.get(i).getInstructorID();
                break;
            }
        }
        return instructorID;
    }

    public void resetEditCourseForm() {
        addOrRemove.selectToggle(null);
        combStudentList.valueProperty().set(null);
        combCourseList.valueProperty().set(null);
        checkInstructor.setSelected(false);
        combInstructorList.valueProperty().set(null);
        combInstructorList.setDisable(true);
    }

    // instead of using a STOP method, we've implemented a few update methods of our own
    //      (Cody, Scott, Austin)
    public void updateCourseFromDatabase() throws SQLException {
        String sqlQuery = "Select count(COURSEID) as \"AMT\" from " + dataBaseUser + "." + courseTable;
        sendDBCommand(sqlQuery);
        dbResults.next();
        int courseCount = dbResults.getInt("AMT");
        String name;
        String building;
        String room;
        int roomCap;
        String empty;
        int instructor = 0;
        for (int i = 0; i < courseCount; i++) {
            sqlQuery = "SELECT * from " + dataBaseUser + "." + courseTable + " where COURSEID = " + i;
            sendDBCommand(sqlQuery);
            dbResults.next();
            name = dbResults.getString(2);
            building = dbResults.getString(3);
            room = dbResults.getString(4);
            roomCap = dbResults.getInt(5);
            empty = dbResults.getString(6);
            courseArray.add(new Course(name, building, room, roomCap));
            if (empty != null) {
                instructor = dbResults.getInt(6) - 100000;
                courseArray.get(i).setCourseInstructor(instructorArray.get(instructor));
            }
        }
    }

    public void updateStudentFromDatabase() throws SQLException {
        String name;
        int year;
        String major;
        double GPA;
        String email;
        int ID;
        String sqlQuery = "SELECT * from " + dataBaseUser + "." + studentTable + " order by STUDENTID";
        sendDBCommand(sqlQuery);
        while (dbResults.next()) {
            ID = dbResults.getInt(1);
            name = dbResults.getString(2) + " " + dbResults.getString(3);
            year = checkYear(dbResults.getString(4));
            major = dbResults.getString(5);
            GPA = dbResults.getDouble(6);
            email = dbResults.getString(7);
            studentArray.add(new Student(ID, name, year, major, GPA, email));
        }
    }

    public void updateInstructorFromDatabase() throws SQLException {
        String sqlQuery = "Select count(INSTRID) from " + dataBaseUser + "." + instructorTable;
        sendDBCommand(sqlQuery);
        dbResults.next();
        int instructorCount = dbResults.getInt(1);
        String name;
        String prefix;
        String office;
        String depart;
        String email;
        for (int i = 0; i < instructorCount; i++) {
            sqlQuery = "SELECT * from " + dataBaseUser + "." + instructorTable + " where INSTRID = " + (i + 100000);
            sendDBCommand(sqlQuery);
            dbResults.next();
            name = dbResults.getString(2);
            prefix = dbResults.getString(3);
            office = dbResults.getString(4);
            depart = dbResults.getString(5);
            email = dbResults.getString(6);
            instructorArray.add(new Instructor(name, prefix, office, depart, email));
        }
    }

    public void updateEnrollmentFromDatabase() throws SQLException {
        String sqlQuery = "SELECT COUNT(COURSEID) FROM " + dataBaseUser + "." + studentEnrollmentTable;
        sendDBCommand(sqlQuery);
        dbResults.next();
        int courseCount = dbResults.getInt(1);
        int student;
        int location = 0;
        for (int i = 0; i < courseCount; i++) {
            sqlQuery = "SELECT * FROM " + dataBaseUser + "." + studentEnrollmentTable +
                    " WHERE COURSEID = " + i;
            sendDBCommand(sqlQuery);
            while (dbResults.next()) {
                student = dbResults.getInt(2);
                for (int j = 0; j < studentArray.size(); j++) {
                    if (studentArray.get(j).getStudentID() == student) {
                        location = j;
                    }
                }

                courseArray.get(i).addStudent(studentArray.get(location));
            }
        }

    }
}//End of SMSAPP_v4()
