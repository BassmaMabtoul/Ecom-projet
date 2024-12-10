package org.sid.geteway;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.util.DisconnectedClientHelper;


import java.util.List;

@SpringBootApplication
public class GetewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetewayApplication.class, args);
    }

   // @Bean

    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/customers/*").uri("lb://CUSTOMER-SERVICE"))
                .route(r->r.path("/products/*").uri("lb://INVENTORY-SERVICE"))
                .build();

    }
    @Bean
    public DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc,
                                                               DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }




}
