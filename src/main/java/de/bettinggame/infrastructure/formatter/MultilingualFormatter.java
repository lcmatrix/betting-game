package de.bettinggame.infrastructure.formatter;

import de.bettinggame.domain.Multilingual;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

import static java.util.Locale.*;

/**
 * Formatter for instances of {@link Multilingual}.
 */
public class MultilingualFormatter implements Formatter<Multilingual> {
    @Override
    public Multilingual parse(String text, Locale locale) throws ParseException {
        Multilingual object;
        if (ENGLISH.getLanguage().equals(locale.getLanguage())) {
            object = new Multilingual(null, text);
        } else {
                object = new Multilingual(text, null);
        }
        return object;
    }

    @Override
    public String print(Multilingual object, Locale locale) {
        return object.getValueForLocale(locale);
    }
}
