package de.mriedel.oauth2.core.commons;

import lombok.Getter;

@Getter
public enum OAuth2Scope {
    EMAIL(
            "Email",
            "Grants the OAuth2 Client access to your email address.",
            "email",
            false
    ),
    EMAIL_VERIFIED(
            "Email, Verified Status",
            "Grants the OAuth2 Client access to your email address and if you have verified it.",
            "email;emailVerified",
            true
    ),
    USERNAME(
            "Username",
            "Grants the OAuth2 Client access to your username.",
            "username",
            false
    ),
    FIRST_NAME(
            "First Name",
            "Grants the OAuth2 Client access to your first name.",
            "firstName",
            false
    ),
    LAST_NAME(
            "Last Name",
            "Grants the OAuth2 Client access to your last name.",
            "lastName",
            false
    ),
    NAME(
            "Name",
            "Grants the OAuth2 Client access to your full name.",
            "firstName;lastName",
            true
    ),
    ADDRESS(
            "Address",
            "Grants the OAuth2 Client access to your full address.",
            "address",
            false
    ),
    PHONE_NUMBER(
            "Phone Number",
            "Grants the OAuth2 Client access to your phone number.",
            "phoneNumber",
            false
    ),
    GENDER(
            "Gender",
            "Grants the OAuth2 Client access to your gender.",
            "gender",
            false
    ),
    DATE_OF_BIRTH(
            "Date of Birth",
            "Grants the OAuth2 Client access to your date of birth.",
            "dateOfBirth",
            false
    ),
    PROFILE_PICTURE(
            "Profile Picture",
            "Grants the OAuth2 Client access to your profile picture.",
            "profilePicture",
            false
    );

    private final String scopeName = this.name();
    private final String presentationName;
    private final String description;
    private final String field;
    private final boolean isComposite;
    private final String compositeSeparator = ";";

    OAuth2Scope(String presentationName, String description, String field, boolean isComposite) {
        this.presentationName = presentationName;
        this.field = field;
        this.isComposite = isComposite;
        this.description = description;
    }
}
