package User.Controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import Database.DatabaseHandler;
import Utils.StageManager;
import Data.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PremiumToFreeController {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private ComboBox<String> subscriptionCombo;

    @FXML
    private JFXButton updateSubscriptionButton;

    @FXML
    void initialize() {
        subscriptionCombo.getItems().add("Free");
        subscriptionCombo.getSelectionModel().selectFirst();
    }

    @FXML
    void changetoFreeButtonHandler(ActionEvent event) {
    String selectedPlan = subscriptionCombo.getValue();

    if (!"Free".equalsIgnoreCase(selectedPlan)) {
        showAlert(AlertType.WARNING, "Please select the Free plan to continue.");
        return;
    }

    int userId = Session.getLoggedInStudent().getUserID();

    boolean success = DatabaseHandler.updateUserSubscriptionStatus(userId, "Free");

    if (success) {
        // Clear session
        Session.clearSession();

        // Inform user
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Subscription Downgraded");
        alert.setHeaderText(null);
        alert.setContentText("Your subscription has been changed to Free. You will be redirected to the login page.");
        alert.showAndWait();

        // Redirect to login page
        redirectToLogin(event);

    } else {
        showAlert(AlertType.ERROR, "Downgrade failed. Please try again.");
    }
}

@FXML
private void redirectToLogin(ActionEvent event) {
    try {
        // Close all currently open stages safely
        StageManager.closeAllStages();

        // Load the login FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login/FXML/LoginPage.fxml"));
        Parent root = loader.load();

        // Create a new stage for the login page
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root, 1000, 600));
        loginStage.show();

        // Register the new stage so it's tracked
        StageManager.register(loginStage);

    } catch (IOException e) {
        e.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Could not load login page. Please restart the application.");
    }
}

    private void showAlert(AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(type.name());
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
