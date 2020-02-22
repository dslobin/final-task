package by.epam.autoshow.util.provider;

import java.util.ResourceBundle;

public class PagePathProvider {
    private final static ResourceBundle PAGE_PATH_RESOURCE_BUNDLE = ResourceBundle.getBundle("config.pagePath");

    private PagePathProvider() {
    }

    public static String getProperty(String key) {
        return PAGE_PATH_RESOURCE_BUNDLE.getString(key);
    }
}