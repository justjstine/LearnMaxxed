package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Billings {

    private final SimpleIntegerProperty userId;
    private final SimpleIntegerProperty transactionID;
    private final SimpleStringProperty transactionDate;
    private final SimpleStringProperty strand;
    private final SimpleIntegerProperty subscriptionID;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;

    public Billings(int userId, int transactionID, String transactionDate, String strand, int subscriptionID, String firstName, String lastName, String email) {
        this.userId = new SimpleIntegerProperty(userId);
        this.transactionID = new SimpleIntegerProperty(transactionID);
        this.transactionDate = new SimpleStringProperty(transactionDate);
        this.strand = new SimpleStringProperty(strand);
        this.subscriptionID = new SimpleIntegerProperty(subscriptionID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
    }

    public int getUserId() {
        return userId.get();
    }

    public int getTransactionID() {
        return transactionID.get();
    }

    public String getTransactionDate() {
        return transactionDate.get();
    }

    public String getStrand() {
        return strand.get();
    }

    public int getSubscriptionID() {
        return subscriptionID.get();
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
}