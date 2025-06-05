package Data;

public class Session {
    private static Students loggedInStudent;

    public static void setLoggedInStudent(Students student) {
        loggedInStudent = student;
    }

    public static Students getLoggedInStudent() {
        return loggedInStudent;
    }

    public static void clearSession() {
        loggedInStudent = null;
    }
}
