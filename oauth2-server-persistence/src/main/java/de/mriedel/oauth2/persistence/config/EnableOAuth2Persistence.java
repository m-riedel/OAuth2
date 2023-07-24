package de.mriedel.oauth2.persistence.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(OAuth2PersistenceMarkerConfiguration.class)
public @interface EnableOAuth2Persistence {
}
