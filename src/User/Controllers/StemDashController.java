package User.Controllers;

import Data.Students;
import Data.Session;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StemDashController {

    @FXML
    private Label date;

    @FXML
    private Label usernameSidePanel;

    @FXML
    private Label welcomebackUsername;

    @FXML
    private Button logoutButton;

    public void displayName(String name) {
        usernameSidePanel.setText(name);
    }

    @FXML
    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
        date.setText(LocalDate.now().format(formatter));

        Students student = Session.getLoggedInStudent();
        if (student != null) {
            usernameSidePanel.setText(student.getFirstName() + " " + student.getLastName());
            welcomebackUsername.setText("Welcome back, " + student.getFirstName() + "!");
        } else {
            welcomebackUsername.setText("Welcome back!");
            usernameSidePanel.setText("");
        }
    }

    @FXML
    public void logoutButtonHandler(javafx.event.ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/Login/FXML/LoginPage.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Login");
            newStage.setScene(new Scene(root, 1000, 600));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
