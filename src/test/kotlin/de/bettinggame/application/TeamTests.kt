package de.bettinggame.application

import de.bettinggame.domain.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.time.OffsetDateTime
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class TeamServiceTest {
    @Mock
    lateinit var teamRepository: TeamRepository

    @Mock
    lateinit var gameRepository: GameRepository
    lateinit var teamService: TeamService

    val teamOneId = "1"
    val teamTwoId = "2"
    val teamThreeId = "3"

    val teamGermany = Team(teamOneId, Multilingual("Deutschland", "Germany"), "DE", Group.F)
    val teamFrance = Team(teamTwoId, Multilingual("Frankreich", "France"), "FR", Group.H)
    val teamMexico = Team(teamThreeId, Multilingual("Mexiko", "Mexico"), "MX", Group.F)

    @Before
    fun setUp() {
        val games = createGamesForTeams()
        given(teamRepository.findAllByGroupCharNotNull()).willReturn(listOf(teamGermany, teamFrance, teamMexico))
        given(gameRepository.findByTeamInPreliminaryLevel(teamGermany)).willReturn(games)
        given(gameRepository.findByTeamInPreliminaryLevel(teamMexico)).willReturn(games)

        teamService = TeamService(teamRepository, gameRepository)
    }

    @Test
    fun testGetAllGroupsWithTeams() {
        val allGroupsWithTeams = teamService.getAllGroupsWithTeams()
        assertEquals(2, allGroupsWithTeams.size, "2 groups expected")

        val iterator = allGroupsWithTeams.iterator()
        val groupF = iterator.next()
        assertEquals(Group.F.name, groupF.groupChar)
        assertEquals(2, groupF.teams.size)
        assertEquals(teamGermany.teamKey, groupF.teams[0].teamKey)

        val germany = groupF.teams[0]
        assertEquals(4, germany.points)
        assertEquals(4, germany.goals)
        assertEquals(1, germany.goalsAgainst)
        val mexico = groupF.teams[1]
        assertEquals(1, mexico.points)
        assertEquals(1, mexico.goals)
        assertEquals(4, mexico.goalsAgainst)

        assertEquals(teamMexico.teamKey, groupF.teams[1].teamKey)

        val groupH = iterator.next()
        assertEquals(Group.H.name, groupH.groupChar)
        assertEquals(1, groupH.teams.size)
        assertEquals(teamFrance.teamKey, groupH.teams[0].teamKey)
    }

    @Test
    fun testTeamToCompare() {
        // compare points
        val team1 = TeamTo("team1", "t1", 3, 5, 2)
        val team2 = TeamTo("team2", "t2", 4, 6, 1)
        assertTrue { team1.compareTo(team2) > 0 }

        // compare goal difference
        val team3 = TeamTo("team3", "t3", 3, 5, 2)
        val team4 = TeamTo("team4", "t4", 3, 6, 1)
        assertTrue { team3.compareTo(team4) > 0 }

        // compare scored goals
        val team5 = TeamTo("team5", "t5", 3, 7, 2)
        val team6 = TeamTo("team6", "t6", 3, 6, 3)
        assertTrue { team5.compareTo(team6) < 0 }

        val team7 = TeamTo("team7", "t7", 3, 6, 3)
        val team8 = TeamTo("team8", "t8", 3, 6, 3)
        assertTrue { team7.compareTo(team8) == 0 }
    }

    private fun createGamesForTeams(): List<Game> {
        return listOf(
                Game(
                        UUID.randomUUID().toString(),
                        OffsetDateTime.now(),
                        createLocation(),
                        TournamentLevel.PRELIMINARY,
                        teamGermany,
                        teamMexico,
                        3,
                        0
                ),
                Game(
                        UUID.randomUUID().toString(),
                        OffsetDateTime.now(),
                        createLocation(),
                        TournamentLevel.PRELIMINARY,
                        teamMexico,
                        teamGermany,
                        1,
                        1
                ),
        )
    }

    private fun createLocation(): Location {
        return Location(UUID.randomUUID().toString(), "x", Multilingual("location", "location"), Multilingual("Stadt", "city"),
                Multilingual("land", "country"))
    }
}
