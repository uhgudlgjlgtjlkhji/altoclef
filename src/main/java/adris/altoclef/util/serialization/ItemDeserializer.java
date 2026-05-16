package adris.altoclef.util.serialization;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import java.io.IOException;

public class ItemDeserializer extends StdDeserializer<Object> {
    public ItemDeserializer() {
        super(Object.class);
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String text = p.getText();
        if (text == null || text.isEmpty()) return Items.AIR;
        ResourceLocation id = ResourceLocation.tryParse(text);
        if (id == null) return Items.AIR;
        return BuiltInRegistries.ITEM.get(id).orElse(Items.AIR);
    }
}
