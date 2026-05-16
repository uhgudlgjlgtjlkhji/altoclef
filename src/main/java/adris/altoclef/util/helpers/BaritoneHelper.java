package adris.altoclef.util.helpers;

import net.minecraft.world.phys.Vec3;

public class BaritoneHelper {
    public static double calculateGenericHeuristic(Vec3 start, Vec3 target) {
        double dx = target.x - start.x;
        double dy = target.y - start.y;
        double dz = target.z - start.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
