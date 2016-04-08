package geoand.at.raw.route;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by gandrianakis on 8/4/2016.
 */
public class RouteDirectionJacksonDeserializer extends JsonDeserializer<Route.Direction> {
    @Override
    public Route.Direction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final String strValue = p.getValueAsString();

        switch (strValue) {
            case "1":
                return Route.Direction.FORWARD;
            case "2":
                return Route.Direction.REVERSE;
            default:
                throw new IllegalArgumentException("Route direction value " + strValue +  " is not an expected value");
        }
    }
}
