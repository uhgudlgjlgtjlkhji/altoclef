package adris.altoclef.tasksystem;

import net.minecraft.client.MinecraftClient;

public abstract class Task {
    private final String name;
    private boolean completed = false;
    private boolean failed = false;

    public Task(String name) {
        this.name = name;
    }

    public abstract Result tick();

    public String getName() { return name; }
    public boolean isCompleted() { return completed; }
    public boolean isFailed() { return failed; }

    public void setCompleted() { this.completed = true; }
    public void setFailed() { this.failed = true; }

    protected MinecraftClient getClient() {
        return MinecraftClient.getInstance();
    }

    public enum Result {
        SUCCESS,
        FAILURE,
        CONTINUE
    }
}
