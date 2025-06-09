package LearningMaterials.STEM.PRECAL.Controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class pCalIntroController {
     @FXML
    private Button dashboardButton;

    @FXML
    private Button logoutButton;

    @FXML
    private JFXButton nextButton;

    @FXML
    private Button physics1Button;

    @FXML
    private Button physics2Button;

    @FXML
    private Button physics3Button;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private JFXComboBox<String> subjectComboBox;

    @FXML
    public void initialize() {
        Platform.runLater(() -> scrollPane.setVvalue(0));
        subjectComboBox.getItems().clear();
        subjectComboBox.getItems().addAll("Chemistry", "Physics", "Biology", "Pre Calculus", "Basic Calculus");
    }

    @FXML
    public void logoutButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent logoutRoot = javafx.fxml.FXMLLoader.load(getClass().getResource("/Login/FXML/LoginPage.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(logoutRoot, 1000, 600));
    }

    @FXML
    public void dashboardButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent dashboardRoot = javafx.fxml.FXMLLoader.load(getClass().getResource("/User/FXML/StemDashboard.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(dashboardRoot, 1000, 600));
    }
    
    @FXML
    public void handleSubjectSelection() {
        String selected = subjectComboBox.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        try {
            if ("Chemistry".equals(selected)) {
                Parent chemistryRoot = FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/Chemistry/FXML/ChemChapter1.fxml"));
                stage.setScene(new Scene(chemistryRoot, 1000, 600));
            } else if ("Physics".equals(selected)) {
                Parent physicsRoot = FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/Physics/FXML/PhysicsIntro.fxml"));
                stage.setScene(new Scene(physicsRoot, 1000, 600));
            } else if ("Biology".equals(selected)) {
                Parent biologyRoot = FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/Biology/FXML/BioIntro.fxml"));
                stage.setScene(new Scene(biologyRoot, 1000, 600));
            }else if ("Pre Calculus".equals(selected)) {
                Parent biologyRoot = FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/Precal/FXML/PCalIntro.fxml"));
                stage.setScene(new Scene(biologyRoot, 1000, 600));
            }else if ("Basic Calculus".equals(selected)) {
                Parent biologyRoot = FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/Basiccal/FXML/BasicCalIntro.fxml"));
                stage.setScene(new Scene(biologyRoot, 1000, 600));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void nextButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent nextRoot = javafx.fxml.FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/PreCal/FXML/PCalChap1.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(nextRoot, 1000, 600));
    }

    @FXML
    public void pCal1ButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent pCal1Root = javafx.fxml.FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/PreCal/FXML/PCalChap1.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(pCal1Root, 1000, 600));
    }

    @FXML
    public void pCal2ButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent pCal2Root = javafx.fxml.FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/PreCal/FXML/PCalChap2.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(pCal2Root, 1000, 600));
    }

    @FXML
    public void pCal3ButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent pCal3Root = javafx.fxml.FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/PreCal/FXML/PCalChap3.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(pCal3Root, 1000, 600));
    }

    public void scrollToTop() {
        if (scrollPane != null) {
        scrollPane.setVvalue(0);
        }
    }   
}
