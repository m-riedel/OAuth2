package de.mriedel.oauth2.core.request.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.mriedel.oauth2.core.commons.OAuth2GrantType;
import de.mriedel.oauth2.core.request.OAuth2TokenAuthCodeGrantRequest;
import de.mriedel.oauth2.core.request.OAuth2TokenRefreshGrantRequest;
import de.mriedel.oauth2.core.request.OAuth2TokenRequest;
import de.mriedel.oauth2.core.request.OAuth2TokenUserPasswordRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
public class OAuth2TokenRequestConverterTest {
    OAuth2TokenRequestConverter converter = new OAuth2TokenRequestConverter();
    ObjectMapper objectMapper = new ObjectMapper();
    String code = "SplxlOBeZQQYbYS6WxSbIA";
    String clientId = "s6BhdRkqt3";
    String redirectUri = "https://client.example.com/cb";
    String username = "johndoe";
    String password = "abc";
    String scope = "read write";
    @Test
    void shouldReturnCorrectObjectForCorrectAuthCodeGrantRequestBody() throws IOException {
        OAuth2GrantType grantType = OAuth2GrantType.AUTHORIZATION_CODE_GRANT;

        String test = String.format("grant_type=%s&code=%s&redirect_uri=%s&client_id=%s",
                grantType.value(),
                code,
                URLEncoder.encode(redirectUri,StandardCharsets.UTF_8),
                clientId);
        OAuth2TokenRequest res = converter.readInternal(OAuth2TokenRequest.class, new TestHttpInputMessage(test, new HttpHeaders()));
        Assertions.assertNotNull(res);
        Assertions.assertEquals(res.getGrantType(), OAuth2GrantType.AUTHORIZATION_CODE_GRANT);
        Assertions.assertInstanceOf(OAuth2TokenAuthCodeGrantRequest.class, res);
        OAuth2TokenAuthCodeGrantRequest resCast = (OAuth2TokenAuthCodeGrantRequest) res;
        Assertions.assertEquals(resCast.getCode(), code);
        Assertions.assertEquals(resCast.getRedirectUri(), redirectUri);
        Assertions.assertEquals(resCast.getClientId(), clientId);
    }
    @Test
    void shouldReturnCorrectObjectForCorrectUsernamePasswordGrantRequestBody() throws IOException {
        OAuth2GrantType grantType = OAuth2GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS_GRANT;
        String test = String.format("grant_type=%s&username=%s&password=%s&client_id=%s&scope=%s",
                grantType.value(),
                code,
                URLEncoder.encode(redirectUri,StandardCharsets.UTF_8),
                clientId,
                URLEncoder.encode(scope,StandardCharsets.UTF_8));
        System.out.println(test);
        OAuth2TokenRequest res = converter.readInternal(OAuth2TokenRequest.class, new TestHttpInputMessage(test, new HttpHeaders()));
        Assertions.assertNotNull(res);
        Assertions.assertEquals(res.getGrantType(), OAuth2GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS_GRANT);
        Assertions.assertInstanceOf(OAuth2TokenUserPasswordRequest.class, res);
        OAuth2TokenUserPasswordRequest resCast = (OAuth2TokenUserPasswordRequest) res;
        Assertions.assertEquals(resCast.getUsername(), code);
        Assertions.assertEquals(resCast.getPassword(), redirectUri);
        System.out.println(resCast.getScope());
        Assertions.assertEquals(resCast.getScope(), scope);
    }

    @Test
    void shouldReturnCorrectObjectForCorrectRefreshTokenGrantRequestBody() throws IOException{
        OAuth2GrantType grantType = OAuth2GrantType.REFRESH_TOKEN_GRANT;
        String test = String.format("grant_type=%s&refresh_token=%s&scope=%s",
                grantType.value(),
                code,
                URLEncoder.encode(scope,StandardCharsets.UTF_8));
        OAuth2TokenRequest res = converter.readInternal(OAuth2TokenRequest.class, new TestHttpInputMessage(test, new HttpHeaders()));
        Assertions.assertNotNull(res);
        Assertions.assertEquals(res.getGrantType(), OAuth2GrantType.REFRESH_TOKEN_GRANT);
        Assertions.assertInstanceOf(OAuth2TokenRefreshGrantRequest.class, res);
        OAuth2TokenRefreshGrantRequest resCast = (OAuth2TokenRefreshGrantRequest) res;
        Assertions.assertEquals(resCast.getRefreshToken(), code);
        Assertions.assertEquals(resCast.getScope(), scope);
    }

    @Test
    void shouldReturnCorrectBodyForRefreshTokenGrantRequest() throws IOException{
        OAuth2GrantType grantType = OAuth2GrantType.REFRESH_TOKEN_GRANT;
        OAuth2TokenRefreshGrantRequest req = new OAuth2TokenRefreshGrantRequest(code, scope);
        TestHttpOutputMessage res = new TestHttpOutputMessage();
        converter.writeInternal(req, res);
        String body = res.getMessage();
        Assertions.assertNotNull(body);
        Assertions.assertTrue(body.contains(String.format("grant_type=%s", grantType.value())));
        Assertions.assertTrue(body.contains(String.format("refresh_token=%s", code)));
        Assertions.assertTrue(body.contains(String.format("scope=%s", URLEncoder.encode(scope,StandardCharsets.UTF_8))));
    }

    @Test
    void shouldReturnCorrectBodyForUsernamePasswordGrantRequest() throws IOException{
        OAuth2GrantType grantType = OAuth2GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS_GRANT;
        OAuth2TokenUserPasswordRequest req = new OAuth2TokenUserPasswordRequest(username, password, scope, clientId);
        TestHttpOutputMessage res = new TestHttpOutputMessage();
        converter.writeInternal(req, res);
        String body = res.getMessage();
        Assertions.assertNotNull(body);
        Assertions.assertTrue(body.contains(String.format("grant_type=%s", grantType.value())));
        Assertions.assertTrue(body.contains(String.format("username=%s", username)));
        Assertions.assertTrue(body.contains(String.format("password=%s", password)));
        Assertions.assertTrue(body.contains(String.format("client_id=%s", clientId)));
        Assertions.assertTrue(body.contains(String.format("scope=%s", URLEncoder.encode(scope,StandardCharsets.UTF_8))));
    }

    @Test
    void shouldReturnCorrectBodyForAuthCodeGrantRequest() throws IOException{
        OAuth2GrantType grantType = OAuth2GrantType.AUTHORIZATION_CODE_GRANT;
        OAuth2TokenAuthCodeGrantRequest req = new OAuth2TokenAuthCodeGrantRequest(code, redirectUri, clientId);
        TestHttpOutputMessage res = new TestHttpOutputMessage();
        converter.writeInternal(req, res);
        String body = res.getMessage();
        Assertions.assertNotNull(body);
        Assertions.assertTrue(body.contains(String.format("grant_type=%s", grantType.value())));
        Assertions.assertTrue(body.contains(String.format("code=%s", code)));
        Assertions.assertTrue(body.contains(String.format("redirect_uri=%s", URLEncoder.encode(redirectUri,StandardCharsets.UTF_8))));
        Assertions.assertTrue(body.contains(String.format("client_id=%s", clientId)));
    }

    @Test
    void shouldReturnNullForInvalidGrantType() throws IOException {
        String test = URLEncoder.encode(String.format("grant_type=%s",
                "invalid_grant"), StandardCharsets.UTF_8);
        OAuth2TokenRequest res = converter.readInternal(OAuth2TokenRequest.class, new TestHttpInputMessage(test, new HttpHeaders()));
        Assertions.assertNull(res);
    }


    static class TestHttpOutputMessage implements HttpOutputMessage{
        private final ByteArrayOutputStream body;
        private final HttpHeaders headers = new HttpHeaders();

        public TestHttpOutputMessage(){
            body = new ByteArrayOutputStream();
        }

        public String getMessage(){
            return body.toString();
        }

        @Override
        public OutputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }


    @Getter
    @Setter
    @AllArgsConstructor
    static class TestHttpInputMessage implements HttpInputMessage {
        private String body;
        private HttpHeaders headers;
        @Override
        public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(body.getBytes());
        }
    }
}
