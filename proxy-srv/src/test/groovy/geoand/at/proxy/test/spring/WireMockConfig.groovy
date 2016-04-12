package geoand.at.proxy.test.spring

import com.github.tomakehurst.wiremock.WireMockServer
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig

/**
 * Created by gandrianakis on 12/4/2016.
 */
@Configuration
@Slf4j
class WireMockConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    @Profile("mockGateway")
    WireMockServer wireMockServer(@Value('${gateway.localPort}') int port) {
        configureFor("localhost", port);
        return new WireMockServer(wireMockConfig().port(port));
    }
}
