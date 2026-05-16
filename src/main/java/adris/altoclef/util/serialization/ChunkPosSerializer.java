package adris.altoclef.util.serialization;

import net.minecraft.world.level.ChunkPos;
import java.util.Arrays;
import java.util.Collection;

public class ChunkPosSerializer extends AbstractVectorSerializer<ChunkPos> {
    public ChunkPosSerializer() {
        super(ChunkPos.class);
    }

    @Override
    protected Collection<String> getParts(ChunkPos value) {
        return Arrays.asList(String.valueOf(value.x), String.valueOf(value.z));
    }
}
