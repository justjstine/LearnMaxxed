package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    ///////////////////// Admin Login Validation /////////////////////
    public static boolean validateadminLogin(String adminusername, String adminPassword) {
        getInstance();
        String query = "SELECT * FROM Admin WHERE Username = '" + adminusername + "' AND Password = '" + adminPassword + "'";
        System.out.println(query);

        ResultSet result = handler.execQuery(query);
        try {
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    ///////////////////// Students Credentials Validation /////////////////////
    public static boolean isUsernameTaken(String username) {
        String sql = "SELECT 1 FROM User WHERE Username = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isEmailTaken(String email) {
        String sql = "SELECT 1 FROM User WHERE EmailAddress = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    ///////////////////// CRUD Students (Students, ICT, STEM) /////////////////////
    public static ResultSet getStudents() { // Get all students
        ResultSet result = null;
        try {
            String query = "SELECT u.UserID, u.FirstName, u.LastName, u.EmailAddress, u.Username, u.Password, u.Created, s.StrandName, u.SubscriptionID, u.PaymentID " +
                           "FROM User u JOIN Strand s ON u.StrandID = s.StrandID;";
            result = handler.execQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ResultSet getStudentsICT() { // Get all ICT students
        ResultSet result = null;
        try {
            String query = "SELECT u.UserID, u.FirstName, u.LastName, u.EmailAddress, u.Username, u.Password, u.Created, s.StrandName, u.SubscriptionID, u.PaymentID " +
                           "FROM User u JOIN Strand s ON u.StrandID = s.StrandID WHERE s.StrandName = 'ICT';";
            result = handler.execQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ResultSet getStudentsSTEM() { // Get all STEM students
        ResultSet result = null;
        try {
            String query = "SELECT u.UserID, u.FirstName, u.LastName, u.EmailAddress, u.Username, u.Password, u.Created, s.StrandName, u.SubscriptionID, u.PaymentID " +
                           "FROM User u JOIN Strand s ON u.StrandID = s.StrandID WHERE s.StrandName = 'STEM';";
            result = handler.execQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean createStudent(Students student) {
        String sql = "INSERT INTO User (FirstName, LastName, EmailAddress, Username, Password, StrandID, SubscriptionID, PaymentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getDBConnection();
             PreparedStatement pstatement = conn.prepareStatement(sql)) {
            pstatement.setString(1, student.getFirstName());
            pstatement.setString(2, student.getLastName());
            pstatement.setString(3, student.getEmail());
            pstatement.setString(4, student.getUsername());
            pstatement.setString(5, student.getPassword());
            pstatement.setString(6, student.getStrand());
            pstatement.setInt(7, student.getSubscriptionID());
            if (student.getPaymentID() == -1) {
                pstatement.setNull(8, java.sql.Types.INTEGER);
            } else {
                pstatement.setInt(8, student.getPaymentID());
            }

            int res = pstatement.executeUpdate();
            return res > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateStudent(Students student) {
        String sql = "UPDATE User SET FirstName = ?, LastName = ?, EmailAddress = ?, Username = ?, Password = ?, StrandID = ?, SubscriptionID = ?, PaymentID = ? WHERE UserID = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement pstatement = conn.prepareStatement(sql)) {
            pstatement.setString(1, student.getFirstName());
            pstatement.setString(2, student.getLastName());
            pstatement.setString(3, student.getEmail());
            pstatement.setString(4, student.getUsername());
            pstatement.setString(5, student.getPassword());
            pstatement.setString(6, student.getStrand());
            pstatement.setInt(7, student.getSubscriptionID());
            if (student.getPaymentID() == -1) {
                pstatement.setNull(8, java.sql.Types.INTEGER);
            } else {
                pstatement.setInt(8, student.getPaymentID());
            }
            pstatement.setInt(9, student.getUserID());

            int res = pstatement.executeUpdate();
            return res > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

    /////////////////// Get Payment, Subscription, Strands | SubscriptionIDByPlanType, StrandIDByName /////////////////////
    public static List<String> getPaymentMethods() {
        List<String> paymentMethods = new ArrayList<>();
        String sql = "SELECT PaymentMethod FROM Payment";
        try (Connection conn = getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                paymentMethods.add(rs.getString("PaymentMethod"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentMethods;
    }

    public static List<String> getStrands() {
        List<String> strands = new ArrayList<>();
        String sql = "SELECT StrandName FROM Strand";
        try (Connection conn = getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                strands.add(rs.getString("StrandName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strands;
    }

    public static List<String> getSubscriptionTypes() {
        List<String> plans = new ArrayList<>();
        String sql = "SELECT PlanType FROM Subscription";
        try (Connection conn = getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                plans.add(rs.getString("PlanType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plans;
    }

    // Get SubscriptionID by PlanType
    public static int getSubscriptionIDByPlanType(String planType) {
        int subscriptionID = -1;
        String sql = "SELECT SubscriptionID FROM Subscription WHERE PlanType = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, planType);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                subscriptionID = rs.getInt("SubscriptionID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptionID;
    }

    // Get StrandID by StrandName
    public static String getStrandIDByName(String strandName) {
        String sql = "SELECT StrandID FROM Strand WHERE StrandName = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, strandName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("StrandID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getPaymentIDByMethod(String paymentMethod) {
        String sql = "SELECT PaymentID FROM Payment WHERE PaymentMethod = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paymentMethod);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("PaymentID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Not found
    }

    public static String getPlanTypeBySubscriptionID(int subscriptionID) {
        String sql = "SELECT PlanType FROM Subscription WHERE SubscriptionID = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("PlanType");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getPaymentMethodByID(int paymentID) {
        String sql = "SELECT PaymentMethod FROM Payment WHERE PaymentID = ?";
        try (Connection conn = getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paymentID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("PaymentMethod");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}