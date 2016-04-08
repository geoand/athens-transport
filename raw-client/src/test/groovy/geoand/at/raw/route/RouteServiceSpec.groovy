package geoand.at.raw.route

import geoand.at.raw.test.support.AbstractWireMockSpecification

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static geoand.at.raw.init.PathUtil.createActApiPath
import static geoand.at.raw.route.RouteService.BY_LINE_CODE_ACT
import static org.assertj.core.api.Assertions.assertThat

/**
 * Created by gandrianakis on 8/4/2016.
 */
class RouteServiceSpec extends AbstractWireMockSpecification {

    def "byLineCode returns expected data"() {
        given:
            final String lineCode = "1077"

        and:
            final String responseStr =
            '''
            [
                {
                    "RouteCode": "1993",
                    "LineCode": "1077",
                    "RouteDescr": "ΤΖΙΤΖΙΦΙΕΣ - ΧΑΛΑΝΔΡΙ",
                    "RouteDescrEng": null,
                    "RouteType": "1",
                    "RouteDistance": "14960.17"
                },
                {
                    "RouteCode": "1994",
                    "LineCode": "1077",
                    "RouteDescr": "ΧΑΛΑΝΔΡΙ - ΤΖΙΤΖΙΦΙΕΣ",
                    "RouteDescrEng": null,
                    "RouteType": "2",
                    "RouteDistance": "14233.44"
                }
            ]
            '''

        and: "create url matching strategy"
            final urlMatchingStrategy = urlPathEqualTo("${createActApiPath(BY_LINE_CODE_ACT)}&p1=$lineCode")

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
            final List<Route> routes = factory.routeService().byLineCode(lineCode).execute().body()

        then: "verify wiremock was used"
            verify(getRequestedFor(urlMatchingStrategy))

        and: "assert the response was as expected"
            routes.size() == 2
            assertThat(routes*.routeCode).containsOnly('1993', '1994')

        and: "test direction"
            routes.find {it.routeCode == '1993'}.direction == Route.Direction.FORWARD
            routes.find {it.routeCode == '1994'}.direction == Route.Direction.REVERSE
    }


}
