package br.com.bookon.apigateway.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfiguration {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("book", r -> r.path("/book/**")
                        .uri("lb://book")
                )

                .route("user", r -> r.path("/user/**")
                        .uri("lb://user")
                )

                .route("loan", r -> r.path("/loan/**")
                        .uri("lb://loan")
                )
                .build();
    }
}
