package adris.altoclef.util.baritone;

import net.minecraft.world.phys.Vec3;

public class CachedProjectile {
    public Vec3 velocity;
    public Vec3 position;
    public boolean hasGravity;
    private Vec3 _cachedHit;

    public CachedProjectile() {}

    public Vec3 getCachedHit() {
        return _cachedHit;
    }

    public void setCacheHit(Vec3 cache) {
        _cachedHit = cache;
    }
}
