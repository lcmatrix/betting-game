package de.bettinggame.domain.repository;

import de.bettinggame.domain.Identity;
import de.bettinggame.domain.betting.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for entity {@link Bet}.
 */
public interface BetRepository extends JpaRepository<Bet, Integer> {
    List<Bet> findAllByGameIdentifier(Identity gameIdentifier);
}
