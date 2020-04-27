package de.bettinggame.adapter

import de.bettinggame.application.GameService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class GameController(private val gameService: GameService) : AbstractController {
    @GetMapping("/game")
    fun list(): ModelAndView {
        val mav = ModelAndView("game/all-games")
        mav.addObject("gamesByLevel", gameService.findAllGames())
        return mav
    }
}