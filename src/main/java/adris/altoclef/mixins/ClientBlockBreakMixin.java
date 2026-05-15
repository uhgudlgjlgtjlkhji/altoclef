package adris.altoclef.mixins;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientBlockBreakMixin {
    @Inject(method = "tryBreakBlock", at = @At("HEAD"))
    private void onBreakBlock(BlockPos pos, CallbackInfo ci) {
        // Break block
    }
}
