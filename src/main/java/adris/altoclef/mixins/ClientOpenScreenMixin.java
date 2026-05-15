package adris.altoclef.mixins;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public class ClientOpenScreenMixin {
    @Inject(method = "method_41041", at = @At("HEAD"))
    private void onOpenScreen(ScreenHandler handler, CallbackInfo ci) {
        // Open screen
    }
}
