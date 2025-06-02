package Admin.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminDashboardController {
    
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

    @FXML
private void addstudentButtonHandler(javafx.event.ActionEvent event) throws IOException {
    Parent studentsRoot = javafx.fxml.FXMLLoader.load(getClass().getResource("/Admin/FXML/Students.fxml"));
    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(studentsRoot, 1000, 600));
}

    @FXML
private void addadminButtonHandler(javafx.event.ActionEvent event) throws IOException {
    Parent addAdminRoot = javafx.fxml.FXMLLoader.load(getClass().getResource("/Admin/FXML/addAdmin.fxml"));
    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(addAdminRoot, 1000, 600));
}
@FXML
private void adminbillingsButtonHandler(javafx.event.ActionEvent event) throws IOException {
    Parent billingsRoot = javafx.fxml.FXMLLoader.load(getClass().getResource("/Admin/FXML/BillingsAdmin.fxml"));
    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(billingsRoot, 1000, 600));
}

}
