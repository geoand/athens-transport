package geoand.at.proxy.test.support

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import geoand.at.proxy.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.ActiveProfiles

/**
 * Created by gandrianakis on 12/4/2016.
 */
@SpringApplicationConfiguration(Application)
@ActiveProfiles("mockGateway")
abstract class AbstractWireMockSpringIntegrationSpec extends AbstractSpringIntegrationSpec {

    @Autowired
    WireMockServer wireMockServer

    def setup() {
        WireMock.reset()
    }
}
