package adris.altoclef;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class BotBehaviour {
    private final AltoClef altoClef;
    private final Settings settings;
    private boolean running = false;
    private String currentTask = "Idle";
    private boolean enabled = true;
    private TaskCatalogue taskCatalogue;

    public BotBehaviour() {
        this.altoClef = AltoClef.getInstance();
        this.settings = new Settings();
        this.taskCatalogue = new TaskCatalogue();
    }

    public void tick() {
        if (!enabled || !running) return;
        
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
    }

    public void enable() {
        enabled = true;
        running = true;
        sendChatMessage("Bot enabled!", Formatting.GREEN);
    }

    public void disable() {
        enabled = false;
        running = false;
        sendChatMessage("Bot disabled!", Formatting.RED);
    }

    public void sendChatMessage(String message, Formatting formatting) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            client.player.sendMessage(Text.literal(message).styled(style -> style.withColor(formatting)));
        }
    }

    public boolean isRunning() { return running; }
    public boolean isEnabled() { return enabled; }
    public String getCurrentTask() { return currentTask; }
    public void setCurrentTask(String task) { currentTask = task; }
    public Settings getSettings() { return settings; }
    public TaskCatalogue getTaskCatalogue() { return taskCatalogue; }
}
