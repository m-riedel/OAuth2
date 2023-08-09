package de.mriedel.oauth2.server.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OAuth2PersistenceMarkerConfiguration {
    @Bean Marker oauth2PersistenceMarkerBean(){
        return new Marker();
    }
    static class Marker{

    }
}
