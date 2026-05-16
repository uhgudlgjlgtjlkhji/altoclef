package adris.altoclef.util.serialization;

import com.fasterxml.jackson.core.JsonToken;
import net.minecraft.core.BlockPos;
import java.util.List;

public class BlockPosDeserializer extends AbstractVectorDeserializer<BlockPos, Integer> {
    public BlockPosDeserializer() {
        super(BlockPos.class);
    }

    @Override
    protected BlockPos deserializeFromUnits(List<Integer> units) {
        if (units.size() < 3) return BlockPos.ZERO;
        return new BlockPos(units.get(0), units.get(1), units.get(2));
    }

    @Override
    protected boolean isUnitTokenValid(JsonToken token) {
        return token == JsonToken.VALUE_NUMBER_INT;
    }

    @Override
    protected Integer parseUnit(String s) {
        return Integer.parseInt(s.trim());
    }
}
