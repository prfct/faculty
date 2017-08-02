package com.my.faculty.util;

import java.util.Locale;

/**
 * @author Oleksii Petrokhalko.
 */
public enum Language {
    ENGLISH(new Locale("en")),
    RUSSIAN(new Locale("ru"));

    private Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getCode() {
        return locale.getLanguage();
    }

    public static Language fromCode(String code) {
        Language language = getDefaultLanguage();
        if (code != null) {
            for (Language tmpLanguage : Language.values()) {
                if (code.equals(tmpLanguage.getLocale().getLanguage())) {
                    language = tmpLanguage;
                    break;
                }
            }
        }
        return language;
    }

    public static Language getDefaultLanguage() {
        return ENGLISH;
    }
}
