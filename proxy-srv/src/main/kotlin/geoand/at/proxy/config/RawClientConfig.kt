package geoand.at.proxy.config

import geoand.at.raw.init.Factory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by gandrianakis on 7/4/2016.
 */
@Configuration
open class RawClientConfig {

    @ConditionalOnProperty("gateway.useLive")
    @Bean
    open fun liveFactory() = Factory.live()

    @ConditionalOnProperty("gateway.localPort")
    @Bean
    open fun localFactory(@Value("\${gateway.localPort}") port: Int) = Factory.custom("localhost:${port}")

}