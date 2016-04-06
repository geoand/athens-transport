package geoand.at.proxy.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by gandrianakis on 4/3/2016.
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty("swagger.enabled")
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .paths(paths())
                .apis(RequestHandlerSelectors.any()).build()
                .pathMapping("/");
    }

    @SuppressWarnings("unchecked")
    private Predicate<String> paths() {
        return Predicates.not(PathSelectors.regex("/error"));
    }

}
