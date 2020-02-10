package by.epam.autoshow.command;

import java.util.Optional;
import java.util.stream.Stream;

public enum UserAuthorityCommand {
    ADD_USER("admin"),
    GET_ALL_USERS("admin"),
    BLOCK_USER("admin"),

    ADD_SERVICE("admin"),
    GET_ALL_SERVICES("admin", "user"),
    DELETE_SERVICE("admin"),

    ADD_CAR("admin"),
    GET_ALL_CARS("admin", "user"),
    EDIT_CAR("admin"),

    ADD_CUSTOMER("admin"),
    GET_ALL_CUSTOMERS("admin"),
    EDIT_CUSTOMER("admin"),

    CREATE_ORDER("admin", "user"),
    ACCEPT_ORDER("admin"),
    REJECT_ORDER("admin"),

    LOG_IN("default"),
    GET_LOGIN_PAGE("default"),
    GET_REGISTRATION_PAGE("default"),
    GET_ABOUT_COMPANY_PAGE("default"),
    GET_HOME_PAGE("default"),
    GET_PROFILE_PAGE("default"),
    LOG_OUT("admin", "user"),
    SIGN_UP("default"),
    REGAIN_PASS("default"),
    NEW_PASS("default"),

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