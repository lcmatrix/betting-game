package de.bettinggame.domain.repository;

import de.bettinggame.domain.game.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Location repository.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
