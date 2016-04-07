package geoand.at.proxy.config

import geoand.at.proxy.mvc.handler.RetrofitCallReturnValueHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by gandrianakis on 6/4/2016.
 */
@Configuration
open class MvcConfig {

    @Bean
    open fun retrofitCallReturnValueHandler(): RetrofitCallReturnValueHandler = RetrofitCallReturnValueHandler()

    @Bean
    open fun observableMVCConfiguration(retrofitCallReturnValueHandler: RetrofitCallReturnValueHandler): WebMvcConfigurerAdapter {
        return object : WebMvcConfigurerAdapter() {
            override fun addReturnValueHandlers(returnValueHandlers: MutableList<HandlerMethodReturnValueHandler>?) {
                returnValueHandlers!!.add(retrofitCallReturnValueHandler)
            }
        }
    }
}
