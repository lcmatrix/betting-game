package de.bettinggame.application

import de.bettinggame.domain.Team
import de.bettinggame.domain.TeamRepository
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service

data class TeamTo(
        val name: String,
        val teamKey: String,
        val points: Int = 0,
        val goals: Int = 0,
        val goalsAgainst: Int = 0
) {
    val logoIconPath = "$teamKey.png"

    constructor(team: Team) : this(
            team.name.getValueForLocale(LocaleContextHolder.getLocale()),
            team.teamKey)
}

data class GroupTo(
        val groupChar: String,
        val teams: List<TeamTo>
)

/**
 * Team service.
 *
 * @author norman
 */
@Service
class TeamService(private val teamRepository: TeamRepository) {

    /**
     * Loads all teams and create representing {@link GroupTO} objects containing their {@link TeamTO}.
     *
     * @return collection of groups
     */
    fun getAllGroupsWithTeams(): Collection<GroupTo> {
        val allTeams = teamRepository.findAllByGroupCharNotNull()
        return allTeams.groupBy { it.groupChar }
                .mapValues { entry -> entry.value.map { team -> TeamTo(team) } }
                .map { entry -> GroupTo(entry.key.name, entry.value) }
    }
}