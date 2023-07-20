package de.mriedel.oauth2.core.commons;

/**
 * Defines the type of OAuth client.
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-2.1">OAuth 2.0 - Client Types</a>
 */
public enum OAuth2ClientType {
    /**
     * Clients capable of maintaining the confidentiality of their
     * credentials (e.g., client implemented on a secure server with
     * restricted access to the client credentials), or capable of secure
     * client authentication using other means.
     */
    CONFIDENTIAL,
    /**
     * Clients incapable of maintaining the confidentiality of their
     * credentials (e.g., clients executing on the device used by the
     * resource owner, such as an installed native application or a web
     * browser-based application), and incapable of secure client
     * authentication via any other means.
     */
    PUBLIC;
}
