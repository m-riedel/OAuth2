package de.mriedel.oauth2.server.authorization.controller;

import de.mriedel.oauth2.core.commons.OAuth2ResponseType;
import de.mriedel.oauth2.core.endpoint.OAuth2AuthorizationEndpoint;
import org.springframework.stereotype.Controller;

/**
 * This controller is responsible for handling the OAuth2 authorization endpoint and
 * the associated flow of gaining authorization from the user.
 * @author Max Riedel
 */
@Controller
public class OAuth2AuthorizationController implements OAuth2AuthorizationEndpoint{
    @Override
    public String authorize(OAuth2ResponseType responseType, String clientId, String scope, String redirectUri, String state) {
        return null;
    }
}
