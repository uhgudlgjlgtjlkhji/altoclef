package adris.altoclef;

public class Debug {
    private static boolean debugMode = false;

    public static void log(String message) {
        if (debugMode) {
            AltoClef.LOGGER.info("[DEBUG] {}", message);
        }
    }

    public static void enableDebug() {
        debugMode = true;
    }
}
