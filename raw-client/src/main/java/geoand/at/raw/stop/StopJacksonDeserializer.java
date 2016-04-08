package geoand.at.raw.stop;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import geoand.at.raw.Position;
import geoand.at.raw.util.jackson.AbstractBaseJacksonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by gandrianakis on 8/4/2016.
 */
public class StopJacksonDeserializer extends AbstractBaseJacksonDeserializer<Stop> {

    private final static Logger log = LoggerFactory.getLogger(StopJacksonDeserializer.class);

    @Override
    public Stop deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            final JsonNode node = p.getCodec().readTree(p);

            final String stopCode = node.get("StopCode").asText();
            final String stopID = node.get("StopID").asText();
            final String stopDescriptionGr = node.get("StopDescr").asText();
            final String stopStreet = node.get("StopStreet").asText();

            final Double latitude = getDouble(node, "StopLat");
            final Double longitude = getDouble(node, "StopLng");

            final Double distance = getDouble(node, "distance");

            return new Stop(stopCode, stopID, stopDescriptionGr, stopStreet, new Position(latitude, longitude), distance);
        } catch (Exception e) {
            log.error("Unable to parse json", e);
            throw new IOException(e);
        }

    }
}

