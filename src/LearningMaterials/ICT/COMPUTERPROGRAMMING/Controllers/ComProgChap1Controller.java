package LearningMaterials.ICT.COMPUTERPROGRAMMING.Controllers;
import java.io.IOException;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;


public class ComProgChap1Controller {
    @FXML
    private Button ComProg1Button;

    @FXML
    private Button ComProg2Button;

    @FXML
    private Button ComProg3Button;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button logoutButton;

    @FXML
    private JFXButton nextButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private JFXComboBox<String> subjectComboBox;

     @FXML
    public void initialize() {
        Platform.runLater(() -> scrollPane.setVvalue(0));
        subjectComboBox.getItems().clear();
        subjectComboBox.getItems().addAll("Computer Programming", "Computer Systems", "Animation", "Web Development");
    }

    @FXML
    void comprog1ButtonHandler(ActionEvent event) {

    }

    @FXML
    void comprog2ButtonHandler(ActionEvent event) {

    }

    @FXML
    void comprog3ButtonHandler(ActionEvent event) {

    }

    @FXML
    void dashboardButtonHandler(ActionEvent event) {

    }

    @FXML
    void handleSubjectSelection(ActionEvent event) {

    }

    @FXML
    void logoutButtonHandler(ActionEvent event) {

    }

    @FXML
public void nextButtonHandler(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/LearningMaterials/ICT/COMPUTERPROGRAMMING/FXML/ComProgChapter2.fxml"));
        Stage stage = (Stage) nextButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Computer Programming - Chapter 2");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}