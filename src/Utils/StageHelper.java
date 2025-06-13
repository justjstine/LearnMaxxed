package Utils;


import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;
import java.util.stream.Collectors;

public class StageHelper {
    public static List<Stage> getStages() {
        return Window.getWindows().stream()
                .filter(w -> w instanceof Stage)
                .map(w -> (Stage) w)
                .collect(Collectors.toList());
    }
}