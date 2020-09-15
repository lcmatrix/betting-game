package de.bettinggame.adapter

import de.bettinggame.application.BetCommand
import de.bettinggame.application.BetService
import de.bettinggame.domain.BetRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.security.Principal
import javax.validation.Valid

@Controller
class BetController(private val betService: BetService, private val betRepository: BetRepository) : AbstractController {

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/game/{gameIdentifier}/bets")
    fun allBetsForGame(@PathVariable gameIdentifier: String, principal: Principal,
                       redirectAttributes: RedirectAttributes): ModelAndView {
        val mav = ModelAndView("bet/bet-list")
        val myBet = betRepository.findByGameIdentifierAndUserIdentifier(gameIdentifier, principal.name)
        mav.addObject("betCommand", myBet ?: BetCommand())
        mav.addObject("betList", betService.findBetsForGame(gameIdentifier, principal.name))
        mav.addObject("gameIdentifier", gameIdentifier)
        mav.addObject("betIdentifier", myBet?.identifier ?: betRepository.nextIdentifier())
        return mav
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/game/{gameIdentifier}/bet/{betIdentifier}")
    fun saveBet(@PathVariable gameIdentifier: String, @PathVariable betIdentifier: String,
                @Valid betCommand: BetCommand, bindingResult: BindingResult, principal: Principal, model: Model,
                redirectAttributes: RedirectAttributes): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("betList", betService.findBetsForGame(gameIdentifier, principal.name))
        }
        betService.saveBet(betCommand, betIdentifier, gameIdentifier, principal.name)
        redirectAttributes.addFlashAttribute("confirmMessage", "bets.confirm,saved")
        return "redirect:/game/$gameIdentifier/bets"
    }
}
