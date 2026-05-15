package adris.altoclef.tasksystem;

import adris.altoclef.AltoClef;
import adris.altoclef.Debug;

public class TaskRunner {
    private Task currentTask;
    private final TaskChain chain;

    public TaskRunner(TaskChain chain) {
        this.chain = chain;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setTask(Task task) {
        this.currentTask = task;
        AltoClef.getInstance().getBotBehaviour().setCurrentTask(task.getName());
    }

    public Task.Result tick() {
        if (currentTask == null) {
            return Task.Result.CONTINUE;
        }

        Debug.log("Ticking task: " + currentTask.getName());
        Task.Result result = currentTask.tick();

        if (result == Task.Result.SUCCESS) {
            currentTask.setCompleted();
            chain.onTaskComplete(currentTask);
            return Task.Result.SUCCESS;
        } else if (result == Task.Result.FAILURE) {
            currentTask.setFailed();
            chain.onTaskFailed(currentTask);
            return Task.Result.FAILURE;
        }

        return Task.Result.CONTINUE;
    }

    public void cancel() {
        if (currentTask != null) {
            currentTask.setFailed();
            currentTask = null;
        }
    }
}
