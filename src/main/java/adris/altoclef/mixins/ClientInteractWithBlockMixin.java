package adris.altoclef.mixins;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.chat.ChatScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatScreen.class)
public class ClientInteractWithBlockMixin {
    @Inject(method = "method_36745", at = @At("HEAD"))
    private void onInteract(Text text, CallbackInfo ci) {
        // Interact
    }
}
