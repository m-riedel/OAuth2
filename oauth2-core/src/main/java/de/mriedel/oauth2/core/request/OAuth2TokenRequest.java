package de.mriedel.oauth2.core.request;


import de.mriedel.oauth2.core.commons.OAuth2GrantType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class OAuth2TokenRequest {
    OAuth2GrantType grantType;
}

