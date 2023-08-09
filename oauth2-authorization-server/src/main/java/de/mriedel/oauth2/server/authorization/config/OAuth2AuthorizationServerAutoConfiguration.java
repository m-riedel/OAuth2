package de.mriedel.oauth2.server.authorization.config;

import de.mriedel.oauth2.server.authorization.controller.OAuth2AuthorizationController;
import de.mriedel.oauth2.server.authorization.controller.OAuth2RestController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(OAuth2AuthorizationServerMarkerConfiguration.Marker.class)
@EnableConfigurationProperties(OAuth2AuthorizationServerProperties.class)
public class OAuth2AuthorizationServerAutoConfiguration {
    @Bean
    public OAuth2AuthorizationController authorizationController(){
        return new OAuth2AuthorizationController();
    }

    @Bean
    public OAuth2RestController restController(){
        return new OAuth2RestController();
    }
}
