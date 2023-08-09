package de.mriedel.oauth2.server.persistence.config;

import de.mriedel.oauth2.server.persistence.entity.OAuth2PersistenceEntityPackageMarker;
import de.mriedel.oauth2.server.persistence.repository.OAuth2PersistenceRepositoryPackageMarker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(OAuth2PersistenceMarkerConfiguration.Marker.class)
@EnableConfigurationProperties(OAuth2PersistenceProperties.class)
@EntityScan(basePackageClasses = OAuth2PersistenceEntityPackageMarker.class)
@EnableJpaRepositories(basePackageClasses = OAuth2PersistenceRepositoryPackageMarker.class)
public class OAuth2PersistenceAutoConfiguration {
}
