package User.Controllers;

import com.jfoenix.controls.JFXButton;
import Database.DatabaseHandler;
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
        
        Session.clearSession();

       
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Subscription Downgraded");
        alert.setHeaderText(null);
        alert.setContentText("Your subscription has been changed to Free. Please Re-login");
        alert.showAndWait();

       
        System.exit(0);
    } else {
        showAlert(AlertType.ERROR, "Downgrade failed. Please try again.");
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
