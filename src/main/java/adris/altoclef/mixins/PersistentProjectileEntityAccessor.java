package adris.altoclef.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.Entity;

@Mixin(PersistentProjectileEntity.class)
public interface PersistentProjectileEntityAccessor {
    @Accessor("shooter")
    Entity getShooter();
}
