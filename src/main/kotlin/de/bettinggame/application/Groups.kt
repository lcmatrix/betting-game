package de.bettinggame.application

import de.bettinggame.domain.TeamRepository
import de.bettinggame.domain.Group
import de.bettinggame.domain.Team
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import java.util.TreeMap

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
        val groupChar: String
) {
    val teams = mutableListOf<TeamTo>()
}

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
        val groups: TreeMap<Group, GroupTo> = TreeMap()
        val allTeams = teamRepository.findAllByGroupCharNotNull()
        allTeams.forEach {
            val group: GroupTo = groups[it.groupChar] ?: GroupTo(it.groupChar.name)
            group.teams.add(TeamTo(it))
            groups[it.groupChar] = group
        }
        return groups.values
    }
}