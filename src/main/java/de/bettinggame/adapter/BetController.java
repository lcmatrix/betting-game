package de.bettinggame.adapter;

import de.bettinggame.application.bet.BetCommand;
import de.bettinggame.application.bet.BetService;
import de.bettinggame.application.bet.BetTo;
import de.bettinggame.domain.AbstractIdentifiableEntity;
import de.bettinggame.domain.Identity;
import de.bettinggame.domain.betting.Bet;
import de.bettinggame.domain.betting.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class BetController implements AbstractController {
    private BetService betService;
    private BetRepository betRepository;

    @Autowired
    public BetController(BetService betService, BetRepository betRepository) {
        this.betService = betService;
        this.betRepository = betRepository;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/game/{gameIdentifier}/bets")
    public ModelAndView allBetsForGame(@PathVariable String gameIdentifier, Principal principal) {
        ModelAndView mav = new ModelAndView("bet/bet-list");
        List<BetTo> allBetsForGame = betService.findBetsForGame(gameIdentifier, principal.getName());
        Optional<Bet> myBet =
                betRepository.findByUserIdentifier(Identity.buildIdentifier(principal.getName()));
        final Optional<String> gameLabel = allBetsForGame.stream().findFirst().map(BetTo::getGame);
        mav.addObject("betCommand", myBet.map(BetCommand::new).orElse(new BetCommand()));
        mav.addObject("allBets", allBetsForGame);
        mav.addObject("gameIdentifier", gameIdentifier);
        mav.addObject("betIdentifier", myBet.map(AbstractIdentifiableEntity::identifier)
                        .orElse(Identity.buildNewIdentity().identifier()));
        gameLabel.ifPresent(label -> mav.addObject("game", label));
        return mav;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/game/{gameIdentifier}/bet/{betIdentifier}")
    public String saveBet(@PathVariable String betIdentifier, @PathVariable String gameIdentifier,
                          @Valid BetCommand betCommand, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "bet/bet-list";
        }
        betService.saveBet(betCommand, betIdentifier, gameIdentifier, principal.getName());
        return "redirect:/game/" + gameIdentifier + "/bets";
    }
}
