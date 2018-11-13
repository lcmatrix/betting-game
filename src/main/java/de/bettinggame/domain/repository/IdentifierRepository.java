package de.bettinggame.domain.repository;

import java.util.UUID;

public interface IdentifierRepository {
    default String nextIdentifier() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
