package de.mriedel.oauth2.core.endpoint;

import de.mriedel.oauth2.core.request.OAuth2TokenIntrospectionRequest;
import de.mriedel.oauth2.core.response.OAuth2TokenIntrospectionResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

/**
 * The introspection endpoint is used to retrieve information about an access token or a refresh token.
 * It should be implemented in a {@link org.springframework.web.bind.annotation.RestController} annotated class.
 * @author Max Riedel
 */
public interface OAuth2IntrospectionEndpoint {
    /**
     * The introspection endpoint is used to retrieve information about an access token or a refresh token as
     * described in <a href="https://datatracker.ietf.org/doc/html/rfc7662">RFC7662</a>.
     * @param request the request
     * @return The response containing information about the token.
     */
    @PostMapping(
            value = "/oauth2/introspect",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Mono<OAuth2TokenIntrospectionResponse> obtainToken(@RequestBody OAuth2TokenIntrospectionRequest request);
}
