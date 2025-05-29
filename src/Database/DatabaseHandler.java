package Database;

import java.sql.*;

import Data.Students;

public class DatabaseHandler {
    private static DatabaseHandler handler = null;
    private static Statement stmt = null;
    private static PreparedStatement pstatement = null;

    public static String dburl = DatabaseCredentials.ignoreDBURL;
    public static String userName = DatabaseCredentials.ignoreUserName;
    public static String password = DatabaseCredentials.ignorePassword;

    private DatabaseHandler() {}

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    public static Connection getDBConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dburl, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = getDBConnection().createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        return result;
    }

    /////////////////////Admin Login Validation/////////////////////
    public static boolean validateadminLogin(String adminusername, String adminPassword){

        getInstance();
        String query = "SELECT * FROM Admin WHERE Username = '" + adminusername + "' AND Password = '" + adminPassword + "'";
            
        System.out.println(query);
    
        ResultSet result = handler.execQuery(query);
        try {
            if (result.next()) {
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    /////////////////////CRUD Students/////////////////////
    public static ResultSet getStudents() {
        ResultSet result = null;
        try {
            String query = "SELECT u.UserID, u.FirstName, u.LastName, u.EmailAddress, s.StrandName FROM User u JOIN Strand s ON u.StrandID = s.StrandID;";
            result = handler.execQuery(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean deleteStudent(Students student) {
        try {
            pstatement = getDBConnection().prepareStatement("DELETE FROM User WHERE UserID = ?");
            pstatement.setInt(1, student.getUserID());
            
            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
