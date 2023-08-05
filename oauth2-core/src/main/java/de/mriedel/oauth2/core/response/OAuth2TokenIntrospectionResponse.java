package de.mriedel.oauth2.core.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;


/**
 * The response to an OAuth2 Token Introspection Request as defined in
 * <a href="https://datatracker.ietf.org/doc/html/rfc7662#section-2.2">RFC 7662 Section 2.2</a>.
 * @author Max Riedel
 * @param active REQUIRED.  Boolean indicator of whether or not the presented token
 *       is currently active.  The specifics of a token's "active" state
 *       will vary depending on the implementation of the authorization
 *       server and the information it keeps about its tokens, but a "true"
 *       value return for the "active" property will generally indicate
 *       that a given token has been issued by this authorization server,
 *       has not been revoked by the resource owner, and is within its
 *       given time window of validity (e.g., after its issuance time and
 *       before its expiration time). See Section 4 for information on
 *       implementation of such checks.
 * @param scope OPTIONAL. A JSON string containing a space-separated list of
 *       scopes associated with this token, in the format described in
 *       Section 3.3 of OAuth 2.0 [RFC6749].
 * @param clientId OPTIONAL.  Client identifier for the OAuth 2.0 client that
 *       requested this token.
 * @param username OPTIONAL.  Human-readable identifier for the resource owner who
 *       authorized this token.
 * @param tokenType OPTIONAL.  Type of the token as defined in Section 5.1 of OAuth
 *       2.0 [RFC6749].
 * @param expiration  OPTIONAL.  Integer timestamp, measured in the number of seconds
 *       since January 1 1970 UTC, indicating when this token will expire,
 *       as defined in JWT [RFC7519].
 * @param issuedAt OPTIONAL.  Integer timestamp, measured in the number of seconds
 *       since January 1 1970 UTC, indicating when this token was
 *       originally issued, as defined in JWT [RFC7519].
 * @param notBefore  OPTIONAL.  Integer timestamp, measured in the number of seconds
 *       since January 1 1970 UTC, indicating when this token is not to be
 *       used before, as defined in JWT [RFC7519].
 * @param subject OPTIONAL.  Subject of the token, as defined in JWT [RFC7519].
 *       Usually a machine-readable identifier of the resource owner who
 *       authorized this token.
 * @param audience OPTIONAL.  Service-specific string identifier or list of string
 *       identifiers representing the intended audience for this token, as
 *       defined in JWT [RFC7519].
 * @param issuer OPTIONAL.  String representing the issuer of this token, as
 *       defined in JWT [RFC7519].
 * @param jwtId OPTIONAL.  String identifier for the token, as defined in JWT
 *       [RFC7519].
 */
@Builder
public record OAuth2TokenIntrospectionResponse (boolean active,
                                                String scope,
                                                @JsonProperty("client_id") String clientId,
                                                String username,
                                                @JsonProperty("token_type") String tokenType,
                                                @JsonProperty("exp") long expiration,
                                                @JsonProperty("iat") long issuedAt,
                                                @JsonProperty("nbf") long notBefore,
                                                @JsonProperty("sub") String subject,
                                                @JsonProperty("aud") String audience,
                                                @JsonProperty("iss") String issuer,
                                                @JsonProperty("jti") String jwtId){
}
