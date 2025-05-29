package Login.Controllers;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;

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

        if ("admin".equals(uname) && "admin".equals(pword)) {
        loader = new FXMLLoader(getClass().getResource("/Admin/FXML/AdminPage.fxml"));
        root = loader.load();
        //AdminController aUserController = loader.getController();
        

    } else {
        Alert alert = new Alert(AlertType.ERROR);
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