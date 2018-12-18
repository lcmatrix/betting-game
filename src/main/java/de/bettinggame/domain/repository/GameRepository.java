package de.bettinggame.domain.repository;

import de.bettinggame.domain.Identity;
import de.bettinggame.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for entity {@link Game}.
 */
public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findByOrderByStarttime();

    Optional<Game> findByIdentifier(Identity identifier);
}
