package geoand.at.raw.stop

import geoand.at.raw.init.Factory
import spock.lang.Specification

/**
 * Created by gandrianakis on 8/4/2016.
 */
class StopServiceIntegrationSpec extends Specification {

    def "closest returns a list of stop when called with valid lat/lng arguments"() {
        given:
            final stopService = Factory.live().stopService()

        when:
            final List<Stop> stops = stopService.closest(37.9194037D, 23.74471790000007D).execute().body()

        then:
            !stops.isEmpty()
    }
}
