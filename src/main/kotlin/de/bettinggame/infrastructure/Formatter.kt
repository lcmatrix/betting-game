package de.bettinggame.infrastructure

import de.bettinggame.domain.MessageKeyAware
import de.bettinggame.domain.Multilingual
import org.springframework.context.MessageSource
import org.springframework.format.Formatter
import java.util.*

/**
 * Formatter for instances of {@link Multilingual}.
 */
class MultilingualFormatter : Formatter<Multilingual?> {
    override fun parse(text: String, locale: Locale): Multilingual {
        if (Locale.ENGLISH.language == locale.language) {
            return Multilingual(null, text)
        }
        return Multilingual(text, null)
    }

    override fun print(obj: Multilingual?, locale: Locale): String? {
        return obj?.getValueForLocale(locale)
    }
}

/**
 * Formatter for instances of {@link MessageKeyAware}.
 */
class MessageKeyFormatter(private val messageSource: MessageSource) : Formatter<MessageKeyAware> {
    override fun print(obj: MessageKeyAware, locale: Locale): String? {
        return messageSource.getMessage(obj.messageKey(), null, locale);
    }

    override fun parse(text: String, locale: Locale): MessageKeyAware =
            throw UnsupportedOperationException("Can't create an instance of MessageKeyAware!")

}
