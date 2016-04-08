package geoand.at.raw.util.jackson;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;

/**
 * Created by gandrianakis on 8/4/2016.
 */
public abstract class AbstractBaseJacksonDeserializer<T> extends JsonDeserializer<T> {

    protected Double getDouble(JsonNode node, String name) {
        return new BigDecimal(node.get(name).asText()).doubleValue();
    }
}
