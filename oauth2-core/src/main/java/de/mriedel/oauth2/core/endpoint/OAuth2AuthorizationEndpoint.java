package de.mriedel.oauth2.core.endpoint;

import de.mriedel.oauth2.core.commons.OAuth2ResponseType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * OAuth Authorization Endpoint
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-3.1">OAuth 2.0 Authorization Endpoint</a>
 */
public interface OAuth2AuthorizationEndpoint {
    /**
     * The authorization endpoint is used to interact with the resource
     * owner and obtain an authorization grant.
     * @param responseType REQUIRED.  The value MUST be one of "code" for requesting an
     *          authorization code as described by <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-4.1.1">Section 4.1.1</a>, "token" for
     *          requesting an access token (implicit grant) as described by
     *          <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-4.2.1">Section 4.2.1</a>
     *          , or a registered extension value as described by
     *          <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-8.4">Section 8.4</a>.
     * @param clientId REQUIRED.  The client identifier as described in <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-2.2">Section 2.2</a>.
     * @param scope OPTIONAL.  The scope of the access request as described by <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-3.3">Section 3.3</a>.
     * @param redirectUri OPTIONAL.  As described in <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-3.1.2">Section 3.1.2</a>
     * @param state RECOMMENDED.  An opaque value used by the client to maintain
     *          state between the request and callback.  The authorization
     *          server includes this value when redirecting the user-agent back
     *          to the client.  The parameter SHOULD be used for preventing
     *          cross-site request forgery as described in <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-10.12">Section 10.12</a>.
     * @return Redirects to the required site and provides an authorization code or an access token,
     * depending on the response type. When using the implicit grant, the access token is provided in the URL fragment.
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-3.1">OAuth 2.0 Authorization Endpoint</a>
     */
    @GetMapping(value = "/oauth2/authorize")
    String authorize(@RequestParam(value = "response_type") OAuth2ResponseType responseType,
                      @RequestParam(value = "client_id") String clientId,
                      @RequestParam(value = "scope", required = false) String scope,
                      @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                      @RequestParam(value = "state", required = false) String state);
}