package User.Controllers.BadgesController;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class BadgeController {
    @FXML
    private JFXButton acceptBadge;

     @FXML
    private void handlebadgeButton() {
        ((Stage) acceptBadge.getScene().getWindow()).close();
    }

    
}
