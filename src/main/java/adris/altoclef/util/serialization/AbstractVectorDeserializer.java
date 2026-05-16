package adris.altoclef.util.serialization;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.*;

public abstract class AbstractVectorDeserializer<T, UnitType> extends StdDeserializer<T> {
    protected AbstractVectorDeserializer(Class<T> vc) {
        super(vc);
    }

    protected abstract T deserializeFromUnits(List<UnitType> units);
    protected abstract boolean isUnitTokenValid(JsonToken unitToken);
    protected abstract UnitType parseUnit(String s);

    UnitType trySet(JsonParser p, Map<String, UnitType> map, String key) throws JsonParseException {
        return map.get(key);
    }

    UnitType tryParse(JsonParser p, String whole, String part) throws JsonParseException {
        try {
            return parseUnit(part);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        List<UnitType> units = new ArrayList<>();
        JsonToken token = p.currentToken();
        if (token == JsonToken.START_ARRAY) {
            while (p.nextToken() != JsonToken.END_ARRAY) {
                String val = p.getText();
                UnitType unit = parseUnit(val);
                if (unit != null) units.add(unit);
            }
        }
        return deserializeFromUnits(units);
    }
}
