package adris.altoclef.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.network.ClientPlayerInteractionManager;

@Mixin(ClientPlayerInteractionManager.class)
public interface ClientPlayerInteractionAccessor {
    @Accessor("currentAction")
    int getCurrentAction();
}
