package Admin.Controllers.BillingsCRUD;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

public class BillingsUpdateController implements Initializable {
    
    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton updateSubscriptionButton;

    @FXML
    private ComboBox<String> statusCombo;

    @FXML
    private ComboBox<String> paymentmethodCombo;

    private int userId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusCombo.getItems().addAll("Subscribed", "Free");

        // Populate payment methods from database
        List<String> paymentMethods = Database.DatabaseHandler.getPaymentMethods();
        paymentmethodCombo.getItems().addAll(paymentMethods);
    }

    @FXML
    private void handleCancelButton() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    @FXML
    private void handleUpdateStatusButton() {
    String selectedStatus = statusCombo.getValue();
    String selectedPayment = paymentmethodCombo.getValue();

    if (userId == 0) {
        showAlert("Please choose a user.");
        return;
    }

    if (selectedStatus == null || selectedStatus.trim().isEmpty()) {
        showAlert("Please select a status.");
        return;
    }

    boolean success;

    if ("Subscribed".equalsIgnoreCase(selectedStatus)) {
        if (selectedPayment == null || selectedPayment.trim().isEmpty()) {
            showAlert("Please select a payment method.");
            return;
        }
        success = Database.DatabaseHandler.updateUserSubscriptionStatus(userId, selectedStatus, selectedPayment);

    } else if ("Free".equalsIgnoreCase(selectedStatus)) {
        // No payment required for free status
        success = Database.DatabaseHandler.updateUserSubscriptionStatus(userId, selectedStatus, "None");

    } else {
        showAlert("Invalid status selected.");
        return;
    }

    if (success) {
        showAlert("Status updated successfully!");
        ((Stage) updateSubscriptionButton.getScene().getWindow()).close();
    } else {
        showAlert("Failed to update status.");
    }
}

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}


