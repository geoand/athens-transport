package geoand.at.raw.stop

import geoand.at.raw.test.support.AbstractWireMockSpecification

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static geoand.at.raw.stop.StopService.CLOSEST_ACT
import static geoand.at.raw.util.PathUtil.createActApiPath
import static org.assertj.core.api.Assertions.assertThat

/**
 * Created by gandrianakis on 8/4/2016.
 */
class StopServiceSpec extends AbstractWireMockSpecification {

    def "closest returns expected data"() {
        given:
            final lat = 37.9194037D
            final lng = 23.74471790000007D

        and:
            final String responseStr =
            '''
            [
                {
                    "StopCode": "210075",
                    "StopID": "210075",
                    "StopDescr": "ΚΟΝΤΟΠΗΓΑΔΟ-ΣΤ.ΑΛΙΜΟΣ",
                    "StopDescrEng": null,
                    "StopStreet": "ΛΕΩΦ.ΒΟΥΛΙΑΓΜΕΝΗΣ",
                    "StopStreetEng": null,
                    "StopHeading": "0",
                    "StopLat": "37.9195217",
                    "StopLng": "23.7446097",
                    "distance": "0.0001600975952806769"
                },
                {
                    "StopCode": "80070",
                    "StopID": "080070",
                    "StopDescr": "ΚΟΝΤΟΠΗΓΑΔΟ-ΣΤ.ΑΛΙΜΟΣ",
                    "StopDescrEng": null,
                    "StopStreet": "ΛΕΩΦ.ΒΟΥΛΙΑΓΜΕΝΗΣ",
                    "StopStreetEng": null,
                    "StopHeading": "180",
                    "StopLat": "37.9181327",
                    "StopLng": "23.7441708",
                    "distance": "0.001383748318902307"
                }
            ]
            '''

        and: "create url matching strategy"
            final urlMatchingStrategy = urlPathEqualTo("${createActApiPath(CLOSEST_ACT)}&p1=$lat&p2=$lng")

        and: "setup wiremock"
            stubFor(
                    get(urlMatchingStrategy)
                            .willReturn(
                            aResponse()
                                    .withStatus(200)
                                    .withBody(responseStr)
                    )
            )


        when:
            final List<Stop> stops = factory.stopService().closest(lat, lng).execute().body()

        then: "verify wiremock was used"
            verify(getRequestedFor(urlMatchingStrategy))

        and: "assert the response was as expected"
            stops.size() == 2
            assertThat(stops*.stopCode).containsOnly('210075', '80070')
    }
}
