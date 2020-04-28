// Authors: Cody Bishop, Scott Baker, Tanner Elphee, Harry Brown, and Austin Lindsey
// Dr. Ezell | CIS 331
// Purpose: Main Method for the Applicaiton 
//  Link to JMU OneDrive: https://dukesjmuedu-my.sharepoint.com/:f:/g/personal/elpheeti_dukes_jmu_edu/Er7HS_yJyotJnm-GCDn661sBVS7mJo0PJ3uKvEESinPfwA?e=t1WDck
//Please connect to database shenu to work with this application. See OneDrive for details. 

// Use MenuOrder example as reference
// Assignment of Duties
//   Phase 1 Build GUI - Austin, Tanner, William
//   Phase 2 Develop the new needed methods(most difficult) - Cody, Harry
//   Phase 3 Prepare Database files - William, Tanner, Austin
//   Phase 4 Develop Database saving feature (Phase 3) - Harry, Cody, Austin, Tanner, William


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.event.*;

// For ArrayList
import java.util.*;

// These enable ComboBoxes
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

//These are needed for Oracle SQL database bridge provided by Dr.Ezell
import java.sql.*;
import oracle.jdbc.pool.*; //Error shown until Database is connected. 


public class SMSApp_v4 extends Application {
    // Our Database Connection method needs these 
    // objects. We declare them here and point them
    // to instance objects below.
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
