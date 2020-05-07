package de.bettinggame.domain

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Team repository.
 *
 * @author norman
 */
interface TeamRepository : JpaRepository<Team, Int> {
    fun findAllByGroupCharNotNull(): List<Team>
}

@Entity
class Team(identifier: String,
           /**
            * Team name or country if international.
            */
           @Embedded
           @AttributeOverrides(
               AttributeOverride(name = "de", column = Column(name = "name_de")),
               AttributeOverride(name = "en", column = Column(name = "name_en"))
           )
           val name: Multilingual,
           /**
            * Business key for team. Could be ISO Code for national teams.
            */
           @NotNull
           @Column(name = "team_key", nullable = false)
           val teamKey: String,
           /**
            * Group.
            */
           @Column(nullable = false)
           @Enumerated(EnumType.STRING)
           val groupChar: Group
) : AbstractIdentifiableEntity(identifier)


/**
 * Enumeration of groups.
 *
 * @author norman
 */
enum class Group {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;
}