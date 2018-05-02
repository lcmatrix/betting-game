package de.bettinggame.model.repository;

import org.springframework.data.repository.CrudRepository;

import de.bettinggame.model.Team;

/**
 * Team repository.
 *
 * @author norman
 */
public interface TeamRepository extends CrudRepository<Team, Long> {
    //Stream<Team> findAllTeams();
}
