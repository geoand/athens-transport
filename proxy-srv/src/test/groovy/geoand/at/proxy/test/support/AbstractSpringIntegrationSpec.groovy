package geoand.at.proxy.test.support

import geoand.at.proxy.Application
import org.springframework.boot.test.SpringApplicationConfiguration
import spock.lang.Specification

/**
 * Created by gandrianakis on 12/4/2016.
 */
@SpringApplicationConfiguration(Application)
abstract class AbstractSpringIntegrationSpec extends Specification {
}
