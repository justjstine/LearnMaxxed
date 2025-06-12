package User.Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Database.DatabaseHandler;
import Data.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.application.Platform;

public class IctFreeUserToPremController implements Initializable {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private ComboBox<String> paymentCombo;

    @FXML
    private ComboBox<String> subscriptionCombo;

    @FXML
    private JFXButton updateSubscriptionButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPaymentMethods();
        loadPlanTypes();
    }

    private void loadPaymentMethods() {
        ObservableList<String> paymentMethods = FXCollections.observableArrayList();
        List<String> methods = DatabaseHandler.getPaymentMethods();
        paymentMethods.addAll(methods);
        paymentCombo.setItems(paymentMethods);
    }

    private void loadPlanTypes() {
        ObservableList<String> planTypes = FXCollections.observableArrayList("Subscribed"); // Fixed plan type
        subscriptionCombo.setItems(planTypes);
        subscriptionCombo.getSelectionModel().selectFirst(); 
    }

    @FXML
private void changetoPremiumButtonHandler(ActionEvent event) {
    String selectedPlan = subscriptionCombo.getValue();
    String selectedPayment = paymentCombo.getValue();

    if (!"Subscribed".equalsIgnoreCase(selectedPlan)) {
        showAlert(AlertType.ERROR, "Only the 'Subscribed' plan is available.");
        return;
    }

    if (selectedPayment == null || selectedPayment.isEmpty()) {
        showAlert(AlertType.WARNING, "Please select a payment method.");
        return;
    }

    int userId = Session.getLoggedInStudent().getUserID();
    boolean success = DatabaseHandler.updateUserSubscriptionStatus(userId, "Subscribed");

    if (success) {
        Session.clearSession();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Subscription Updated");
        alert.setHeaderText(null);
        alert.setContentText("Your account has been upgraded. The application will now close.");
        alert.showAndWait();

        // ✅ Immediately terminate the application without crashing
        System.exit(0);

    } else {
        showAlert(AlertType.ERROR, "Upgrade failed. Please try again.");
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
