package geoand.at.raw.stop;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by gandrianakis on 8/4/2016.
 */
public interface StopService {

    String CLOSEST_ACT = "getClosestStops";

    @GET("?act=" + CLOSEST_ACT)
    Call<List<Stop>> closest(@Query("p1") Double lat, @Query("p2") Double lng);
}
