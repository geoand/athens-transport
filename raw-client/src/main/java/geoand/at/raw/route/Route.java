package geoand.at.raw.route;

/**
 * Created by gandrianakis on 8/4/2016.
 */
public class Route {

    private final String routeCode;

    private final String lineCode;

    private final String routeDescriptionGr;

    private final Direction direction;

    private final Double distance;

    public Route(String routeCode, String lineCode, String routeDescriptionGr, Direction direction, Double distance) {
        this.routeCode = routeCode;
        this.lineCode = lineCode;
        this.routeDescriptionGr = routeDescriptionGr;
        this.direction = direction;
        this.distance = distance;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public String getLineCode() {
        return lineCode;
    }

    public String getRouteDescriptionGr() {
        return routeDescriptionGr;
    }

    public Direction getDirection() {
        return direction;
    }

    public Double getDistance() {
        return distance;
    }

    /**
     * Created by gandrianakis on 8/4/2016.
     */
    public static enum Direction {

        FORWARD,
        REVERSE
    }
}
