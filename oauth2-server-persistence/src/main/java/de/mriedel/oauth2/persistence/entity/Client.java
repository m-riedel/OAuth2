package de.mriedel.oauth2.persistence.entity;

import de.mriedel.oauth2.core.commons.OAuth2ClientType;
import de.mriedel.oauth2.persistence.entity.constraint.DefaultableSingleOccurrenceConstraint;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    @Enumerated(EnumType.STRING)
    private OAuth2ClientType type;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "icon")
    private String icon;

    @OneToMany(mappedBy = "client")
    private Set<ClientScope> scopes;

    @Column(name = "updated_scopes_at", nullable = false)
    private LocalDateTime updatedScopesAt;


    @OneToMany(mappedBy = "client")
    @DefaultableSingleOccurrenceConstraint
    private List<ClientRole> roles;

    @OneToMany(mappedBy = "client")
    @DefaultableSingleOccurrenceConstraint
    private List<ClientRedirectUrl> redirectUrls;

    @OneToMany(mappedBy = "client")
    private List<ResourceOwnerClientAuthorizations> clientAuthorizations;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private ResourceOwner owner;
}