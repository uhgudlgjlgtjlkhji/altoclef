package adris.altoclef.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class PlayerCollidesWithEntityMixin {
    @Inject(method = "collidesWith", at = @At("HEAD"), cancellable = true)
    private void onCollide(Entity other, CallbackInfoReturnable<Boolean> cir) {
        // Handle entity collisions
    }
}
