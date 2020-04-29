package de.bettinggame.infrastructure

import de.bettinggame.domain.MessageKeyAware
import de.bettinggame.domain.Multilingual
import org.springframework.context.MessageSource
import org.springframework.format.Printer
import java.util.*

/**
 * Printer for instances of {@link Multilingual}.
 */
class MultilingualPrinter : Printer<Multilingual?> {
    override fun print(obj: Multilingual?, locale: Locale): String? {
        return obj?.getValueForLocale(locale)
    }
}

/**
 * Printer for instances of {@link MessageKeyAware}.
 */
class MessageKeyPrinter(private val messageSource: MessageSource) : Printer<MessageKeyAware> {
    override fun print(obj: MessageKeyAware, locale: Locale): String? {
        return messageSource.getMessage(obj.messageKey(), null, locale)
    }
}
