/*
 * Created on 04.08.2017
 * 
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

package de.bettinggame.model.repository;

import org.springframework.data.repository.CrudRepository;

import de.bettinggame.model.User;

/**
 * Repository for {@link User} entity.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Find a user by its e-mail address
     * @param email e-Mail address
     * @return User
     */
    User findByEmail(String email);
}
