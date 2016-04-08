package geoand.at.raw.buslocation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import geoand.at.raw.Position;
import geoand.at.raw.util.jackson.AbstractBaseJacksonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gandrianakis on 8/4/2016.
 */
public class BusLocationJacksonDeserializer extends AbstractBaseJacksonDeserializer<BusLocation> {

    private final static Logger log = LoggerFactory.getLogger(BusLocationJacksonDeserializer.class);

    private static final long HOUR = 3600*1000; // in milli-seconds.
    private static final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm:ss:SSSaa", Locale.US);

    @Override
    public BusLocation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            final JsonNode node = p.getCodec().readTree(p);

            final String vehicleNumber = node.get("VEH_NO").asText();
            final String timestampStr = node.get("CS_DATE").asText();
            final Double latitude = getDouble(node, "CS_LAT");
            final Double longitude = getDouble(node, "CS_LNG");
            final String routeCode = node.get("ROUTE_CODE").asText();

            return new BusLocation(vehicleNumber, getDate(timestampStr), new Position(latitude, longitude), routeCode);
        } catch (Exception e) {
            log.error("Unable to parse json", e);
            throw new IOException(e);
        }

    }

    private Date getDate(String timestampStr) throws ParseException {
        final Date parseResult = sdf.parse(timestampStr);

        if(timestampStr.endsWith("PM")) {
            return new Date(parseResult.getTime() + 12 * HOUR);
        }

        return parseResult;
    }

}
