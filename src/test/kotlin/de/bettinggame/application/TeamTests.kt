package de.bettinggame.application

import de.bettinggame.domain.Multilingual
import kotlin.test.assertEquals
import java.util.Locale

import de.bettinggame.domain.TeamRepository
import de.bettinggame.domain.team.Group
import de.bettinggame.domain.team.Team
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class TeamServiceTest {
    @Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()
    @Mock
    lateinit var teamRepository: TeamRepository
    lateinit var teamService: TeamService

    @Before
    fun setUp() {
        Mockito.doAnswer { createListOfTeams() }.`when`(teamRepository.findAllByGroupCharNotNull())
        teamService = TeamService(teamRepository)
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