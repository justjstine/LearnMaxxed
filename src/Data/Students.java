package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Students {
    private final SimpleIntegerProperty userID;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty username;
    private final SimpleStringProperty email;
    private final SimpleStringProperty password;
    private final SimpleStringProperty strand;
    private final SimpleIntegerProperty subscriptionID;
    private final SimpleIntegerProperty paymentID;
    private final SimpleStringProperty created;

    public Students(int userID, String firstName, String lastName, String email, String username, String password, String strand, int subscriptionID, int paymentID, String created) {
        this.userID = new SimpleIntegerProperty(userID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.strand = new SimpleStringProperty(strand);
        this.subscriptionID = new SimpleIntegerProperty(subscriptionID);
        this.paymentID = new SimpleIntegerProperty(paymentID);
        this.created = new SimpleStringProperty(created);
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

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }

    public String getStrand() {
        return strand.get();
    }

    public int getSubscriptionID() {
        return subscriptionID.get();
    }

    public int getPaymentID() {
        return paymentID.get();
    }

    public String getCreated() {
        return created.get();
    }

     public String getPlanType() {
        if (getSubscriptionID() == 1) {
            return "Subscribed";
        } else if (getSubscriptionID() == 2) {
            return "Free";
        } else {
            return "Unknown";
        }
    }

}