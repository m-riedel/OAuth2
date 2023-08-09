package de.mriedel.oauth2.server.authorization.controller;

import de.mriedel.oauth2.core.endpoint.OAuth2IntrospectionEndpoint;
import de.mriedel.oauth2.core.endpoint.OAuth2TokenEndpoint;
import de.mriedel.oauth2.core.request.OAuth2TokenIntrospectionRequest;
import de.mriedel.oauth2.core.request.OAuth2TokenRequest;
import de.mriedel.oauth2.core.response.OAuth2TokenIntrospectionResponse;
import de.mriedel.oauth2.core.response.OAuth2TokenResponse;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OAuth2RestController implements OAuth2TokenEndpoint,
        OAuth2IntrospectionEndpoint {
    @Override
    public Mono<OAuth2TokenIntrospectionResponse> obtainToken(OAuth2TokenIntrospectionRequest request) {
        return null;
    }

    @Override
    public Mono<OAuth2TokenResponse> obtainToken(OAuth2TokenRequest OAuth2TokenRequest) {
        return null;
    }
}
