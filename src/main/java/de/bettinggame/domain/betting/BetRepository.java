package de.bettinggame.domain.betting;

import de.bettinggame.domain.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for entity {@link Bet}.
 */
public interface BetRepository extends JpaRepository<Bet, Integer> {
    List<Bet> findAllByGameIdentifier(Identity gameIdentifier);
}
