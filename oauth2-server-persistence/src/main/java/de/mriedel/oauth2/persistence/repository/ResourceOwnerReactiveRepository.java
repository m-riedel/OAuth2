package de.mriedel.oauth2.persistence.repository;

import de.mriedel.oauth2.persistence.entity.ResourceOwner;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResourceOwnerReactiveRepository extends ReactiveCrudRepository<ResourceOwner, UUID> {
}
