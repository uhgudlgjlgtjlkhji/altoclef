package adris.altoclef.mixins;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.chat.ChatScreen;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatScreen.class)
public class ChatInputMixin {
    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void onKeyPress(int keyCode, int scanCode, int modifiers, CallbackInfo ci) {
        // Chat key press
    }
}
