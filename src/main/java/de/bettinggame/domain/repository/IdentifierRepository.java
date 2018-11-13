package de.bettinggame.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IdentifierRepository<T, ID> extends JpaRepository<T, ID> {
    default String nextIdentifier() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
