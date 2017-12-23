/*
 * Created on 23.12.2017
 *
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

package de.bettinggame.model;

import javax.persistence.Embeddable;

/**
 * Enumeration for tournament level.
 *
 * @author norman
 */
@Embeddable
public enum TournamentLevel {
    PRELIMINARY,
    EIGTH_FINAL,
    QUARTER_FINAL,
    SEMI_FINAL,
    SMALL_FINAL,
    FINAL
}
