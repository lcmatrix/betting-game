package de.bettinggame.domain

import de.bettinggame.domain.game.Game
import org.springframework.data.jpa.repository.JpaRepository

enum class TournamentLevel(private val messageKey: String) {
    PRELIMINARY("tournament.level.preliminary"),
    EIGTH_FINAL("tournament.level.eigth_final"),
    QUARTER_FINAL("tournament.level.quarter_final"),
    SEMI_FINAL("tournament.level.semi_final"),
    SMALL_FINAL("tournament.level.small_final"),
    FINAL("tournament.level.final");
}

interface GameRepository : JpaRepository<Game, Int> {
    fun findAllByOrderByStarttime(): List<Game>
}