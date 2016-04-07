package geoand.at.raw.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import geoand.at.raw.buslocation.BusLocation;
import geoand.at.raw.buslocation.BusLocationJacksonMixin;
import geoand.at.raw.buslocation.BusLocationService;
import geoand.at.raw.line.Line;
import geoand.at.raw.line.LineJacksonMixin;
import geoand.at.raw.line.LineService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by gandrianakis on 6/4/2016.
 */
public final class Factory {

    private final Retrofit retrofit;

    private Factory(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public static Factory live() {
        return new Factory(createRetrofit(PathUtil.createBasePath()));
    }

    public static Factory custom(String host) {
        return new Factory(createRetrofit(PathUtil.createBasePath(host)));
    }

    private static Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(getJacksonConverterFactory()).build();
    }

    private static JacksonConverterFactory getJacksonConverterFactory() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(BusLocation.class, BusLocationJacksonMixin.class);
        objectMapper.addMixIn(Line.class, LineJacksonMixin.class);
        return JacksonConverterFactory.create(objectMapper);
    }

    public BusLocationService busLocationService() {
        return retrofit.create(BusLocationService.class);
    }

    public LineService lineService() {
        return retrofit.create(LineService.class);
    }
}
