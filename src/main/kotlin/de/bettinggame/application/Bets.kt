package de.bettinggame.application

import de.bettinggame.domain.*
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException
import java.util.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Service
class BetService(
        private val betRepository: BetRepository,
        private val gameRepository: GameRepository,
        private val userRepository: UserRepository
) {
    fun findBetsForGame(gameIdentifier: String, userIdentifier: String): BetListTo {
        val game = gameRepository.findByIdentifier(gameIdentifier)
                ?: throw IllegalArgumentException("Game with Identifier $gameIdentifier not found")
        val otherBets = betRepository.findAllByGameIdentifierAndUserIdentifierIsNot(gameIdentifier, userIdentifier)
        val betTos = otherBets.map { bet -> Pair(bet, userRepository.findByIdentifier(bet.userIdentifier)) }
                .map { pair -> BetTo(pair.first, pair.second) }
        return BetListTo(game, betTos, LocaleContextHolder.getLocale())
    }

    @Transactional
    fun saveBet(betCommand: BetCommand, betIdentifier: String, gameIdentifier: String, userIdentifier: String) {
        val bet = betRepository.findByIdentifier(betIdentifier)
        if (bet != null) {
            bet.updateGoals(betCommand.goalsHomeTeam, betCommand.goalsGuestTeam)
        } else {
            betRepository.save(Bet(
                    betIdentifier,
                    betCommand.goalsHomeTeam,
                    betCommand.goalsGuestTeam,
                    Quota(1.0),
                    gameIdentifier,
                    userIdentifier))
        }
    }
}

data class BetCommand(
        @NotNull
        @Min(value = 0, message = "field.value.min.0")
        var goalsHomeTeam: Int?,

        @NotNull
        @Min(value = 0, message = "field.value.min.0")
        var goalsGuestTeam: Int?
) {
    constructor() : this(null, null)
}

data class BetListTo(
        val gameIdentifier: String,
        val homeTeam: String,
        val guestTeam: String,
        val result: String,
        val bets: List<BetTo>
) {
    val game: String
        get() = "$homeTeam vs. $guestTeam"

    constructor(game: Game, bets: List<BetTo>, locale: Locale) : this(
            game.identifier,
            game.homeTeam.name.getValueForLocale(locale),
            game.guestTeam.name.getValueForLocale(locale),
            "${game.goalsHomeTeam}:${game.goalsGuestTeam}",
            bets
    )
}

data class BetTo(val userTip: String, val user: String) {
    constructor(bet: Bet, user: User?) : this(
            "${bet.goalsHomeTeam}:${bet.goalsGuestTeam}",
            user?.username ?: "unknown"
    )
}
