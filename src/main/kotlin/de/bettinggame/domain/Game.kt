package de.bettinggame.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
class Game(identifier: String,
           /**
            * Kick-off date and time.
            */
           val starttime: OffsetDateTime,

           /**
            * Location of the game.
            */
           @ManyToOne(optional = false)
           @JoinColumn(name = "location_id", nullable = false)
           val location: Location,

           /**
            * Level in tournament.
            */
           @Column(nullable = false)
           @Enumerated(EnumType.STRING)
           val level: TournamentLevel,

           /**
            * Team one ("home team").
            */
           @OneToOne(optional = false)
           @JoinColumn(name = "home_team_id", nullable = false)
           val homeTeam: Team,

           /**
            * Team two ("guest team").
            */
           @OneToOne(optional = false)
           @JoinColumn(name = "guest_team_id", nullable = false)
           val guestTeam: Team,

           @Column(name = "goals_home_team")
           val goalsHomeTeam: Int?,

           @Column(name = "goals_guest_team")
           val goalsGuestTeam: Int?
) : AbstractIdentifiableEntity(identifier) {

    /**
     * Gets the goals scored by the given team.
     */
    fun goalsForTeam(team: Team): Int {
        return if (homeTeam == team) goalsHomeTeam ?: 0 else goalsGuestTeam ?: 0
    }

    /**
     * Gets the goals scored againts the given team.
     */
    fun goalsAgainstTeam(team: Team): Int {
        return if (homeTeam == team) goalsGuestTeam ?: 0 else goalsHomeTeam ?: 0
    }

    /**
     * Calculates points for the given team in this game.
     */
    fun pointsForTeam(team: Team): Int {
        val goalsForTeam = goalsForTeam(team)
        val goalsAgainstTeam = goalsAgainstTeam(team)
        // TODO: move this calculation into service if the points can be configured in future
        if (goalsAgainstTeam < goalsForTeam) {
            return 3
        } else if (goalsAgainstTeam == goalsForTeam) {
            return 1
        }
        return 0
    }
}

interface GameRepository : JpaRepository<Game, Int> {
    fun findAllByOrderByStarttime(): List<Game>

    @JvmDefault
    fun findByTeamInPreliminaryLevel(team: Team): List<Game> {
        return findByHomeTeamOrGuestTeam(team, team).filter { it.level == TournamentLevel.PRELIMINARY }
    }

    fun findByHomeTeamOrGuestTeam(homeTeam: Team, guestTeam: Team): List<Game>
}

enum class TournamentLevel(override val messageKey: String) : MessageKeyAware {
    PRELIMINARY("tournament.level.preliminary"),
    EIGTH_FINAL("tournament.level.eigth_final"),
    QUARTER_FINAL("tournament.level.quarter_final"),
    SEMI_FINAL("tournament.level.semi_final"),
    SMALL_FINAL("tournament.level.small_final"),
    FINAL("tournament.level.final");
}