package de.mriedel.oauth2.core.request;

import de.mriedel.oauth2.core.commons.OAuth2GrantType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2TokenRefreshGrantRequest extends OAuth2TokenRequest{
    private String refreshToken;
    private String scope;
    public OAuth2TokenRefreshGrantRequest(String refreshToken, String scope){
        super(OAuth2GrantType.REFRESH_TOKEN_GRANT);
        this.refreshToken = refreshToken;
        this.scope = scope;
    }
}
