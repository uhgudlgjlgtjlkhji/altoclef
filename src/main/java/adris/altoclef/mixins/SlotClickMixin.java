package adris.altoclef.mixins;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public class SlotClickMixin {
    @Inject(method = "clickSlot", at = @At("HEAD"), cancellable = true)
    private void onSlotClick(int slotIdx, int button, int action, CallbackInfo ci) {
        // Slot click
    }
}
