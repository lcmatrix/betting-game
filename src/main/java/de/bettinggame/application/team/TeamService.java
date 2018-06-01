package de.bettinggame.application.team;

import java.util.Collection;
import java.util.List;
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
     * Loads all teams and create representing {@link GroupTO} objects containing their {@link TeamTO}.
     *
     * @return collection of groups
     */
    public Collection<GroupTO> getAllGroupsWithTeams() {
        Map<de.bettinggame.domain.Group, GroupTO> groupMap = new TreeMap<>();
        List<de.bettinggame.domain.Team> teams = teamRepository.findAllByGroupCharNotNull();
        for(de.bettinggame.domain.Team team : teams) {
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
