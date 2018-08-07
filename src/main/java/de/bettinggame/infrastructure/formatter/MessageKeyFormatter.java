package de.bettinggame.infrastructure.formatter;

import de.bettinggame.domain.support.MessageKeyAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

import java.util.Locale;

/**
 * Formatter for instances of {@link MessageKeyAware}.
 */
public class MessageKeyFormatter implements Formatter<MessageKeyAware> {
    @Autowired
    private MessageSource messageSource;

    @Override
    public MessageKeyAware parse(String text, Locale locale) {
        throw new UnsupportedOperationException("Can't create an instance of MessageKeyAware!");
    }

    @Override
    public String print(MessageKeyAware object, Locale locale) {
        return messageSource.getMessage(object.messageKey(), new Object[0], locale);
    }
}
