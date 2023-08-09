package de.mriedel.oauth2.server.persistence.entity;

import de.mriedel.oauth2.server.persistence.commons.Defaultable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRole implements Defaultable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany(mappedBy = "roles")
    @JoinTable(
            name = "client_resource_owner_roles",
            joinColumns = @JoinColumn(name = "client_role_id"),
            inverseJoinColumns = @JoinColumn(name = "authorization_id")
    )
    private List<ResourceOwnerClientAuthorizations> authorizations;

    @Override
    public boolean isDefault() {
        return isDefault;
    }
}
