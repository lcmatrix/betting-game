package de.bettinggame.application

import de.bettinggame.domain.GameRepository
import de.bettinggame.domain.game.Game
import de.bettinggame.domain.game.Location
import de.bettinggame.domain.game.TournamentLevel
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.*

data class LocationTo(val name: String, val city: String, val country: String) {
    constructor(location: Location, locale: Locale) : this(
            location.name.getValueForLocale(locale),
            location.city.getValueForLocale(locale),
            location.country.getValueForLocale(locale)
    )
}

data class GameTo(
        val identifier: String,
        val homeTeam: String,
        val guestTeam: String,
        val location: LocationTo,
        val starttime: OffsetDateTime,
        val goalsHomeTeam: Int,
        val goalsGuestGuest: Int
) {
    constructor(game: Game, locale: Locale) : this(
            game.identifier,
            game.homeTeam.name.getValueForLocale(locale),
            game.guestTeam.name.getValueForLocale(LocaleContextHolder.getLocale()),
            LocationTo(game.location, locale),
            game.starttime,
            game.goalsHomeTeam,
            game.goalsGuestTeam
    )
}

@Service
class GameService(private val gameRepository: GameRepository) {

    fun findAllGames(): Map<TournamentLevel, List<GameTo>> {
        val locale = LocaleContextHolder.getLocale()
        return gameRepository.findAllByOrderByStarttime()
                .groupBy({ it.level }, { GameTo(it, locale) })
                .toSortedMap()
    }
}