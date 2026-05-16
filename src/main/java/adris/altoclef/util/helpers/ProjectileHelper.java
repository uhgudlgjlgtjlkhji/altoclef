package adris.altoclef.util.helpers;

import adris.altoclef.util.baritone.CachedProjectile;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;

public class ProjectileHelper {

    public static boolean hasGravity(Projectile entity) {
        return !(entity instanceof AbstractHurtingProjectile);
    }

    public static Vec3 calculateArrowClosestApproach(Vec3 shootOrigin, Vec3 shootVelocity, double yGravity, Vec3 playerOrigin) {
        double t = getNearestTimeOfShotProjectile(shootOrigin, shootVelocity, yGravity, playerOrigin);
        double x = shootOrigin.x + shootVelocity.x * t;
        double y = shootOrigin.y + shootVelocity.y * t + 0.5 * yGravity * t * t;
        double z = shootOrigin.z + shootVelocity.z * t;
        return new Vec3(x, y, z);
    }

    public static Vec3 calculateArrowClosestApproach(CachedProjectile projectile, Vec3 pos) {
        return calculateArrowClosestApproach(projectile.position, projectile.velocity, projectile.hasGravity ? -0.05 : 0, pos);
    }

    public static Vec3 calculateArrowClosestApproach(CachedProjectile projectile, LocalPlayer player) {
        return calculateArrowClosestApproach(projectile, player.position());
    }

    public static Vec3 getThrowOrigin(Entity entity) {
        return entity.position().add(0, entity.getEyeHeight(), 0);
    }

    private static Vec3 getClosestPointOnFlatLine(double shootX, double shootZ, double velX, double velZ, double playerX, double playerZ) {
        double t = ((playerX - shootX) * velX + (playerZ - shootZ) * velZ) / (velX * velX + velZ * velZ);
        if (t < 0) t = 0;
        return new Vec3(shootX + velX * t, 0, shootZ + velZ * t);
    }

    private static double getNearestTimeOfShotProjectile(Vec3 shootOrigin, Vec3 shootVelocity, double yGravity, Vec3 playerOrigin) {
        double dx = playerOrigin.x - shootOrigin.x;
        double dz = playerOrigin.z - shootOrigin.z;
        double vx = shootVelocity.x;
        double vz = shootVelocity.z;
        double denom = vx * vx + vz * vz;
        if (denom == 0) return 0;
        return (dx * vx + dz * vz) / denom;
    }
}
