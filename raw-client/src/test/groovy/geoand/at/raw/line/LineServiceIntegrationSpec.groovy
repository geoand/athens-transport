package geoand.at.raw.line

import geoand.at.raw.init.Factory
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

/**
 * Created by gandrianakis on 6/4/2016.
 */
class LineServiceIntegrationSpec extends Specification {

    def "get returns a list of locations when called with a known correct routeCode"() {
        given:
            final lineService = Factory.live().lineService()

        when:
            final List<Line> lines = lineService.all().execute().body()

        then:
            !lines.isEmpty()

        and: "greek characters handled correctly"
            final greekDescriptionAsList = lines.head().lineDescriptionGr.toList()
            assertThat(greekDescriptionAsList).contains('Α') || assertThat(greekDescriptionAsList).contains('Ο') || assertThat(greekDescriptionAsList).contains('Ε')
    }
}
