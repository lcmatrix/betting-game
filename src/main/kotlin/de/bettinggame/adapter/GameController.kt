package de.bettinggame.adapter

import de.bettinggame.application.GameService
import de.bettinggame.application.GameTo
import de.bettinggame.domain.TournamentLevel
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
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

@RestController
class ApiGameController(private val gameService: GameService) {

    @GetMapping("/api/games")
    fun listGames(): Map<TournamentLevel, List<GameTo>> {
        return gameService.findAllGames()
    }
}
