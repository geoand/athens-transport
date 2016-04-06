package geoand.at.raw.buslocation;

import java.util.Date;

/**
 * Created by gandrianakis on 6/4/2016.
 */
public class BusLocation {

    private final String vehicleNumber;

    private final Date timestamp;

    private final String latitude;

    private final String longitude;

    private final String routeCode;

    public BusLocation(String vehicleNumber, Date timestamp, String latitude, String longitude, String routeCode) {
        this.vehicleNumber = vehicleNumber;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.routeCode = routeCode;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getRouteCode() {
        return routeCode;
    }
}
