package adris.altoclef.util.serialization;

import net.minecraft.core.BlockPos;
import java.util.Arrays;
import java.util.Collection;

public class BlockPosSerializer extends AbstractVectorSerializer<BlockPos> {
    public BlockPosSerializer() {
        super(BlockPos.class);
    }

    @Override
    protected Collection<String> getParts(BlockPos value) {
        return Arrays.asList(String.valueOf(value.getX()), String.valueOf(value.getY()), String.valueOf(value.getZ()));
    }
}
