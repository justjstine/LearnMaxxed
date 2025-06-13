package Login.Controllers;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import Database.DatabaseHandler;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController {
@FXML
    private JFXButton loginbutton;

    @FXML
    private PasswordField passwordtextfield;

    @FXML
    private Button signupbutton;

    @FXML
    private TextField usernametextfield;

    @FXML
    private TextField passwordText; 

    @FXML
    private ImageView eyeIcon;

    private Image eyeoff, eyeon;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize() {
      
        eyeoff = new Image("file:Resources/Icons/eyeoff.png");
        eyeon = new Image("file:Resources/Icons/eyeon.png");
        if (eyeIcon != null && eyeoff != null) {
            eyeIcon.setImage(eyeoff);
        }
      
        passwordText.setVisible(false);
        passwordText.setManaged(false);
        passwordtextfield.textProperty().bindBidirectional(passwordText.textProperty());
    }

    @FXML
    private void changeVisibility() {
        if (!passwordText.isVisible()) {
            passwordText.setText(passwordtextfield.getText());
            passwordText.setVisible(true);
            passwordText.setManaged(true);
            passwordtextfield.setVisible(false);
            passwordtextfield.setManaged(false);
            passwordText.requestFocus();
            passwordText.positionCaret(passwordText.getText().length());
            if (eyeIcon != null && eyeon != null) {
                eyeIcon.setImage(eyeon);
            }
        } else {
            passwordtextfield.setText(passwordText.getText());
            passwordtextfield.setVisible(true);
            passwordtextfield.setManaged(true);
            passwordText.setVisible(false);
            passwordText.setManaged(false);
            passwordtextfield.requestFocus();
            passwordtextfield.positionCaret(passwordtextfield.getText().length());
            if (eyeIcon != null && eyeoff != null) {
                eyeIcon.setImage(eyeoff);
            }
        }
    }
    @FXML
    public void loginbuttonHandler(ActionEvent event) throws IOException {
        String uname = usernametextfield.getText();
        String pword = passwordtextfield.isVisible() ? passwordtextfield.getText() : passwordText.getText();

        Task<Parent> loadTask = new Task<>() {
            @Override
            protected Parent call() throws Exception {
                FXMLLoader loader;
                Parent loadedRoot = null;

                if (DatabaseHandler.validateadminLogin(uname, pword)) {
                    loader = new FXMLLoader(getClass().getResource("/Admin/FXML/AdminPage.fxml"));
                    loadedRoot = loader.load();
                } else if (DatabaseHandler.usernameExists(uname)) {
                    if (DatabaseHandler.validatestudentLogin(uname, pword)) {
                        Data.Students student = DatabaseHandler.getStudentByUsername(uname);
                        if (student != null) {
                            Data.Session.setLoggedInStudent(student);
                            String strand = student.getStrand();
                            String planType = student.getPlanType();
                            int subscriptionID = student.getSubscriptionID();

                            if ("Subscribed".equalsIgnoreCase(planType) || subscriptionID == 1) {
                                loader = new FXMLLoader(getClass().getResource("/User/FXML/PremiumDashboard.fxml"));
                                loadedRoot = loader.load();
                            } else {
                                if ("STEM".equalsIgnoreCase(strand)) {
                                    loader = new FXMLLoader(getClass().getResource("/User/FXML/StemDashboard.fxml"));
                                    loadedRoot = loader.load();
                                } else if ("ICT".equalsIgnoreCase(strand)) {
                                    loader = new FXMLLoader(getClass().getResource("/User/FXML/IctDashboard.fxml"));
                                    loadedRoot = loader.load();
                                } else {
                                    throw new Exception("Your strand does not have a dashboard.");
                                }
                            }
                        } else {
                            throw new Exception("No user exists with that username.");
                        }
                    } else {
                        throw new Exception("Invalid username or password. Please try again.");
                    }
                } else {
                    throw new Exception("No user exists with that username.");
                }
                return loadedRoot;
            }
        };

        loadTask.setOnSucceeded(workerStateEvent -> {
            try {
                Parent root = loadTask.get();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
            }
        });

        loadTask.setOnFailed(workerStateEvent -> {
            Throwable ex = loadTask.getException();
            String message = ex.getMessage() != null ? ex.getMessage() : "An error occurred during login.";
            showAlert(Alert.AlertType.ERROR, "Error", message);
        });

        new Thread(loadTask).start();
    }
    
    public void signupButtonHandler(ActionEvent event) {
        try {
            Parent signupRoot = FXMLLoader.load(getClass().getResource("/Login/FXML/SignupPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(signupRoot, 1000, 600));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add this helper method if not present
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}