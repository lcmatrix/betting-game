package de.bettinggame.adapter;

import de.bettinggame.domain.game.Game;
import de.bettinggame.domain.game.TournamentLevel;
import de.bettinggame.domain.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class GameController implements AbstractController {
    private GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/game")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("game/all-games");
        List<Game> games = gameRepository.findByOrderByStarttime();
        Map<TournamentLevel, List<Game>> matchesByLevel = games.stream()
                .collect(Collectors.groupingBy(Game::getLevel, TreeMap::new,
                        Collectors.mapping(Function.identity(), Collectors.toList())));
        mav.addObject("gamesByLevel", matchesByLevel);
        return mav;
    }
}
