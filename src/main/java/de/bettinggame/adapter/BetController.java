package de.bettinggame.adapter;

import de.bettinggame.application.bet.BetService;
import de.bettinggame.application.bet.BetTo;
import de.bettinggame.domain.Identity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BetController implements AbstractController {

    @Autowired
    private BetService betService;

    @GetMapping("/game/{gameIdentifier}/bet")
    public ModelAndView allBetsForGame(@PathVariable String gameIdentifier) {
        List<BetTo> allBetsForGame = betService.findBetsForGame(new Identity(gameIdentifier));
        ModelAndView mav = new ModelAndView("bet/bet-list");
        mav.addObject("allBets", allBetsForGame);
        return mav;
    }
}
