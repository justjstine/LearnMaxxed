package Admin.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.CustomTextField;

import com.jfoenix.controls.JFXButton;

import Admin.Controllers.StudentsCRUD.StudentsCreateController;
import Data.Students;
import Database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminStudentsController implements Initializable {

    ObservableList<Students> studentsList = FXCollections.observableArrayList();

    @FXML
    private Button ictButton, stembutton, logoutButton, studentsButton;

    @FXML
    private JFXButton createButton, deletebutton;

    @FXML
    private TableView<Students> studentsTable;

    @FXML
    private TableColumn<Students, Integer> userIDColumn;

    @FXML
    private CustomTextField searchField;

    @FXML
    private TableColumn<Students, String> firstNameColumn, lastNameColumn, emailColumn, strandColumn, usernameColumn, passwordColumn, createdColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeUserIDColumn();
        initializeFirstNameColumn();
        initializeLastNameColumn();
        initializeEmailColumn();
        initializeStrandColumn();
        initializeUsernameColumn();
        initializePasswordColumn();
        initializeCreatedColumn();

        displayStudents();
    }

    private void initializeUserIDColumn() {
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    private void initializeFirstNameColumn() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    }

    private void initializeLastNameColumn() {
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    }

    private void initializeEmailColumn() {
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void initializeStrandColumn() {
        strandColumn.setCellValueFactory(new PropertyValueFactory<>("strand"));
    }

    private void initializeUsernameColumn() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    private void initializePasswordColumn() {
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void initializeCreatedColumn() {
        createdColumn.setCellValueFactory(new PropertyValueFactory<>("created"));
    }

    public void displayStudents() {
        studentsList.clear();

        ResultSet result = DatabaseHandler.getStudents();
        if (result == null) {
            System.err.println("Error: ResultSet is null. Check database connection.");
            return;
        }
        try {
            while (result.next()) {
                int userID = result.getInt("UserID");
                String firstName = result.getString("FirstName");
                String lastName = result.getString("LastName");
                String email = result.getString("EmailAddress");
                String username = result.getString("Username");
                String password = result.getString("Password");
                String created = result.getString("Created");
                String strand = result.getString("StrandName");
                int subscriptionID = result.getInt("SubscriptionID");
                int paymentID = result.getInt("PaymentID");

                studentsList.add(new Students(userID, firstName, lastName, email, username, password, strand, subscriptionID, paymentID, created));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        studentsTable.setItems(studentsList);
    }

    @FXML
    private void createButtonHandler() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/FXML/Create Popup.fxml"));
            Parent root = loader.load();

            StudentsCreateController createController = loader.getController();
            createController.setParentController(this);
            Stage popupStage = new javafx.stage.Stage();
            popupStage.setTitle("Create Student");
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateButtonHandler() {
        Students selectedStudent = studentsTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("No student selected.");
            alert.showAndWait();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/FXML/Update Popup.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the selected student and parent controller
            Admin.Controllers.StudentsCRUD.StudentsUpdateController updateController = loader.getController();
            updateController.setParentController(this);
            updateController.setStudentToUpdate(selectedStudent);

            Stage popupStage = new Stage();
            popupStage.setTitle("Update Student");
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteButtonHandler() {
        Students selectedStudent = studentsTable.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            boolean deleted = DatabaseHandler.deleteStudent(selectedStudent);
            if (deleted) {
                Alert alert = new Alert(AlertType.INFORMATION);
                studentsList.remove(selectedStudent);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Student deleted successfully!");
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert = new javafx.scene.control.Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to delete student.");
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("No student selected.");
            alert.showAndWait();
        }
    }

    @FXML
    private void searchFieldHandler() {
        String searchText = searchField.getText().toLowerCase();
        ObservableList<Students> filteredList = FXCollections.observableArrayList();

        for (Students student : studentsList) {
            if (student.getFirstName().toLowerCase().contains(searchText) ||
                student.getLastName().toLowerCase().contains(searchText) ||
                student.getEmail().toLowerCase().contains(searchText) ||
                student.getUsername().toLowerCase().contains(searchText)) {
                filteredList.add(student);
            }
        }

        studentsTable.setItems(filteredList);
    }

    @FXML 
    private void logoutButtonHandler() {
        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
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
    private void ictButtonHandler() {
        try {
            Stage stage = (Stage) ictButton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/Admin/FXML/Students ICT.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Students ICT");
            newStage.setScene(new Scene(root, 1000, 600));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void stembuttonHandler() {
        try {
            Stage stage = (Stage) stembutton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/Admin/FXML/Students STEM.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Students STEM");
            newStage.setScene(new Scene(root, 1000, 600));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}