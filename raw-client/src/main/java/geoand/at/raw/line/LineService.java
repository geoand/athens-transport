package geoand.at.raw.line;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by gandrianakis on 7/4/2016.
 */
public interface LineService {

    String GET_LINES_ACT = "webGetLines";

    @GET("?act=" + GET_LINES_ACT)
    Call<List<Line>> all();
}
