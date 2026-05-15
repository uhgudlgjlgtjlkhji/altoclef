package adris.altoclef.mixins;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ClientOpenScreenMixin {
    @Inject(method = "method_36742", at = @At("HEAD"))
    private void onOpenScreen(net.minecraft.screen.ScreenHandler handler, CallbackInfo ci) {
        // Handle screen opening
    }
}
