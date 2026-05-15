package adris.altoclef.mixins;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.ChunkData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class LoadChunkMixin {
    @Inject(method = "onChunkData", at = @At("HEAD"))
    private void onChunkData(ChunkData chunk, CallbackInfo ci) {
        // Handle chunk loading
    }
}
