package de.bettinggame.domain.repository;

import org.springframework.data.repository.CrudRepository;

import de.bettinggame.domain.Team;

/**
 * Team repository.
 *
 * @author norman
 */
public interface TeamRepository extends CrudRepository<Team, Long> {
    //Stream<Team> findAllTeams();
}
