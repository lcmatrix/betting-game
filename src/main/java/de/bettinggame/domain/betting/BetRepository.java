package de.bettinggame.domain.betting;

import de.bettinggame.domain.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for entity {@link Bet}.
 */
public interface BetRepository extends JpaRepository<Bet, Integer> {
    List<Bet> findAllByGameIdentifierAndUserIdentifierIsNot(Identity gameIdentifier, Identity userIdentifier);

    Optional<Bet> findByUserIdentifier(Identity userIdentifier);

    Optional<Bet> findByIdentifier(Identity betIdentifier);
}
