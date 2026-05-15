package adris.altoclef.util;

public class Dimension {
    public static final String OVERWORLD = "minecraft:overworld";
    public static final String NETHER = "minecraft:the_nether";
    public static final String END = "minecraft:the_end";

    public static boolean isOverworld(String dimension) {
        return OVERWORLD.equals(dimension);
    }

    public static boolean isNether(String dimension) {
        return NETHER.equals(dimension);
    }

    public static boolean isEnd(String dimension) {
        return END.equals(dimension);
    }
}
