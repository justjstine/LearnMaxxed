package Data;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Admin {
    
    private final SimpleIntegerProperty adminID;
    private final SimpleStringProperty adminUserName;
    private final SimpleStringProperty adminPassword;

    public Admin(int adminID, String adminUserName, String adminPassword) {
        this.adminID = new SimpleIntegerProperty(adminID);
        this.adminUserName = new SimpleStringProperty(adminUserName);
        this.adminPassword = new SimpleStringProperty(adminPassword);
    }

    public int getAdminID() {
        return adminID.get();
    }

    public String getAdminUserName() {
        return adminUserName.get();
    }

    public String getAdminPassword() {
        return adminPassword.get();
    }
}