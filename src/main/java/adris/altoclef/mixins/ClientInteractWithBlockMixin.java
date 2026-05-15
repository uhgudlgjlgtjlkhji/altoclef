package adris.altoclef.mixins;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ClientInteractWithBlockMixin {
    @Inject(method = "method_36745", at = @At("HEAD"))
    private void onInteract(Text text, CallbackInfo ci) {
        // Handle block interaction
    }
}
