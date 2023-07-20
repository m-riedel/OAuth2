package de.mriedel.oauth2.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.mriedel.oauth2.core.commons.OAuth2GrantType;
import lombok.*;

@Getter
@Setter
@Builder
public class OAuth2TokenAuthCodeGrantRequest extends OAuth2TokenRequest{
    String code;
    @JsonProperty("redirect_uri") String redirectUri;
    @JsonProperty("client_id") String clientId;

    public OAuth2TokenAuthCodeGrantRequest(
            String code,
            @JsonProperty("redirect_uri") String redirectUri,
            @JsonProperty("client_id") String clientId){
        super(OAuth2GrantType.AUTHORIZATION_CODE_GRANT);
        this.code = code;
        this.redirectUri = redirectUri;
        this.clientId = clientId;
    }
}
