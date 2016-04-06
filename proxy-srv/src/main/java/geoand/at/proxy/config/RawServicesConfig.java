package geoand.at.proxy.config;

import geoand.at.raw.buslocation.BusLocationService;
import geoand.at.raw.init.Factory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gandrianakis on 6/4/2016.
 */
@Configuration
public class RawServicesConfig {

    @Bean
    public BusLocationService busLocationService(Factory factory) {
        return factory.busLocationService();
    }
}
