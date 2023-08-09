package de.mriedel.oauth2.server.authorization.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(OAuth2AuthorizationServerMarkerConfiguration.class)
public @interface EnableOAuth2AuthorizationServer {
}
