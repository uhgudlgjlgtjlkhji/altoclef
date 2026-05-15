package adris.altoclef;

public class Debug {
    private static boolean debugMode = false;
    private static boolean verboseMode = false;

    public static void log(String message) {
        if (debugMode) {
            AltoClef.LOGGER.info("[DEBUG] {}", message);
        }
    }

    public static void logVerbose(String message) {
        if (verboseMode || debugMode) {
            AltoClef.LOGGER.info("[VERBOSE] {}", message);
        }
    }

    public static void enableDebug() {
        debugMode = true;
    }

    public static void enableVerbose() {
        verboseMode = true;
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static boolean isVerboseMode() {
        return verboseMode;
    }
}
