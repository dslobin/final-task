package by.epam.autoshow.resource;

import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle MESSAGE_RESOURCE_BUNDLE = ResourceBundle.getBundle("properties.messages");

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return MESSAGE_RESOURCE_BUNDLE.getString(key);
    }
}