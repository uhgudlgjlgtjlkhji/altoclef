package adris.altoclef.mixins;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityAccessor {
    @Accessor("type")
    org.apache.commons.lang3.ObjectType<?> getType();
    @Accessor("uuid")
    java.util.UUID getUuid();
}
