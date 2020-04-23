package de.bettinggame.adapter;

import de.bettinggame.application.bet.BetCommand;
import de.bettinggame.application.bet.BetListTO;
import de.bettinggame.application.bet.BetService;
import de.bettinggame.domain.AbstractIdentifiableEntity;
import de.bettinggame.domain.Identity;
import de.bettinggame.domain.betting.Bet;
import de.bettinggame.domain.betting.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
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
    public ModelAndView allBetsForGame(@PathVariable String gameIdentifier, Principal principal,
            RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("bet/bet-list");
        BetListTO betList = betService.findBetsForGame(gameIdentifier, principal.getName());
        Optional<Bet> myBet = betRepository.findByUserIdentifier(Identity.buildIdentifier(principal.getName()));
        mav.addObject("betCommand", myBet.map(BetCommand::new).orElse(new BetCommand()));
        mav.addObject("betList", betList);
        mav.addObject("gameIdentifier", gameIdentifier);
        mav.addObject("betIdentifier", myBet.map(AbstractIdentifiableEntity::identifier)
                .orElse(Identity.buildNewIdentity().identifier()));
        mav.addAllObjects(redirectAttributes.getFlashAttributes());
        return mav;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/game/{gameIdentifier}/bet/{betIdentifier}")
    public String saveBet(@PathVariable String betIdentifier, @PathVariable String gameIdentifier,
            @Valid BetCommand betCommand, BindingResult bindingResult, Principal principal, Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("betList", betService.findBetsForGame(gameIdentifier, principal.getName()));
            return "bet/bet-list";
        }
        betService.saveBet(betCommand, betIdentifier, gameIdentifier, principal.getName());
        redirectAttributes.addFlashAttribute("confirmMessage", "bets.confirm.saved");
        return "redirect:/game/" + gameIdentifier + "/bets";
    }
}
