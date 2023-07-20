package de.mriedel.oauth2.core.commons;

import org.springframework.core.convert.converter.Converter;

public enum OAuth2ResponseType {
    CODE ("code"),
    TOKEN("token");
    private final String value;
    OAuth2ResponseType(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }

    public static class OAuth2ResponseTypeConverter implements Converter<String, OAuth2ResponseType> {
        @Override
        public OAuth2ResponseType convert(String value) {
            return switch (value) {
                case "code" -> OAuth2ResponseType.CODE;
                case "token" -> OAuth2ResponseType.TOKEN;
                default -> null;
            };
        }
    }
}
