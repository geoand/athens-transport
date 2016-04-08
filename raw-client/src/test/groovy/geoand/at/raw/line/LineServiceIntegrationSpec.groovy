package geoand.at.raw.line

import geoand.at.raw.init.Factory
import spock.lang.Specification

/**
 * Created by gandrianakis on 6/4/2016.
 */
class LineServiceIntegrationSpec extends Specification {

    def "all returns correct data"() {
        given:
            final lineService = Factory.live().lineService()

        when:
            final List<Line> lines = lineService.all().execute().body()

        then:
            !lines.isEmpty()

        and: "greek characters handled correctly"
            lines*.lineDescriptionGr.any {it.contains('ΑΘΗΝΑ')}
    }
}
