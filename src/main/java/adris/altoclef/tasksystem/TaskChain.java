package adris.altoclef.tasksystem;

import adris.altoclef.tasksystem.Task;

public abstract class TaskChain {
    protected final TaskRunner runner;
    protected int currentTaskIndex = 0;

    public TaskChain() {
        this.runner = new TaskRunner(this);
    }

    public TaskRunner getRunner() {
        return runner;
    }

    public abstract void registerTasks();
    public abstract void onTaskComplete(Task task);
    public abstract void onTaskFailed(Task task);
    public abstract void run();
}
