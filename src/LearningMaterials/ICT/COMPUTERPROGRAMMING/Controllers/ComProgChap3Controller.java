package LearningMaterials.ICT.COMPUTERPROGRAMMING.Controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class ComProgChap3Controller {
    
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
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/LearningMaterials/ICT/COMPUTERPROGRAMMING/FXML/ComProgChapter1.fxml"));
        Stage stage = (Stage) ComProg1Button.getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Computer Programming - Chapter 1");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    void comprog2ButtonHandler(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/LearningMaterials/ICT/COMPUTERPROGRAMMING/FXML/ComProgChapter2.fxml"));
        Stage stage = (Stage) ComProg2Button.getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Computer Programming - Chapter 2");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    void comprog3ButtonHandler(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/LearningMaterials/ICT/COMPUTERPROGRAMMING/FXML/ComProgChapter3.fxml"));
        Stage stage = (Stage) ComProg3Button.getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Computer Programming - Chapter 3");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    public void dashboardButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent dashboardRoot = javafx.fxml.FXMLLoader.load(getClass().getResource("/User/FXML/IctDashboard.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(dashboardRoot, 1000, 600));
    }


    @FXML
public void handleSubjectSelection() {
    String selected = subjectComboBox.getSelectionModel().getSelectedItem();
    try {
        if ("Computer Programming".equals(selected)) {
            Parent root = FXMLLoader.load(getClass().getResource("/LearningMaterials/ICT/COMPUTERPROGRAMMING/FXML/ComProgIntro.fxml"));
            Stage stage = (Stage) subjectComboBox.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 600));
            stage.setTitle("Computer Programming Introduction");
            stage.show();
        } else if ("Computer Systems".equals(selected)) {
            Parent root = FXMLLoader.load(getClass().getResource("/LearningMaterials/ICT/COMPUTERSYSTEMS/FXML/ComSysIntro.fxml"));
            Stage stage = (Stage) subjectComboBox.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 600));
            stage.setTitle("Computer Systems Introduction");
            stage.show();
        } else if ("Animation".equals(selected)) {
            Parent root = FXMLLoader.load(getClass().getResource("/LearningMaterials/ICT/ANIMATION/FXML/AnimationIntro.fxml"));
            Stage stage = (Stage) subjectComboBox.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 600));
            stage.setTitle("Animation Introduction");
            stage.show();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @FXML
    public void logoutButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent logoutRoot = javafx.fxml.FXMLLoader.load(getClass().getResource("/Login/FXML/LoginPage.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(logoutRoot, 1000, 600));
    }

    @FXML
public void nextButtonHandler(ActionEvent event) {
    }
}

