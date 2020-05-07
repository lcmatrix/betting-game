package de.bettinggame.domain

import java.util.*
import javax.persistence.*

/**
 * Abstract class for entities. Provides dbId attribute.
 */
@MappedSuperclass
abstract class AbstractIdEntity(
        /**
         * Id column for Hibernate.
         */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        val dbId: Int?)

/**
 * Abstract base class for aggregates. It provides a functional identifier.
 */
@MappedSuperclass
abstract class AbstractIdentifiableEntity(
        /**
         * Identifier for an entity. It's an UUID.
         */
        val identifier: String
) : AbstractIdEntity(null)

/**
 * Instances of this interface have a message key for their representation.
 */
interface MessageKeyAware {
    /**
     * Returns the message key for this instance.
     * @return message key
     */
    val messageKey: String
}

/**
 * A class to support multilingual attributes in entities.
 */
@Embeddable
class Multilingual(
        private var de: String,
        private var en: String
) {
    fun getValueForLocale(locale: Locale): String {
        if (Locale.ENGLISH.language == locale.language) {
            return en
        }
        // as I'm German, so default/fallback is
        return de
    }
}