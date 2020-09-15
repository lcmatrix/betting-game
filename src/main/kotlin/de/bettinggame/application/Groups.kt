package de.bettinggame.application

import de.bettinggame.domain.GameRepository
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
) : Comparable<TeamTo> {
    constructor(team: Team) : this(
            team.name.getValueForLocale(LocaleContextHolder.getLocale()),
            team.teamKey)

    override fun compareTo(other: TeamTo): Int {
        if (this.points > other.points) {
            return -1
        } else if (this.points < other.points) {
            return 1
        } else if (this.goals - this.goalsAgainst == other.goals - other.goalsAgainst) {
            return other.goals - this.goals
        } else {
            return (other.goals - other.goalsAgainst) - (this.goals - this.goalsAgainst)
        }
    }
}

data class GroupTo(
        val groupChar: String,
        val teams: List<TeamTo>
)

/**
 * Some private class for collecting team statistics in this file.
 */
private data class TeamStats(
        var identifier: String,
        var points: Int = 0,
        var goals: Int = 0,
        var goalsAgainst: Int = 0
)

/**
 * Team service.
 *
 * @author norman
 */
@Service
class TeamService(private val teamRepository: TeamRepository, private val gameRepository: GameRepository) {

    /**
     * Loads all teams and create representing {@link GroupTO} objects containing their {@link TeamTO}.
     *
     * @return collection of groups
     */
    fun getAllGroupsWithTeams(): Collection<GroupTo> {
        val allTeams = teamRepository.findAllByGroupCharNotNull()
        return allTeams.groupBy({ it }, { gameRepository.findByTeamInPreliminaryLevel(it) })
                .mapValues { it.value.flatten() }
                .mapValues {
                    it.value.fold(TeamStats(it.key.identifier), { stats, game ->
                        TeamStats(
                                stats.identifier,
                                stats.points + game.pointsForTeam(it.key),
                                stats.goals + game.goalsForTeam(it.key),
                                stats.goalsAgainst + game.goalsAgainstTeam(it.key)
                        )
                    })
                }
                .entries.groupBy(
                        { it.key.groupChar },
                        {
                            TeamTo(
                                    it.key.name.getValueForLocale(LocaleContextHolder.getLocale()),
                                    it.key.teamKey,
                                    it.value.points,
                                    it.value.goals,
                                    it.value.goalsAgainst
                            )
                        })
                .map { entry -> GroupTo(entry.key.name, entry.value.sorted()) }
    }
}
