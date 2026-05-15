package adris.altoclef;

public class TaskCatalogue {
    private static TaskCatalogue instance;

    public TaskCatalogue() {
        if (instance == null) {
            instance = this;
        }
    }
}
