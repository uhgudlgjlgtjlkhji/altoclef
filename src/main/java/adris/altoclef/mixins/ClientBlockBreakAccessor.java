package adris.altoclef.mixins;

import net.minecraft.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockEntity.class)
public interface ClientBlockBreakAccessor {
    @Accessor("worldPos")
    org.joml.Vector3i getWorldPos();
}
