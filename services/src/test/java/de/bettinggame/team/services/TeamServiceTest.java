/*
 * Created on 28.12.2017
 *
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

package de.bettinggame.team.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import de.bettinggame.model.Group;
import de.bettinggame.model.Team;
import de.bettinggame.model.repository.TeamRepository;

/**
 * Unit test for {@link TeamService}.
 * 
 * @author norman
 */
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository = Mockito.mock(TeamRepository.class);

    @InjectMocks
    private TeamService teamService = new TeamService();

    @Before
    public void setUp() {
        Mockito.doAnswer(a -> createListOfTeams()).when(teamRepository).findAll();
    }

    @Test
    public void testGetAllGroupsWithTeams() {
        Collection<de.bettinggame.team.objects.Group> allGroupsWithTeams = teamService.getAllGroupsWithTeams();
        Assert.assertEquals("2 groups expected", 2, allGroupsWithTeams.size());
        Iterator<de.bettinggame.team.objects.Group> iterator = allGroupsWithTeams.iterator();
        de.bettinggame.team.objects.Group groupF = iterator.next();
        Assert.assertEquals(Group.F.name(), groupF.getGroupChar());
        Assert.assertEquals("Germany", groupF.getTeams().get(0).getName());

        de.bettinggame.team.objects.Group groupH = iterator.next();
        Assert.assertEquals(Group.H.name(), groupH.getGroupChar());
        Assert.assertEquals("England", groupH.getTeams().get(0).getName());

    }

    private List<Team> createListOfTeams() {
        Team a = new Team("Germany", "DE", Group.F);
        Team b = new Team("England", "EN", Group.H);
        List<Team> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return list;
    }
}
