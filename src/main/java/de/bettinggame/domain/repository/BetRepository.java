package de.bettinggame.domain.repository;

import de.bettinggame.domain.betting.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Integer> {
    List<Bet> findAllByGameIdentifier(String gameIdentifier);
}
