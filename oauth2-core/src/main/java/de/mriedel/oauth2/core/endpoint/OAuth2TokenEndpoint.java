package de.mriedel.oauth2.core.endpoint;

import de.mriedel.oauth2.core.request.OAuth2TokenRequest;
import de.mriedel.oauth2.core.response.OAuth2TokenResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;


/**
 * The token endpoint is used by the client to obtain an access token by presenting its
 * authorization grant or refresh token. <br>
 * It should be implemented in a {@link org.springframework.web.bind.annotation.RestController} annotated class.
 */
public interface OAuth2TokenEndpoint {

    /**
     * The token endpoint is used by the client to obtain an access token by presenting its
     * authorization grant or refresh token.
     * @param OAuth2TokenRequest the request
     * @return The access token. It can include refresh token and scope.
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-4.1.3"></a>
     */
    @PostMapping(
            value = "/oauth2/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Mono<OAuth2TokenResponse> obtainToken(@RequestBody OAuth2TokenRequest OAuth2TokenRequest);
}
