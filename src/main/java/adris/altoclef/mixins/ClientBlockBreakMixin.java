package adris.altoclef.mixins;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientBlockBreakMixin {
    @Inject(method = "tryBreakBlock", at = @At("HEAD"))
    private void onBreakBlock(net.minecraft.util.math.BlockPos pos, CallbackInfo ci) {
        // Handle block breaking
    }
}
