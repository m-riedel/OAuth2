package de.mriedel.oauth2.core.request.converter;

import de.mriedel.oauth2.core.commons.OAuth2GrantType;
import de.mriedel.oauth2.core.request.OAuth2TokenAuthCodeGrantRequest;
import de.mriedel.oauth2.core.request.OAuth2TokenRefreshGrantRequest;
import de.mriedel.oauth2.core.request.OAuth2TokenRequest;
import de.mriedel.oauth2.core.request.OAuth2TokenUserPasswordRequest;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Converts a {@link OAuth2TokenRequest} to and from a {@link HttpInputMessage} and {@link HttpOutputMessage} in the
 * application/x-www-form-urlencoded format respectively.
 * @author Max Riedel
 * @see OAuth2TokenRequest
 * @see OAuth2TokenAuthCodeGrantRequest
 * @see OAuth2TokenRefreshGrantRequest
 * @see OAuth2TokenUserPasswordRequest
 */
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
                yield new OAuth2TokenAuthCodeGrantRequest(code, redirectUri, clientId);
            }
            case RESOURCE_OWNER_PASSWORD_CREDENTIALS_GRANT -> {
                String username = data.get("username");
                String password = data.get("password");
                String scope = data.get("scope");
                String clientId = data.get("client_id");
                yield new OAuth2TokenUserPasswordRequest(username, password, scope, clientId);
            }
            case REFRESH_TOKEN_GRANT -> {
                String refreshToken = data.get("refresh_token");
                String scope = data.get("scope");
                yield new OAuth2TokenRefreshGrantRequest(refreshToken, scope);
            }
            default -> null;
        };
    }

    @Override
    protected void writeInternal(OAuth2TokenRequest OAuth2TokenRequest, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        switch (OAuth2TokenRequest.getGrantType()){
            case AUTHORIZATION_CODE_GRANT -> {
                OAuth2TokenAuthCodeGrantRequest OAuth2TokenAuthCodeGrantRequest = (OAuth2TokenAuthCodeGrantRequest) OAuth2TokenRequest;
                MultiValueMap<String, String> keyValues = new LinkedMultiValueMap<>();
                keyValues.add("grant_type", OAuth2TokenAuthCodeGrantRequest.getGrantType().value());
                keyValues.add("code", OAuth2TokenAuthCodeGrantRequest.getCode());
                keyValues.add("redirect_uri", OAuth2TokenAuthCodeGrantRequest.getRedirectUri());
                keyValues.add("client_id", OAuth2TokenAuthCodeGrantRequest.getClientId());
                formHttpMessageConverter.write(keyValues, null, outputMessage);
            }
            case RESOURCE_OWNER_PASSWORD_CREDENTIALS_GRANT -> {
                OAuth2TokenUserPasswordRequest OAuth2TokenUserPasswordRequest = (OAuth2TokenUserPasswordRequest) OAuth2TokenRequest;
                MultiValueMap<String, String> keyValues = new LinkedMultiValueMap<>();
                keyValues.add("grant_type", OAuth2TokenUserPasswordRequest.getGrantType().value());
                keyValues.add("client_id", OAuth2TokenUserPasswordRequest.getClientId());
                keyValues.add("username", OAuth2TokenUserPasswordRequest.getUsername());
                keyValues.add("password", OAuth2TokenUserPasswordRequest.getPassword());
                keyValues.add("scope", OAuth2TokenUserPasswordRequest.getScope());
                formHttpMessageConverter.write(keyValues, null, outputMessage);
            }
            case REFRESH_TOKEN_GRANT -> {
                OAuth2TokenRefreshGrantRequest OAuth2TokenRefreshGrantRequest = (OAuth2TokenRefreshGrantRequest) OAuth2TokenRequest;
                MultiValueMap<String, String> keyValues = new LinkedMultiValueMap<>();
                keyValues.add("grant_type", OAuth2TokenRefreshGrantRequest.getGrantType().value());
                keyValues.add("refresh_token", OAuth2TokenRefreshGrantRequest.getRefreshToken());
                keyValues.add("scope", OAuth2TokenRefreshGrantRequest.getScope());
                formHttpMessageConverter.write(keyValues, null, outputMessage);
            }
            default -> throw new HttpMessageNotWritableException("Unsupported grant type");
        }
    }

    @Override
    public List<MediaType> getSupportedMediaTypes(Class<?> clazz) {
        return List.of(MediaType.APPLICATION_FORM_URLENCODED);
    }
}
