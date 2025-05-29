package Login.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminController {
    @FXML
    private Button addadminbutton;

    @FXML
    private Button addstudentbutton;

    @FXML
    private Button adminbillingsbutton;

    @FXML
    private Button adminstudentbutton;

    @FXML
    private Button dashboardadminbutton;

    @FXML
    private Button logoutadminbutton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
private void addstudentButtonHandler(javafx.event.ActionEvent event) throws IOException {
    Parent studentsRoot = javafx.fxml.FXMLLoader.load(getClass().getResource("/Admin/FXML/Students.fxml"));
    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(studentsRoot, 1000, 600));
}
}
