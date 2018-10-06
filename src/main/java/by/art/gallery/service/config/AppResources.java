package by.art.gallery.service.config;

import java.util.ResourceBundle;

public class AppResources {

    private final static AppResources instance = new AppResources();

    private ResourceBundle bundle = ResourceBundle.getBundle("app");

    private AppResources() {
    }

    public static AppResources getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
