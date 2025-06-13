package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Badges {

    private final SimpleIntegerProperty userBadgeID;
    private final SimpleIntegerProperty userID;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty strand;
    private final SimpleStringProperty planType;
    private final SimpleStringProperty badgeName;
    private final SimpleStringProperty dateAwarded;

    public Badges(int userBadgeID, int userID, String firstName, String lastName, 
                 String email, String strand, String planType, String badgeName, 
                 String dateAwarded) {
        this.userBadgeID = new SimpleIntegerProperty(userBadgeID);
        this.userID = new SimpleIntegerProperty(userID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.strand = new SimpleStringProperty(strand);
        this.planType = new SimpleStringProperty(planType);
        this.badgeName = new SimpleStringProperty(badgeName);
        this.dateAwarded = new SimpleStringProperty(dateAwarded);
    }

    public int getUserBadgeID() {
        return userBadgeID.get();
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

    public String getPlanType() {
        return planType.get();
    }

    public String getBadgeName() {
        return badgeName.get();
    }

    public String getDateAwarded() {
        return dateAwarded.get();
    }
}
