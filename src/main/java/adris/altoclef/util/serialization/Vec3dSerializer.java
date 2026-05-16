package adris.altoclef.util.serialization;

import net.minecraft.world.phys.Vec3;
import java.util.Arrays;
import java.util.Collection;

public class Vec3dSerializer extends AbstractVectorSerializer<Vec3> {
    public Vec3dSerializer() {
        super(Vec3.class);
    }

    @Override
    protected Collection<String> getParts(Vec3 value) {
        return Arrays.asList(String.valueOf(value.x), String.valueOf(value.y), String.valueOf(value.z));
    }
}
