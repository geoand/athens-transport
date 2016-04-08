package geoand.at.raw.util;

import static java.lang.String.format;

/**
 * Created by gandrianakis on 6/4/2016.
 */
public final class PathUtil {

    private static final String LIVE_HOST = "195.46.22.91";  //TODO make this more configurable

    private PathUtil() {}

    public static String createBasePath() {
        return createBasePath(LIVE_HOST);
    }

    public static String createBasePath(String host) {
        return format("http://%s/api/", host.replace("http://", "").replace("/", ""));
    }

    public static String createActApiPath(String act) {
        return format("/api/?act=%s", act);
    }

}
