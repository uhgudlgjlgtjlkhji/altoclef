package adris.altoclef.mixins;

import net.minecraft.client.gui.ChatLine;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatLine.class)
public class ChatReadMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onChatMessage(Text message, int type, CallbackInfo ci) {
        // Handle chat messages
    }
}
