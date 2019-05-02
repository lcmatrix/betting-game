package de.bettinggame.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Team repository.
 *
 * @author norman
 */
public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findAllByGroupCharNotNull();
}
