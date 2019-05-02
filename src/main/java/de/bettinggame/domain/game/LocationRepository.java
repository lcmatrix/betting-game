package de.bettinggame.domain.game;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Location repository.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
