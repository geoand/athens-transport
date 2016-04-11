package geoand.at.raw.route

import geoand.at.raw.test.support.AbstractWireMockSpecification

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static geoand.at.raw.route.RouteService.BY_STOP_CODE_ACT
import static geoand.at.raw.util.PathUtil.createActApiPath
import static org.assertj.core.api.Assertions.assertThat

/**
 * Created by gandrianakis on 8/4/2016.
 */
class RouteServiceByStopCodeSpec extends AbstractWireMockSpecification {

    def "byLineCode returns expected data"() {
        given:
            final String lineCode = "1077"

        and:
            final String responseStr =
            '''
            [
                {
                  "RouteCode": "1964",
                  "LineCode": "959",
                  "RouteDescr": "ΚΗΦΙΣΙΑ - Π. ΦΑΛΗΡΟ",
                  "RouteDescrEng": null,
                  "RouteType": "2",
                  "RouteDistance": "20712.49",
                  "LineID": "550",
                  "LineDescr": "Π. ΦΑΛΗΡΟ - ΚΗΦΙΣΙΑ",
                  "LineDescrEng": "P. FALIRO - KIFISIA",
                  "MasterLineCode": "135"
                },
                {
                  "RouteCode": "1994",
                  "LineCode": "1077",
                  "RouteDescr": "ΧΑΛΑΝΔΡΙ - ΤΖΙΤΖΙΦΙΕΣ",
                  "RouteDescrEng": null,
                  "RouteType": "1",
                  "RouteDistance": "14233.44",
                  "LineID": "10",
                  "LineDescr": "ΤΖΙΤΖΙΦΙΕΣ - ΧΑΛΑΝΔΡΙ",
                  "LineDescrEng": "TZITZIFIES - XALANDRI",
                  "MasterLineCode": "176"
                }
            ]
            '''

        and: "create url matching strategy"
            final urlMatchingStrategy = urlPathEqualTo("${createActApiPath(BY_STOP_CODE_ACT)}&p1=$lineCode")

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
            final List<Route> routes = factory.routeService().byStopCode(lineCode).execute().body()

        then: "verify wiremock was used"
            verify(getRequestedFor(urlMatchingStrategy))

        and: "assert the response was as expected"
            routes.size() == 2
            assertThat(routes*.routeCode).containsOnly('1964', '1994')

        and: "test direction"
            routes.find {it.routeCode == '1964'}.direction == Route.Direction.REVERSE
            routes.find {it.routeCode == '1994'}.direction == Route.Direction.FORWARD
    }


}
