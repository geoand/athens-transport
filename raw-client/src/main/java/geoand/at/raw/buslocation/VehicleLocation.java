package geoand.at.raw.buslocation;

import geoand.at.raw.Position;

import java.util.Date;

/**
 * Created by gandrianakis on 6/4/2016.
 */
public class VehicleLocation {

    private final String vehicleNumber;

    private final Date timestamp;

    private final String routeCode;

    private final Position position;

    VehicleLocation(String vehicleNumber, Date timestamp, Position position, String routeCode) {
        this.vehicleNumber = vehicleNumber;
        this.timestamp = timestamp;
        this.position = position;
        this.routeCode = routeCode;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Position getPosition() {
        return position;
    }

    public String getRouteCode() {
        return routeCode;
    }
}
