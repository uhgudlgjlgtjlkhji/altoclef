package adris.altoclef.util.serialization;

import com.fasterxml.jackson.core.JsonToken;
import net.minecraft.world.level.ChunkPos;
import java.util.List;

public class ChunkPosDeserializer extends AbstractVectorDeserializer<ChunkPos, Integer> {
    public ChunkPosDeserializer() {
        super(ChunkPos.class);
    }

    @Override
    protected ChunkPos deserializeFromUnits(List<Integer> units) {
        if (units.size() < 2) return new ChunkPos(0, 0);
        return new ChunkPos(units.get(0), units.get(1));
    }

    @Override
    protected boolean isUnitTokenValid(JsonToken unitToken) {
        return unitToken == JsonToken.VALUE_NUMBER_INT;
    }

    @Override
    protected Integer parseUnit(String s) {
        return Integer.parseInt(s.trim());
    }
}
