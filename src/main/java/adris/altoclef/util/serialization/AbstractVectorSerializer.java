package adris.altoclef.util.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Collection;

public abstract class AbstractVectorSerializer<T> extends StdSerializer<T> {
    protected AbstractVectorSerializer(Class<T> t) {
        super(t);
    }

    protected abstract Collection<String> getParts(T value);

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        for (String part : getParts(value)) {
            gen.writeString(part);
        }
        gen.writeEndArray();
    }
}
