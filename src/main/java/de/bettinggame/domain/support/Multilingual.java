package de.bettinggame.domain.support;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Locale;

/**
 * A class to support multilingual attributes in entities.
 */
@Embeddable
public class Multilingual {

    @Column(name = "de")
    private String de;

    @Column(name = "en")
    private String en;

    protected Multilingual() {

    }

    public Multilingual(String de, String en) {
        this.de = de;
        this.en = en;
    }

    public String getDe() {
        return de;
    }

    public String getEn() {
        return en;
    }

    public String getValueForLocale(Locale locale) {
        if (Locale.ENGLISH.getLanguage().equals(locale.getLanguage()) && en != null) {
            return en;
        }
        // as I'm German, so default/fallback is
        return de;
    }
}
