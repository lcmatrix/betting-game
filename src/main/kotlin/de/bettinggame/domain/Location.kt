package de.bettinggame.domain

import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Location class.
 */
@Entity
class Location(identifier: String,
               /**
                * Short unique key.
                */
               @NotNull
               val key: String,

               /**
                * Name of the arena.
                */
               @NotNull
               @Embedded
               @AttributeOverrides(
                       AttributeOverride(name = "de", column = Column(name = "name_de")),
                       AttributeOverride(name = "en", column = Column(name = "name_en"))
               )
               val name: Multilingual,

               /**
                * City of the arena.
                */
               @NotNull
               @Embedded
               @AttributeOverrides(
                       AttributeOverride(name = "de", column = Column(name = "city_de")),
                       AttributeOverride(name = "en", column = Column(name = "city_en"))
               )
               val city: Multilingual,

               /**
                * Country of the arena.
                */
               @NotNull
               @Embedded
               @AttributeOverrides(
                       AttributeOverride(name = "de", column = Column(name = "country_de")),
                       AttributeOverride(name = "en", column = Column(name = "country_en"))
               )
               val country: Multilingual
) : AbstractIdentifiableEntity(identifier)
