package de.mriedel.oauth2.core.request;

import de.mriedel.oauth2.core.commons.OAuth2GrantType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2TokenUserPasswordRequest extends OAuth2TokenRequest{
    String username;
    String password;
    String scope;

    public OAuth2TokenUserPasswordRequest(
            String username,
            String password,
            String scope){
        super(OAuth2GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS_GRANT);
        this.username = username;
        this.password = password;
        this.scope = scope;
    }
}
