package de.bettinggame.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.lang.IllegalArgumentException
import javax.persistence.*

@Entity
class Bet(identifier: String,
          @Embedded
          @AttributeOverride(name = "value", column = Column(name = "quota"))
          var quota: Quota,
          val gameIdentifier: String,
          val userIdentifier: String
) : AbstractIdentifiableEntity(identifier) {
    var goalsGuestTeam: Int = 0
        private set
    var goalsHomeTeam: Int = 0
        private set

    constructor(identifier: String,
                goalsHomeTeam: Int?,
                goalsGuestTeam: Int?,
                quota: Quota,
                gameIdentifier: String,
                userIdentifier: String
    ) : this(identifier, quota, gameIdentifier, userIdentifier) {
        this.goalsGuestTeam = goalsGuestTeam ?: 0
        this.goalsHomeTeam = goalsHomeTeam ?: 0
    }

    fun updateGoals(goalsHomeTeam: Int?, goalsGuestTeam: Int?) {
        if (goalsGuestTeam != null && goalsGuestTeam >= 0 && goalsHomeTeam != null && goalsHomeTeam >= 0) {
            this.goalsHomeTeam = goalsHomeTeam
            this.goalsGuestTeam = goalsGuestTeam
        } else {
            throw IllegalArgumentException("Negative values not allowed")
        }
    }
}

@Embeddable
data class Quota(val value: Double)

/**
 * Repository for entity {@link Bet}.
 */
interface BetRepository : JpaRepository<Bet, Int>, IdentifierRepository {
    fun findAllByGameIdentifierAndUserIdentifierIsNot(gameIdentifier: String, userIdentifier: String): List<Bet>

    fun findByGameIdentifierAndUserIdentifier(gameIdentifier: String, userIdentifier: String): Bet?

    fun findByIdentifier(betIdentifier: String): Bet?
}
