package de.mriedel.oauth2.core.request.converter;

import de.mriedel.oauth2.core.commons.OAuth2GrantType;
import de.mriedel.oauth2.core.request.OAuth2TokenAuthCodeGrantRequest;
import de.mriedel.oauth2.core.request.OAuth2TokenRefreshGrantRequest;
import de.mriedel.oauth2.core.request.OAuth2TokenRequest;
import de.mriedel.oauth2.core.request.OAuth2TokenUserPasswordRequest;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class OAuth2TokenRequestConverter extends AbstractHttpMessageConverter<OAuth2TokenRequest> {
    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final OAuth2GrantType.OAuth2GrantTypeConverter grantTypeConverter = new OAuth2GrantType.OAuth2GrantTypeConverter();
    @Override
    protected boolean supports(Class<?> clazz) {
        return (OAuth2TokenRequest.class == clazz);
    }

    @Override

    protected OAuth2TokenRequest readInternal(Class<? extends OAuth2TokenRequest> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException, IOException {
        Map<String, String> data = formHttpMessageConverter.read(null, inputMessage).toSingleValueMap();
        if(!data.containsKey("grant_type")){
            return null;
        }
        OAuth2GrantType grantType = grantTypeConverter.convert(data.get("grant_type"));
        if(grantType == null){
            return null;
        }
        return switch (grantType){
            case AUTHORIZATION_CODE_GRANT -> {
                String code = data.get("code");
                String redirectUri = data.get("redirect_uri");
                String clientId = data.get("client_id");
                if(code == null || redirectUri == null || clientId == null){
                    yield null;
                }
                yield new OAuth2TokenAuthCodeGrantRequest(code, redirectUri, clientId);
            }
            case RESOURCE_OWNER_PASSWORD_CREDENTIALS_GRANT -> {
                String username = data.get("username");
                String password = data.get("password");
                String scope = data.get("scope");
                if(username == null || password == null){
                    yield null;
                }
                yield new OAuth2TokenUserPasswordRequest(username, password, scope);
            }
            case REFRESH_TOKEN_GRANT -> {
                String refreshToken = data.get("refresh_token");
                if(refreshToken == null){
                    yield null;
                }
                String scope = data.get("scope");
                yield new OAuth2TokenRefreshGrantRequest(refreshToken, scope);
            }
            default -> null;
        };
    }

    @Override
    protected void writeInternal(OAuth2TokenRequest OAuth2TokenRequest, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        throw new HttpMessageNotWritableException(String.format("%s is not writable, because it is a request.", OAuth2TokenRequest.getClass()));
    }
}
