package Utils;

import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class StageManager {
    private static final List<Stage> stages = new ArrayList<>();

    public static void register(Stage stage) {
        stages.add(stage);
        stage.setOnHidden(e -> stages.remove(stage));
    }

    public static void closeAllStages() {
        for (Stage stage : new ArrayList<>(stages)) {
            stage.close();
        }
        stages.clear();
    }

    public static List<Stage> getStages() {
        return new ArrayList<>(stages);
    }
}
