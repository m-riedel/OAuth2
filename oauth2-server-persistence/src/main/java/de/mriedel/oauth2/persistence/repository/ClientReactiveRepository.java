package de.mriedel.oauth2.persistence.repository;

import de.mriedel.oauth2.persistence.entity.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface ClientReactiveRepository extends ReactiveCrudRepository<Client, UUID> {
}
