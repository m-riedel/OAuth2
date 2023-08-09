package de.mriedel.oauth2.server.persistence.repository;

import de.mriedel.oauth2.server.persistence.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
