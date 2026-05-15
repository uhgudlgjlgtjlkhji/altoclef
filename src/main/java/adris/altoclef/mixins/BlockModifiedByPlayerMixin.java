package adris.altoclef.mixins;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class BlockModifiedByPlayerMixin {
    @Inject(method = "method_38266", at = @At("HEAD"))
    private void onBlockModified(int x, int y, int z, CallbackInfo ci) {
        // Block modified
    }
}
