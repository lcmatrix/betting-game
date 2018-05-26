package de.bettinggame.domain.repository;

import de.bettinggame.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findByOrderByStarttime();
}
