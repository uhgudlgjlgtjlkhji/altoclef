package adris.altoclef.util.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import java.io.IOException;

public class ItemSerializer extends StdSerializer<Object> {
    public ItemSerializer() {
        super(Object.class);
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value instanceof Item item) {
            var key = BuiltInRegistries.ITEM.getKey(item);
            gen.writeString(key != null ? key.toString() : "minecraft:air");
        } else {
            gen.writeNull();
        }
    }
}
