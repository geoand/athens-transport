package geoand.at.raw.line

import geoand.at.raw.test.support.AbstractWireMockSpecification

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static geoand.at.raw.util.PathUtil.createActApiPath
import static geoand.at.raw.line.LineService.GET_LINES_ACT
import static org.assertj.core.api.Assertions.assertThat

/**
 * Created by gandrianakis on 7/4/2016.
 */
class LineServiceSpec extends AbstractWireMockSpecification {

    def "all returns expected data"() {
        given:
            final String responseStr =
            '''
            [
                {
                  "LineCode": "815",
                  "LineID": "021",
                  "LineDescr": "ΠΛΑΤΕΙΑ ΚΑΝΙΓΓΟΣ - ΓΚΥΖΗ",
                  "LineDescrEng": "PLATEIA KANIGKOS - GKIZI"
                }, {
                  "LineCode": "821",
                  "LineID": "022",
                  "LineDescr": "Ν. ΚΥΨΕΛΗ - ΜΑΡΑΣΛΕΙΟΣ",
                  "LineDescrEng": "NEA KIPSELI - MARASLEIOS"
                }
            ]
            '''

        and: "create url matching strategy"
            final urlMatchingStrategy = urlPathEqualTo(createActApiPath(GET_LINES_ACT))

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
            final List<Line> lines = factory.lineService().all().execute().body()

        then: "verify wiremock was used"
            verify(getRequestedFor(urlMatchingStrategy))

        and: "assert the response was as expected"
            lines.size() == 2
            assertThat(lines*.lineCode).containsOnly('815', '821')
    }
}
