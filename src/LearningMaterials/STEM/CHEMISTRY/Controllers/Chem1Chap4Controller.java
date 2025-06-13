package LearningMaterials.STEM.CHEMISTRY.Controllers;

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

public class Chem1Chap4Controller {
    @FXML
    private Button dashboardButton;

    @FXML
    private Button logoutButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private JFXButton nextButton;

    @FXML
    private Button premdashboardButton;

    @FXML
    private JFXButton premnextButton;

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
        Parent logoutRoot = FXMLLoader.load(getClass().getResource("/Login/FXML/LoginPage.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(logoutRoot, 1000, 600));
    }

    @FXML
    public void dashboardButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent dashboardRoot = FXMLLoader.load(getClass().getResource("/User/FXML/StemDashboard.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(dashboardRoot, 1000, 600));
    }

    public void chem1ButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent chem1Root = FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/Chemistry/FXML/ChemChapter1.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(chem1Root, 1000, 600));
    }

    @FXML
    public void chem2ButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent chem2Root = FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/Chemistry/FXML/ChemChapter2.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(chem2Root, 1000, 600));
    }

    @FXML
    public void chem3ButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent chem3Root = javafx.fxml.FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/Chemistry/FXML/ChemChapter3.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(chem3Root, 1000, 600));
    }

    public void premchem1ButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent chem1Root = FXMLLoader.load(getClass().getResource("/LearningMaterials/Premium/ChemChapter1.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(chem1Root, 1000, 600));
    }

    @FXML
    public void premchem2ButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent chem2Root = FXMLLoader.load(getClass().getResource("/LearningMaterials/Premium/ChemChapter2.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(chem2Root, 1000, 600));
    }

    @FXML
    public void premchem3ButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent chem3Root = javafx.fxml.FXMLLoader.load(getClass().getResource("/LearningMaterials/Premium/ChemChapter3.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(chem3Root, 1000, 600));
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
        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/LearningMaterials/STEM/Chemistry/FXML/ChemChapter2.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Login");
            newStage.setScene(new Scene(root, 1000, 600));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void premDashboardButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent dashboardRoot = javafx.fxml.FXMLLoader.load(getClass().getResource("/User/FXML/PremiumDashboard.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(dashboardRoot, 1000, 600));
    }

    @FXML
    public void premNextButtonHandler(javafx.event.ActionEvent event) throws IOException {
        Parent nextRoot = javafx.fxml.FXMLLoader.load(getClass().getResource("/LearningMaterials/Premium/ChemChapter4.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(nextRoot, 1000, 600));
    }

    @FXML
    private void finishButtonHandler(javafx.event.ActionEvent event) throws IOException {
        boolean badgeAdded = false;
        boolean alreadyHasBadge = false;
        if (Data.Session.getLoggedInStudent() != null) {
            int userID = Data.Session.getLoggedInStudent().getUserID();
            badgeAdded = Database.DatabaseHandler.addBadgeIfAllowed(userID, "Chemistry");
            // Check if the user already has the badge
            if (!badgeAdded && Database.DatabaseHandler.hasBadge(userID, "Chemistry")) {
                alreadyHasBadge = true;
            }
        }

        String fxmlPath;
        if (badgeAdded) {
            fxmlPath = "/User/FXML/Congratulations.fxml";
        } else if (alreadyHasBadge) {
            fxmlPath = "/User/FXML/AlreadyHasBadge.fxml"; // Create this FXML for a custom message
        } else {
            fxmlPath = "/User/FXML/CongratulationsNoBadge.fxml";
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Congratulations!");
        popupStage.setScene(new Scene(root, 555, 333));
        popupStage.initOwner(((JFXButton) event.getSource()).getScene().getWindow());
        popupStage.initModality(javafx.stage.Modality.WINDOW_MODAL);
        popupStage.setResizable(false);
        popupStage.showAndWait();
    }

    public void scrollToTop() {
        if (scrollPane != null) {
        scrollPane.setVvalue(0);
        }
    }   
}
