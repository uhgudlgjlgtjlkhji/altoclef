package adris.altoclef.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.screen.AbstractFurnaceScreenHandler;

@Mixin(AbstractFurnaceScreenHandler.class)
public interface AbstractFurnaceScreenHandlerAccessor {
    @Accessor("fuelTime")
    int getFuelTime();
}
