package Admin.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Data.Students;
import Database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminStudentsController implements Initializable {

    ObservableList<Students> studentsList = FXCollections.observableArrayList();

    @FXML
    private Button billingsButton, dashboardButton, logoutButton, studentsButton;

    @FXML
    private JFXButton createButton, deletebutton, updateButton;

    @FXML
    private TableView<Students> studentsTable;

    @FXML
    private TableColumn<Students, Integer> userIDColumn;

    @FXML
    private TableColumn<Students, String> firstNameColumn, lastNameColumn, emailColumn, strandColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeUserIDColumn();
        initializeFirstNameColumn();
        initializeLastNameColumn();
        initializeEmailColumn();
        initializeStrandColumn();

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

    private void displayStudents() {
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
                String strand = result.getString("StrandName");

                studentsList.add(new Students(userID, firstName, lastName, email, strand));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        studentsTable.setItems(studentsList);
    }

    @FXML
    private void createButtonHandler() {
        // Logic to handle creating a new student
        System.out.println("Create button clicked");
    }

    @FXML
    private void updateButtonHandler() {
        // Logic to handle updating a student
        System.out.println("Update button clicked");
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
            alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
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

}