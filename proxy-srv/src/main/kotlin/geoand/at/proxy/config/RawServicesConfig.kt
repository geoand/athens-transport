package geoand.at.proxy.config

import geoand.at.raw.init.Factory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by gandrianakis on 7/4/2016.
 */
@Configuration
open class RawServicesConfig {

    @Bean
    open fun busLocationService(factory: Factory) = factory.busLocationService()
}