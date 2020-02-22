package by.epam.autoshow.util.provider;

import java.util.ResourceBundle;

public class MessageProvider {
    private final static ResourceBundle MESSAGE_RESOURCE_BUNDLE = ResourceBundle.getBundle("properties.messages");

    private MessageProvider() {
    }

    public static String getProperty(String key) {
        return MESSAGE_RESOURCE_BUNDLE.getString(key);
    }
}