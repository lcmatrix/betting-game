/*
 * Created on 24.12.2017
 *
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

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
