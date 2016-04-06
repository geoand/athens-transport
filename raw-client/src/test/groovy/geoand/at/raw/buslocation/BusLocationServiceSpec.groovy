package geoand.at.raw.buslocation

import geoand.at.raw.test.support.AbstractWireMockSpecification

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static geoand.at.raw.buslocation.BusLocationService.ACT
import static geoand.at.raw.init.PathUtil.createActApiPath
import static org.assertj.core.api.Assertions.assertThat

/**
 * Created by gandrianakis on 6/4/2016.
 */
class BusLocationServiceSpec extends AbstractWireMockSpecification {

    def "get returns expected data"() {
        given:
            final String routeCode = "123"

        and:
            final String responseStr =
            '''
            [
                {
                  "VEH_NO": "89005",
                  "CS_DATE": "Apr 6 2016 01:52:37:000PM",
                  "CS_LAT": "37.9434620",
                  "CS_LNG": "23.6853710",
                  "ROUTE_CODE": "1993"
                },
                {
                  "VEH_NO": "89025",
                  "CS_DATE": "Apr 6 2016 01:59:17:000PM",
                  "CS_LAT": "37.9431800",
                  "CS_LNG": "23.6853120",
                  "ROUTE_CODE": "1993"
                }
            ]
            '''

        and: "create url matching strategy"
            final urlMatchingStrategy = urlPathEqualTo("${createActApiPath(ACT)}&p1=$routeCode")

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
            final List<BusLocation> locations = factory.busLocationService().byRouteCode(routeCode).execute().body()

        then: "verify wiremock was used"
            verify(getRequestedFor(urlMatchingStrategy))

        and: "assert the response was as expected"
            locations.size() == 2
            assertThat(locations*.vehicleNumber).containsOnly('89005', '89025')

        and: "test date"
            final firstDate = locations.head().timestamp
            assertThat(firstDate).isInSameYearAs('2016-04-06')
            assertThat(firstDate).isInSameMonthAs('2016-04-06')
            assertThat(firstDate).isInSameDayAs('2016-04-06')
            assertThat(firstDate).isInSameHourAs('2016-04-06 13:52:37.000')
    }
}
