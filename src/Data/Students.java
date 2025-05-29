package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Students {
    private final SimpleIntegerProperty userID;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty strand;

    public Students(int userID, String firstName, String lastName, String email, String strand) {
        this.userID = new SimpleIntegerProperty(userID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.strand = new SimpleStringProperty(strand);
    }

    public int getUserID() {
        return userID.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getStrand() {
        return strand.get();
    }
}
