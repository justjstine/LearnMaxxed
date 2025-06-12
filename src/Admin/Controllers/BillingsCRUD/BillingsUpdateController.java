package Admin.Controllers.BillingsCRUD;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class BillingsUpdateController implements Initializable {
    
    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton updateSubscriptionButton;

    @FXML
    private ComboBox<String> statusCombo;

    private int userId;


 @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusCombo.getItems().addAll("Subscribed", "Cancelled");
    }

    @FXML
    private void handleCancelButton() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

   @FXML
private void handleUpdateStatusButton() {
    String selectedStatus = statusCombo.getValue();
    if (userId == 0) {
        showAlert("Please choose a user.");
        return;
    }
    if (selectedStatus == null) {
        showAlert("Please select a status.");
        return;
    }
    boolean success = Database.DatabaseHandler.updateUserSubscriptionStatus(userId, selectedStatus);
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


