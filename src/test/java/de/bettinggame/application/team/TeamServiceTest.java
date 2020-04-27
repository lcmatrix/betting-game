/*
 * Created on 28.12.2017
 *
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

package de.bettinggame.application.team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import de.bettinggame.application.GroupTo;
import de.bettinggame.application.TeamService;
import de.bettinggame.domain.Multilingual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import de.bettinggame.domain.team.Group;
import de.bettinggame.domain.team.Team;

/**
 * Unit test for {@link TeamService}.
 * 
 * @author norman
 */
public class TeamServiceTest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    private de.bettinggame.domain.TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService = new TeamService(teamRepository);

    @Before
    public void setUp() {
        Mockito.doAnswer(a -> createListOfTeams()).when(teamRepository).findAllByGroupCharNotNull();
    }

    @Test
    public void testGetAllGroupsWithTeams() {
        Collection<GroupTo> allGroupsWithTeams = teamService.getAllGroupsWithTeams();
        Assert.assertEquals("2 groups expected", 2, allGroupsWithTeams.size());
        Iterator<GroupTo> iterator = allGroupsWithTeams.iterator();
        GroupTo groupF = iterator.next();
        assertLocalisedTeamName(new Locale("de","DE"), groupF.getTeams().get(0).getName());
        Assert.assertEquals(Group.F.name(), groupF.getGroupChar());

        GroupTo groupH = iterator.next();
        Assert.assertEquals(Group.H.name(), groupH.getGroupChar());
        assertLocalisedTeamName(new Locale("fr", "FR"), groupH.getTeams().get(0).getName());

    }

    private List<Team> createListOfTeams() {
        Team a = new Team(new Multilingual("Deutschland", "Germany"), "DE", Group.F);
        Team b = new Team(new Multilingual("Frankreich","France"), "FR", Group.H);
        List<Team> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return list;
    }

    private void assertLocalisedTeamName(Locale expectedLocaleWithCountry, String teamName) {
        Assert.assertEquals(expectedLocaleWithCountry.getDisplayCountry(Locale.getDefault()), teamName);
    }
}
