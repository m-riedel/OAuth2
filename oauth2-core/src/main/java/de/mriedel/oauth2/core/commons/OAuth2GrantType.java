package de.mriedel.oauth2.core.commons;

import org.springframework.core.convert.converter.Converter;

public enum OAuth2GrantType {
    IMPLICIT_GRANT("implicit_grant"),
    AUTHORIZATION_CODE_GRANT("authorization_code"),
    RESOURCE_OWNER_PASSWORD_CREDENTIALS_GRANT("password"),
    CLIENT_CREDENTIALS_GRANT("client_credentials"),
    REFRESH_TOKEN_GRANT("refresh_token");
    private final String value;
    OAuth2GrantType(String value){
        this.value = value;
    }
    public String value(){
        return this.value;
    }

    public static class OAuth2GrantTypeConverter implements Converter<String, OAuth2GrantType> {
        @Override
        public OAuth2GrantType convert(String value) {
            return switch (value) {
                case "implicit_grant" -> OAuth2GrantType.IMPLICIT_GRANT;
                case "authorization_code" -> OAuth2GrantType.AUTHORIZATION_CODE_GRANT;
                case "password" -> OAuth2GrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS_GRANT;
                case "client_credentials" -> OAuth2GrantType.CLIENT_CREDENTIALS_GRANT;
                case "refresh_token" -> OAuth2GrantType.REFRESH_TOKEN_GRANT;
                default -> null;
            };
        }
    }
}
