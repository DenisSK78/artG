package by.art.gallery.repository.config;

import java.util.ResourceBundle;

public class DBResources {
    private final static DBResources instance = new DBResources();
    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    private DBResources() {
    }

    public static DBResources getInstance() {
        return instance;
    }

    String getValue(String key) {
        return bundle.getString(key);
    }
}
