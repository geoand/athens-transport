package geoand.at.raw.route;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by gandrianakis on 7/4/2016.
 */
public interface RouteService {

    String BY_LINE_CODE_ACT = "webGetRoutes";

    @GET("?act=" + BY_LINE_CODE_ACT)
    Call<List<Route>> byLineCode(@Query("p1") String lineCode);
}
