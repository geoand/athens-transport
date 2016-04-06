package geoand.at.proxy.config;

import geoand.at.proxy.mvc.handler.RetrofitCallReturnValueHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by gandrianakis on 6/4/2016.
 */
@Configuration
public class MvcConfig {

    @Bean
    public RetrofitCallReturnValueHandler retrofitCallReturnValueHandler() {
        return new RetrofitCallReturnValueHandler();
    }

    @Bean
    public WebMvcConfigurerAdapter observableMVCConfiguration(RetrofitCallReturnValueHandler retrofitCallReturnValueHandler) {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
                returnValueHandlers.add(retrofitCallReturnValueHandler);
            }
        };
    }
}
