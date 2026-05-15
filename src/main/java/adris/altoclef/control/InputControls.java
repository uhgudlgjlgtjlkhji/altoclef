package adris.altoclef.control;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class InputControls {
    private final MinecraftClient client = MinecraftClient.getInstance();

    public void pressAttack() {
        // Handle attack key
    }

    public void pressUse() {
        // Handle use key
    }

    public void pressJump() {
        client.player.jump();
    }

    public void pressSneak() {
        client.player.setSneaking(true);
    }

    public void pressSprint() {
        client.player.setSprinting(true);
    }
}
