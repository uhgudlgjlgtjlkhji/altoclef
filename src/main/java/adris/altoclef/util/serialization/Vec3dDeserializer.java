package adris.altoclef.util.serialization;

import com.fasterxml.jackson.core.JsonToken;
import net.minecraft.world.phys.Vec3;
import java.util.List;

public class Vec3dDeserializer extends AbstractVectorDeserializer<Vec3, Double> {
    public Vec3dDeserializer() {
        super(Vec3.class);
    }

    @Override
    protected Vec3 deserializeFromUnits(List<Double> units) {
        if (units.size() < 3) return Vec3.ZERO;
        return new Vec3(units.get(0), units.get(1), units.get(2));
    }

    @Override
    protected boolean isUnitTokenValid(JsonToken token) {
        return token == JsonToken.VALUE_NUMBER_FLOAT || token == JsonToken.VALUE_NUMBER_INT;
    }

    @Override
    protected Double parseUnit(String s) {
        return Double.parseDouble(s.trim());
    }
}
