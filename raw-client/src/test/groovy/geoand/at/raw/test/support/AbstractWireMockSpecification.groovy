package geoand.at.raw.test.support

import com.github.tomakehurst.wiremock.junit.WireMockRule
import geoand.at.raw.init.Factory as Factory
import org.junit.Rule
import spock.lang.Specification

/**
 * Created by gandrianakis on 6/4/2016.
 */
abstract class AbstractWireMockSpecification extends Specification {

    private static final int PORT = 8089

    @Rule
    WireMockRule wireMockServer = new WireMockRule(PORT)

    Factory factory = Factory.custom(host())

    protected String host() {
        return "localhost:${PORT}"
    }


}
