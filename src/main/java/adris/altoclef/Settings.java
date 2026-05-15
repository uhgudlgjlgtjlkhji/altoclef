package adris.altoclef;

import net.fabricmc.loader.api.FabricLoader;
import java.io.File;

public class Settings {
    private final File configDir;

    public Settings() {
        this.configDir = new File(FabricLoader.getInstance().getGameDir().toFile(), "altoclef-settings");
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
    }
}
