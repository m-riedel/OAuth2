package de.mriedel.oauth2.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * The request to the token introspection endpoint as defined in
 * <a href="https://datatracker.ietf.org/doc/html/rfc7662#section-2.1">RFC7662</a>.
 * @author Max Riedel
 * @param token REQUIRED.  The string value of the token.  For access tokens, this
 *       is the "access_token" value returned from the token endpoint
 *       defined in OAuth 2.0 [RFC6749], Section 5.1.  For refresh tokens,
 *       this is the "refresh_token" value returned from the token endpoint
 *       as defined in OAuth 2.0 [RFC6749], Section 5.1.  Other token types
 *       are outside the scope of this specification.
 * @param tokenTypeHint OPTIONAL.  A hint about the type of the token submitted for
 *       introspection.  The protected resource MAY pass this parameter to
 *       help the authorization server optimize the token lookup.  If the
 *       server is unable to locate the token using the given hint, it MUST
 *       extend its search across all of its supported token types.  An
 *       authorization server MAY ignore this parameter, particularly if it
 *       is able to detect the token type automatically.  Values for this
 *       field are defined in the "OAuth Token Type Hints" registry defined
 *       in OAuth Token Revocation [RFC7009].
 * @param clientId REQUIRED.  The client identifier issued to the client during the
 *                 registration process as defined in OAuth 2.0 [RFC6749]
 * @param clientSecret REQUIRED.  The client secret. Used as means of authentication for the client
 *                     to prevent token scanning attacks as mentioned in <a href="https://datatracker.ietf.org/doc/html/rfc7662#section-2.1">[RFC7662]</a>.
 */
@Builder
public record OAuth2TokenIntrospectionRequest (String token,
                                               @JsonProperty("token_type_hint") String tokenTypeHint,
                                               @JsonProperty("client_id") String clientId,
                                               @JsonProperty("client_secret") String clientSecret) {
}
