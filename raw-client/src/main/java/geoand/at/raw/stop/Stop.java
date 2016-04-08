package geoand.at.raw.stop;

import geoand.at.raw.Position;

/**
 * Created by gandrianakis on 8/4/2016.
 */
public class Stop {

    private final String stopCode;

    private final String stopID;

    private final String stopDescriptionGr;

    private final String stopStreet;

    private final Position position;

    private final Double distance;

    Stop(String stopCode, String stopID, String stopDescriptionGr, String stopStreet, Position position, Double distance) {
        this.stopCode = stopCode;
        this.stopID = stopID;
        this.stopDescriptionGr = stopDescriptionGr;
        this.stopStreet = stopStreet;
        this.position = position;
        this.distance = distance;
    }

    public String getStopCode() {
        return stopCode;
    }

    public String getStopID() {
        return stopID;
    }

    public String getStopDescriptionGr() {
        return stopDescriptionGr;
    }

    public String getStopStreet() {
        return stopStreet;
    }

    public Position getPosition() {
        return position;
    }

    public Double getDistance() {
        return distance;
    }
}
