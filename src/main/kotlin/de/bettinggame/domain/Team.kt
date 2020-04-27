package de.bettinggame.domain

import de.bettinggame.domain.team.Team
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Team repository.
 *
 * @author norman
 */
interface TeamRepository: JpaRepository<Team, Int> {
    fun findAllByGroupCharNotNull(): List<Team>
}