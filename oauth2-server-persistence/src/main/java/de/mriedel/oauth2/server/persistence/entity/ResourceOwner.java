package de.mriedel.oauth2.server.persistence.entity;

import de.mriedel.oauth2.server.persistence.entity.embedabble.Address;
import de.mriedel.oauth2.server.persistence.entity.enumurated.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "locked", nullable = false)
    private Boolean banned;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "timezone")
    private String timezone;

    @OneToMany(mappedBy = "resourceOwner")
    private List<ResourceOwnerClientAuthorizations> clientAuthorizations;

    @OneToMany(mappedBy = "owner")
    private List<Client> ownedClients;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}