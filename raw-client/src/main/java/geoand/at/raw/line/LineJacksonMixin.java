package geoand.at.raw.line;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gandrianakis on 6/4/2016.
 */
public abstract class LineJacksonMixin {

    LineJacksonMixin(@JsonProperty("LineCode") String lc,
                     @JsonProperty("LineID") String lid,
                     @JsonProperty("LineDescr") String ld,
                     @JsonProperty("LineDescrEng") String lde) {}
}
