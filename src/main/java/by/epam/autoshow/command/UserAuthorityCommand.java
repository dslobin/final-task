package by.epam.autoshow.command;

import java.util.Optional;
import java.util.stream.Stream;

public enum UserAuthorityCommand {
    LOGIN("guest"),
    LOGOUT("admin", "client"),
    REGISTRATION("admin", "client", "guest"),
    REGAIN_PASSWORD("admin", "client"),
    CHANGE_LOCALE("admin", "client", "guest"),

    GET_ALL_USERS("admin"),
    GET_ALL_CARS("admin", "client", "guest"),
    GET_ALL_SERVICES("admin", "client", "guest"),
    GET_ALL_ORDERS("admin"),
    GET_ALL_CUSTOMERS("admin"),

    GET_HOME_PAGE("admin", "client", "guest"),
    GET_PROFILE_PAGE("client"),
    GET_LOGIN_PAGE("admin", "client", "guest"),
    GET_REGISTRATION_PAGE("admin", "client", "guest"),
    GET_ABOUT_COMPANY_PAGE("admin", "client", "guest"),

    GET_USER_ADD_PAGE("admin"),
    GET_USER_EDIT_PAGE("admin"),

    GET_SERVICE_ADD_PAGE("admin"),
    GET_SERVICE_EDIT_PAGE("admin"),

    GET_CUSTOMER_ADD_PAGE("admin"),
    GET_CUSTOMER_EDIT_PAGE("admin"),

    GET_CAR_ADD_PAGE("admin"),
    GET_CAR_EDIT_PAGE("admin"),
    GET_CAR_IMAGE_UPLOAD_PAGE("admin"),

    GET_ORDER_ADD_PAGE("admin", "client"),

    ADD_USER("admin"),
    EDIT_USER("admin"),

    ADD_SERVICE("admin"),
    EDIT_SERVICE("admin"),

    ADD_CUSTOMER("admin"),
    EDIT_CUSTOMER("admin"),

    ADD_CAR("admin"),
    EDIT_CAR("admin"),

    ACCEPT_ORDER("admin"),
    CREATE_ORDER("admin", "client"),
    REJECT_ORDER("admin"),

    DEFAULT_VALUE("");

    private String[] roles;

    UserAuthorityCommand(String... roles) {
        this.roles = roles;
    }

    public static Optional<UserAuthorityCommand> fromString(String type) {
        return Stream.of(UserAuthorityCommand.values())
                .filter(e -> e.name().equalsIgnoreCase(type))
                .findFirst();
    }

    public boolean isValidRole(String role) {
        return Stream.of(roles).anyMatch(r -> r.equalsIgnoreCase(role));
    }
}