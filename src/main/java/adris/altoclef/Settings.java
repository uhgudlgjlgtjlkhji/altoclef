package adris.altoclef;

import net.fabricmc.loader.api.FabricLoader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Settings {
    public static final String SETTINGS_DIR = "altoclef-settings";
    public static final String SETTINGS_FILE = "settings.json";
    
    private final File configDir;
    private Map<String, Object> settings;
    private final File settingsFile;

    public Settings() {
        this.configDir = new File(FabricLoader.getInstance().getGameDir().toFile(), SETTINGS_DIR);
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
        this.settingsFile = new File(configDir, SETTINGS_FILE);
        this.settings = new HashMap<>();
        
        if (settingsFile.exists()) {
            loadSettings();
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return (boolean) settings.getOrDefault(key, defaultValue);
    }

    public void setBoolean(String key, boolean value) {
        settings.put(key, value);
        saveSettings();
    }

    public String getString(String key, String defaultValue) {
        return (String) settings.getOrDefault(key, defaultValue);
    }

    public void setString(String key, String value) {
        settings.put(key, value);
        saveSettings();
    }

    public int getInt(String key, int defaultValue) {
        return (int) settings.getOrDefault(key, defaultValue);
    }

    public void setInt(String key, int value) {
        settings.put(key, value);
        saveSettings();
    }

    private void loadSettings() {
        try (FileReader reader = new FileReader(settingsFile)) {
            // Simple JSON-like parsing stub
            // In production, use proper JSON library
        } catch (IOException e) {
            AltoClef.LOGGER.warn("Failed to load settings", e);
        }
    }

    private void saveSettings() {
        try (FileWriter writer = new FileWriter(settingsFile)) {
            // Simple JSON serialization stub
            writer.write("{}");
        } catch (IOException e) {
            AltoClef.LOGGER.warn("Failed to save settings", e);
        }
    }
}
