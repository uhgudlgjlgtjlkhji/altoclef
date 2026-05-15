package adris.altoclef.mixins;

import net.minecraft.screen.AbstractFurnaceScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractFurnaceScreenHandler.class)
public interface AbstractFurnaceScreenHandlerAccessor {
    @Accessor("fuelTime")
    int getFuelTime();
    
    @Accessor("fuelTime")
    void setFuelTime(int fuelTime);
    
    @Accessor("cookedTime")
    int getCookedTime();
    
    @Accessor("cookedTime")
    void setCookedTime(int cookedTime);
}
