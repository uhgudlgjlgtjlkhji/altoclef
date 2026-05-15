package adris.altoclef.mixins;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public class EntryMixin {
    @Inject(method = "addDrawableChild", at = @At("HEAD"))
    private void onAddElement(Element element, CallbackInfoReturnable<Boolean> cir) {
        // Add element
    }
}
