package de.bettinggame.model.repository;

import de.bettinggame.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Location repository.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
