package adris.altoclef;

import adris.altoclef.tasksystem.Task;

public class TaskCatalogue {
    private static TaskCatalogue instance;

    public TaskCatalogue() {
        if (instance == null) {
            instance = this;
        }
    }

    public static TaskCatalogue getInstance() {
        if (instance == null) {
            instance = new TaskCatalogue();
        }
        return instance;
    }

    public Task getTask(String name) {
        // Stub - tasks will be registered here
        return null;
    }

    public void registerAllTasks() {
        // Register all task types
    }
}
