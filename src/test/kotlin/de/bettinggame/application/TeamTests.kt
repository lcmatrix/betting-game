package de.bettinggame.application

import de.bettinggame.domain.Multilingual
import de.bettinggame.domain.TeamRepository
import de.bettinggame.domain.team.Group
import de.bettinggame.domain.team.Team
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class TeamServiceTest {
    @Mock
    lateinit var teamRepository: TeamRepository
    lateinit var teamService: TeamService

    @Before
    fun setUp() {
        Mockito.`when`(teamRepository.findAllByGroupCharNotNull()).thenReturn(createListOfTeams())
        teamService = TeamService(teamRepository)
    }

    @Test
    fun testGetAllGroupsWithTeams() {
        val allGroupsWithTeams = teamService.getAllGroupsWithTeams()
        assertEquals(2, allGroupsWithTeams.size, "2 groups expected")
        val iterator = allGroupsWithTeams.iterator()
        val groupF = iterator.next()
        assertLocalizedTeams(Locale("de", "DE"), groupF.teams[0].name)
        assertEquals(Group.F.name, groupF.groupChar)

        val groupH = iterator.next()
        assertLocalizedTeams(Locale("fr", "FR"), groupH.teams[0].name)
        assertEquals(Group.H.name, groupH.groupChar)
    }

    private fun assertLocalizedTeams(locale: Locale, teamName: String) {
        assertEquals(locale.getDisplayCountry(Locale.getDefault()), teamName)
    }

    private fun createListOfTeams(): List<Team> {
        return listOf(
                Team(Multilingual("Deutschland", "Germany"), "DE", Group.F),
                Team(Multilingual("Frankreich", "France"), "FR", Group.H)
        )
    }
}