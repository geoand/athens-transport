package geoand.at.raw.init;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import geoand.at.raw.line.Line;
import geoand.at.raw.line.LineJacksonMixin;
import geoand.at.raw.line.LineService;
import geoand.at.raw.route.Route;
import geoand.at.raw.route.RouteDirectionJacksonDeserializer;
import geoand.at.raw.route.RouteJacksonMixin;
import geoand.at.raw.route.RouteService;
import geoand.at.raw.stop.Stop;
import geoand.at.raw.stop.StopJacksonDeserializer;
import geoand.at.raw.stop.StopService;
import geoand.at.raw.util.PathUtil;
import geoand.at.raw.vehiclelocation.VehicleLocation;
import geoand.at.raw.vehiclelocation.VehicleLocationJacksonDeserializer;
import geoand.at.raw.vehiclelocation.VehicleLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by gandrianakis on 6/4/2016.
 */
public final class Factory {

    private final static Logger log = LoggerFactory.getLogger(Factory.class);

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
        log.debug("Using baseUrl = " + baseUrl + " to construct factory");
        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(getJacksonConverterFactory()).build();
    }

    private static JacksonConverterFactory getJacksonConverterFactory() {
        final ObjectMapper objectMapper = new ObjectMapper();
        addMixins(objectMapper);
        addDeserializers(objectMapper);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return JacksonConverterFactory.create(objectMapper);
    }

    private static void addMixins(ObjectMapper objectMapper) {
        objectMapper.addMixIn(Line.class, LineJacksonMixin.class);

        objectMapper.addMixIn(Route.class, RouteJacksonMixin.class);
    }

    private static void addDeserializers(ObjectMapper objectMapper) {
        final SimpleModule module = new SimpleModule("AthensTransportModule", new Version(1, 0, 0, null, "athens-transport", "raw-client"));

        module.addDeserializer(VehicleLocation.class, new VehicleLocationJacksonDeserializer());

        module.addDeserializer(Route.Direction.class, new RouteDirectionJacksonDeserializer());

        module.addDeserializer(Stop.class, new StopJacksonDeserializer());

        objectMapper.registerModule(module);
    }

    public VehicleLocationService busLocationService() {
        return retrofit.create(VehicleLocationService.class);
    }

    public LineService lineService() {
        return retrofit.create(LineService.class);
    }

    public RouteService routeService() {
        return retrofit.create(RouteService.class);
    }

    public StopService stopService() {
        return retrofit.create(StopService.class);
    }
}
