package de.bettinggame.adapter;

import de.bettinggame.application.bet.BetService;
import de.bettinggame.application.bet.BetTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class BetController implements AbstractController {
    private BetService betService;

    @Autowired
    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/game/{gameIdentifier}/bets")
    public ModelAndView allBetsForGame(@PathVariable String gameIdentifier, Principal principal) {
        List<BetTo> allBetsForGame = betService.findBetsForGame(gameIdentifier);
        ModelAndView mav = new ModelAndView("bet/bet-list");
        mav.addObject("allBets", allBetsForGame);
        return mav;
    }
}
