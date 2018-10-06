package by.art.gallery.service.config;

import java.util.ResourceBundle;

public class LocaleBundle {
    private final static LocaleBundle instance = new LocaleBundle();

    private ResourceBundle bundle = ResourceBundle.getBundle("locale");

    private LocaleBundle() {}

    public static LocaleBundle getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
