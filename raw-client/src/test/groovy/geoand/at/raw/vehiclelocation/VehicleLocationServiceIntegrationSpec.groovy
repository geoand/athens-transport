package geoand.at.raw.vehiclelocation

import geoand.at.raw.init.Factory
import spock.lang.Specification

/**
 * Created by gandrianakis on 6/4/2016.
 */
class VehicleLocationServiceIntegrationSpec extends Specification {

    def "get returns a list of locations when called with a known correct routeCode"() {
        given:
            final busLocationService = Factory.live().busLocationService()

        when:
            final List<VehicleLocation> locations = busLocationService.byRouteCode("1993").execute().body()

        then:
            !locations.isEmpty()
    }
}
