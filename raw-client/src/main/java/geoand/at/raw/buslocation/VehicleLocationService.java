package geoand.at.raw.buslocation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by gandrianakis on 6/4/2016.
 */
public interface VehicleLocationService {

    String BUS_LOCATION_ACT = "getBusLocation";

    @GET("?act=" + BUS_LOCATION_ACT)
    Call<List<VehicleLocation>> byRouteCode(@Query("p1") String routeCode);
}
