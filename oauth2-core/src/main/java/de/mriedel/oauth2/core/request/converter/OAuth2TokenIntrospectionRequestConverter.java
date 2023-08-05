package de.mriedel.oauth2.core.request.converter;

import de.mriedel.oauth2.core.request.OAuth2TokenIntrospectionRequest;
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
 * Converts a {@link OAuth2TokenIntrospectionRequest} to and from a {@link HttpInputMessage} and {@link HttpOutputMessage}
 * in the application/x-www-form-urlencoded format respectively.
 * @author Max Riedel
 * @see OAuth2TokenIntrospectionRequest
 */
public class OAuth2TokenIntrospectionRequestConverter extends AbstractHttpMessageConverter<OAuth2TokenIntrospectionRequest> {
    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();

    @Override
    protected boolean supports(Class<?> clazz) {
        return (OAuth2TokenIntrospectionRequest.class == clazz);
    }

    @Override
    protected OAuth2TokenIntrospectionRequest readInternal(Class<? extends OAuth2TokenIntrospectionRequest> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String, String> data = formHttpMessageConverter.read(null, inputMessage).toSingleValueMap();
        if(!data.containsKey("token") || !data.containsKey("client_id") || !data.containsKey("client_secret")){
            return null;
        }
        String token = data.get("token");
        String clientId = data.get("client_id");
        String clientSecret = data.get("client_secret");
        String tokenTypeHint = data.get("token_type_hint");
        return OAuth2TokenIntrospectionRequest.builder()
                .token(token)
                .tokenTypeHint(tokenTypeHint)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    @Override
    protected void writeInternal(OAuth2TokenIntrospectionRequest request, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("token", request.token());
        data.add("client_id", request.clientId());
        data.add("client_secret", request.clientSecret());
        data.add("token_type_hint", request.tokenTypeHint());
        formHttpMessageConverter.write(data, null, outputMessage);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes(Class<?> clazz) {
        return List.of(MediaType.APPLICATION_FORM_URLENCODED);
    }
}
