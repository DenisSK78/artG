package by.art.gallery.service.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocaleRegistry {
    private static final Map<String, Locale> LOCALE_MAP = new HashMap<>();

    static {
        LOCALE_MAP.put("en_US", new Locale("en", "US"));
        LOCALE_MAP.put("ru_RU", new Locale("ru", "RU"));
    }

    private LocaleRegistry(){}

    public static Locale getLocale(String customCode){
        return LOCALE_MAP.containsKey(customCode)
                ?LOCALE_MAP.get(customCode)
                :LOCALE_MAP.getOrDefault("en_US", Locale.US);
    }
}
