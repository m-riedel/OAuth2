package de.mriedel.oauth2.server.persistence.repository;

import de.mriedel.oauth2.server.persistence.entity.ResourceOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResourceOwnerRepository extends JpaRepository<ResourceOwner, UUID> {
}
