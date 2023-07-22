package de.mriedel.oauth2.persistence.entity;

import de.mriedel.oauth2.persistence.commons.Defaultable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRedirectUrl implements Defaultable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @Override
    public boolean isDefault() {
        return isDefault;
    }
}
