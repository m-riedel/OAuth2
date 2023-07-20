package de.mriedel.oauth2.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OAuth2TokenErrorResponse(
        ErrorType error,
        @JsonProperty("error_description") @JsonInclude(JsonInclude.Include.NON_NULL) String errorDescription,
        @JsonProperty("error_uri") @JsonInclude(JsonInclude.Include.NON_NULL) String errorUri
){
    public static enum ErrorType{
        INVALID_REQUEST,
        INVALID_CLIENT,
        INVALID_GRANT,
        UNAUTHORIZED_CLIENT,
        UNSUPPORTED_GRANT_TYPE,
        INVALID_SCOPE;
        private final String error;
        ErrorType(){
            this.error = this.name().toLowerCase();
        }
        public String error(){
            return this.error;
        }
        @Override
        public String toString(){
            return this.error;
        }
    }
}
