package Login.Controllers;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import Database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
@FXML
    private JFXButton loginbutton;

    @FXML
    private PasswordField passwordtextfield;

    @FXML
    private Button signupbutton;

    @FXML
    private TextField usernametextfield;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginbuttonHandler(ActionEvent event) throws IOException {
    String uname = usernametextfield.getText();
    String pword = passwordtextfield.getText();
    FXMLLoader loader;

    if (DatabaseHandler.validateadminLogin(uname, pword)) {
        loader = new FXMLLoader(getClass().getResource("/Admin/FXML/AdminPage.fxml"));
        root = loader.load();
    } else if (DatabaseHandler.validatestudentLogin(uname, pword)) {
        Data.Students student = DatabaseHandler.getStudentByUsername(uname);
        if (student != null) {
            String strand = student.getStrand();
            String planType = student.getPlanType(); // "Subscribed" or "Free"
            int subscriptionID = student.getSubscriptionID(); // 1 for premium, 2 for free

            if ("Subscribed".equalsIgnoreCase(planType) || subscriptionID == 1) {
                // Premium user: show premium dashboard
                loader = new FXMLLoader(getClass().getResource("/User/FXML/PremiumDashboard.fxml"));
                root = loader.load();
            } else {
                // Free user: show only their strand dashboard
                if ("STEM".equalsIgnoreCase(strand)) {
                    loader = new FXMLLoader(getClass().getResource("/User/FXML/StemDashboard.fxml"));
                    root = loader.load();
                } else if ("ICT".equalsIgnoreCase(strand)) {
                    loader = new FXMLLoader(getClass().getResource("/User/FXML/IctDashboard.fxml"));
                    root = loader.load();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Access Denied");
                    alert.setHeaderText(null);
                    alert.setContentText("Your strand does not have a dashboard.");
                    alert.showAndWait();
                    return;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Student not found.");
            alert.showAndWait();
            return;
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid username or password. Please try again.");
        alert.showAndWait();
        return;
    }

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
    
    public void signupButtonHandler(ActionEvent event) {
        try {
            Parent signupRoot = FXMLLoader.load(getClass().getResource("/Login/FXML/SignupPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(signupRoot, 1000, 600));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}