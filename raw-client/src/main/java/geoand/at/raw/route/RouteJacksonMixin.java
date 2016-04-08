package geoand.at.raw.route;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gandrianakis on 8/4/2016.
 */
public abstract class RouteJacksonMixin {

    public RouteJacksonMixin(@JsonProperty("RouteCode") String rc,
                             @JsonProperty("LineCode") String lc,
                             @JsonProperty("RouteDescr") String rd,
                             @JsonProperty("RouteType") Route.Direction dir,
                             @JsonProperty("RouteDistance") Double dis) {}
}
