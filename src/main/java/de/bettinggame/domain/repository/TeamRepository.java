package de.bettinggame.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bettinggame.domain.team.Team;

import java.util.List;

/**
 * Team repository.
 *
 * @author norman
 */
public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findAllByGroupCharNotNull();
}
