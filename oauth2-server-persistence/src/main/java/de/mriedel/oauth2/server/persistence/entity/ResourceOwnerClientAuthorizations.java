package de.mriedel.oauth2.server.persistence.entity;

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
public class ResourceOwnerClientAuthorizations {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "resource_owner_id", nullable = false)
    private ResourceOwner resourceOwner;

    @ManyToMany(mappedBy = "authorizations")
    private List<ClientRole> roles;

    @ElementCollection
    @CollectionTable(name = "resource_owner_client_authorization_scopes", joinColumns = @JoinColumn(name = "client_id"))
    @Enumerated(EnumType.STRING)
    private Set<String> scopes;

    @Column(name = "updated_scopes_at", nullable = false)
    private LocalDateTime updatedScopesAt;

}
