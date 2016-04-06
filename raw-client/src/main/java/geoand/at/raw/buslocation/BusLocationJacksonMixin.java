package geoand.at.raw.buslocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by gandrianakis on 6/4/2016.
 */
public abstract class BusLocationJacksonMixin {

    BusLocationJacksonMixin(@JsonProperty("VEH_NO") String v,
                            @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MMM dd yyyy HH:mm:ss:SSSa", timezone="EET")
                            @JsonProperty("CS_DATE")
                            Date t,
                            @JsonProperty("CS_LAT") String lat,
                            @JsonProperty("CS_LNG") String lon,
                            @JsonProperty("ROUTE_CODE") String r) {}
}
