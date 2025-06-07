package Admin.Controllers;


import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.CustomTextField;

import com.jfoenix.controls.JFXButton;

import Admin.Controllers.AdminCRUD.AdminCreateController;
import Admin.Controllers.AdminCRUD.AdminUpdateController; 

import Data.Admin;
import Database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class AddAdminController implements Initializable {

    @FXML
    private JFXButton adminCreateButton;

    @FXML
    private JFXButton adminDeletebutton;

    @FXML
    private JFXButton adminUpdateButton;

    @FXML
    private TableColumn<Admin, Integer> adminIDColumn;

    @FXML
    private TableColumn<Admin, String> adminUsernameColumn;

    @FXML
    private TableColumn<Admin, String> adminPasswordColumn;

    @FXML
    private Button logoutButton, billingsButton;

    @FXML
    private Button goToStudentsbutton, dashboardButton;

    @FXML
    private CustomTextField searchField;

    @FXML
    private TableView<Admin> adminTable;

    private ObservableList<Admin> adminList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        adminIDColumn.setCellValueFactory(new PropertyValueFactory<>("adminID"));
        adminUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("adminUserName"));
        adminPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("adminPassword"));

        displayAdmins();
    }

    public void displayAdmins() {
        adminList.clear();

        ResultSet result = DatabaseHandler.getInstance().execQuery("SELECT * FROM Admin");
        if (result == null) {
            System.err.println("Error: ResultSet is null. Check database connection.");
            return;
        }
        try {
            while (result.next()) {
                int adminID = result.getInt("AdminID");
                String adminUserName = result.getString("Username");
                String adminPassword = result.getString("Password");

                adminList.add(new Admin(adminID, adminUserName, adminPassword));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        adminTable.setItems(adminList);
    }

    @FXML
    private void adminCreateButtonHandler() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/FXML/adminCreatePopup.fxml"));
        Parent root = loader.load();

        AdminCreateController createController = loader.getController();
        createController.setParentController(this);
        Stage popupStage = new javafx.stage.Stage();
        popupStage.setTitle("Create Admin");
        popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        popupStage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        popupStage.setScene(new Scene(root));
        popupStage.showAndWait();

    } catch (Exception e) {
        e.printStackTrace();
    }
}   

@FXML
private void AdminUpdateButtonHandler() { // <-- lowercase 'a'
    Admin selectedAdmin = adminTable.getSelectionModel().getSelectedItem();
    if (selectedAdmin == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText(null);
        alert.setContentText("No admin selected.");
        alert.showAndWait();
        return;
    }
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/FXML/adminUpdatePopup.fxml"));
        Parent root = loader.load();

        AdminUpdateController updateController = loader.getController();
        updateController.setParentController(this);
        updateController.setAdminToUpdate(selectedAdmin);

        Stage popupStage = new Stage();
        popupStage.setTitle("Update Admin");
        popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        popupStage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        popupStage.setScene(new Scene(root));
        popupStage.showAndWait();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@FXML
private void adminDeleteButtonHandler() {
    Admin selectedAdmin = adminTable.getSelectionModel().getSelectedItem();

    if (selectedAdmin != null) {
        boolean deleted = DatabaseHandler.deleteAdmin(selectedAdmin);
        Alert alert;
        if (deleted) {
            adminList.remove(selectedAdmin);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Admin deleted successfully!");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to delete admin.");
        }
        alert.showAndWait();
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText(null);
        alert.setContentText("No admin selected.");
        alert.showAndWait();
    }
}

@FXML
private void logoutButtonHandler(javafx.event.ActionEvent event) {
    try {
        // If you have a logout button, get the stage from it, or from the event
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/Login/FXML/LoginPage.fxml"));
        Stage newStage = new Stage();
        newStage.setTitle("Login");
        newStage.setScene(new Scene(root, 1000, 600));
        newStage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @FXML 
    private void goToStudentsButtonHandler() {
         try {
            Stage stage = (Stage) goToStudentsbutton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Admin/FXML/Students.fxml"));
            stage.setTitle("Students");
            stage.setScene(new Scene(root, 1000, 600));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void dashboardButtonHandler() {
        try {
            Stage stage = (Stage) dashboardButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Admin/FXML/AdminPage.fxml"));
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root, 1000, 600));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML 
    private void goToBillingsButtonHandler() {
        try {
            Stage stage = (Stage) billingsButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Admin/FXML/BillingsAdmin.fxml"));
            stage.setTitle("Admin Billings");
            stage.setScene(new Scene(root, 1000, 600));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchFieldHandler() {
        String searchText = searchField.getText().toLowerCase();
        ObservableList<Admin> filteredList = FXCollections.observableArrayList();

        for (Admin admin : adminList) {
            if (admin.getAdminUserName().toLowerCase().contains(searchText)) {
                filteredList.add(admin);
            }
        }

        adminTable.setItems(filteredList);
    }
}