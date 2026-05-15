package adris.altoclef.ui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class MessageSender {
    public static void sendInfo(String message) {
        send(ChatType.INFO, message);
    }

    public static void sendWarning(String message) {
        send(ChatType.WARNING, message);
    }

    public static void sendError(String message) {
        send(ChatType.ERROR, message);
    }

    private static void send(ChatType type, String message) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            Text text = Text.literal("[AltoClef] " + message);
            client.player.sendMessage(text);
        }
    }

    public enum ChatType {
        INFO,
        WARNING,
        ERROR
    }
}
