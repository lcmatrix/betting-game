package de.bettinggame.adapter;

import de.bettinggame.domain.betting.Bet;
import de.bettinggame.domain.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BetController implements AbstractController {

    @Autowired
    private BetRepository betRepository;

    @GetMapping("/game/{gameIdentifier}/bet")
    public ModelAndView allBetsForGame(@PathVariable String gameIdentifier) {
        List<Bet> allBetsForGame = betRepository.findAllByGameIdentifier(gameIdentifier);
        ModelAndView mav = new ModelAndView("bet/bet-list");
        mav.addObject("allBets", allBetsForGame);
        return mav;
    }
}
