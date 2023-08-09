package de.mriedel.oauth2.server.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OAuth2AuthorizationServerMarkerConfiguration {
    @Bean
    Marker oauth2AuthorizationServerMarkerBean(){
        return new Marker();
    }
    static class Marker{

    }
}
