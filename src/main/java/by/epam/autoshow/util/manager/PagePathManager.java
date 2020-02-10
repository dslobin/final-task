package by.epam.autoshow.util.manager;

import java.util.ResourceBundle;

public class PagePathManager {
    private final static ResourceBundle PAGE_PATH_RESOURCE_BUNDLE = ResourceBundle.getBundle("config.pagePath");

    private PagePathManager() {
    }

    public static String getProperty(String key) {
        return PAGE_PATH_RESOURCE_BUNDLE.getString(key);
    }
}