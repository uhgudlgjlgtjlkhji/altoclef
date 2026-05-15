package adris.altoclef.mixins;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockEntity.class)
public interface ClientBlockBreakAccessor {
    @Accessor("pos")
    BlockPos getPos();
}
