package de.bettinggame.application.team;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.bettinggame.domain.team.Group;
import de.bettinggame.domain.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bettinggame.domain.repository.TeamRepository;

/**
 * Team service.
 *
 * @author norman
 */
@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    /**
     * Loads all teams and create representing {@link GroupTO} objects containing their {@link TeamTO}.
     *
     * @return collection of groups
     */
    public Collection<GroupTO> getAllGroupsWithTeams() {
        Map<Group, GroupTO> groupMap = new TreeMap<>();
        List<Team> teams = teamRepository.findAllByGroupCharNotNull();
        for(Team team : teams) {
            GroupTO group = groupMap.get(team.getGroupChar());
            if (group == null) {
                group = new GroupTO(team.getGroupChar());
            }
            group.addTeam(new TeamTO(team));
            groupMap.put(team.getGroupChar(), group);
        }

        /*try(Stream<Team> allTeams = teamRepository.findAllTeams()) {
            Map<de.bettinggame.domain.GroupTO, List<Team>> collect =
                    allTeams.collect(Collectors.toMap(
                            Team::getGroupChar,
                            t -> new ArrayList<>(new Team(t)),
                            (left, right) -> {left.addAll(right); return left; }));
        }*/
        return groupMap.values();
    }
}
