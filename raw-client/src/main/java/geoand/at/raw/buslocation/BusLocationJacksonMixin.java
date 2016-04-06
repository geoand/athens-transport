package geoand.at.raw.buslocation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gandrianakis on 6/4/2016.
 */
public abstract class BusLocationJacksonMixin {

    BusLocationJacksonMixin(@JsonProperty("VEH_NO") String v,
                            @JsonProperty("CS_DATE") String t,
                            @JsonProperty("CS_LAT") String lat,
                            @JsonProperty("CS_LNG") String lon,
                            @JsonProperty("ROUTE_CODE") String r) {}
}
