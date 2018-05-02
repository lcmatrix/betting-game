package de.bettinggame.application.team;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.bettinggame.domain.repository.TeamRepository;

/**
 * Team service.
 *
 * @author norman
 */
@Service
public class TeamService {

    @Resource
    private TeamRepository teamRepository;

    /**
     * Loads all teams and create representing {@link Group} objects containing their {@link Team}.
     *
     * @return collection of groups
     */
    public Collection<Group> getAllGroupsWithTeams() {
        Map<de.bettinggame.domain.Group, Group> groupMap = new TreeMap<>();
        Iterable<de.bettinggame.domain.Team> teams = teamRepository.findAll();
        for(de.bettinggame.domain.Team team : teams) {
            Group group = groupMap.get(team.getGroupChar());
            if (group == null) {
                group = new Group(team.getGroupChar());
            }
            group.addTeam(new Team(team));
            groupMap.put(team.getGroupChar(), group);
        }

        /*try(Stream<Team> allTeams = teamRepository.findAllTeams()) {
            Map<de.bettinggame.domain.Group, List<Team>> collect =
                    allTeams.collect(Collectors.toMap(
                            Team::getGroupChar,
                            t -> new ArrayList<>(new Team(t)),
                            (left, right) -> {left.addAll(right); return left; }));
        }*/
        return groupMap.values();
    }
}
