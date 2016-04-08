package geoand.at.raw.route

import geoand.at.raw.init.Factory
import geoand.at.raw.line.Line
import spock.lang.Specification

/**
 * Created by gandrianakis on 6/4/2016.
 */
class RouteServiceIntegrationSpec extends Specification {

    def "all returns correct data"() {
        given:
            final lineService = Factory.live().routeService()

        when:
            final List<Route> routes = lineService.byLineCode('1077').execute().body()

        then:
            !routes.isEmpty()

        and: "greek characters handled correctly"
            routes*.routeDescriptionGr.any {it.contains('ΧΑΛΑΝΔΡΙ')}
    }
}
