package adris.altoclef.util.helpers;

import net.minecraft.client.Minecraft;

public class EntityHelper {

    public static boolean isAdventureMode() {
        if (Minecraft.getInstance().player == null) return false;
        return false;
    }

    public static boolean isAlive(net.minecraft.world.entity.Entity entity) {
        return entity != null && entity.isAlive();
    }
}
