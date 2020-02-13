package by.epam.autoshow.command;

import by.epam.autoshow.command.impl.*;
import by.epam.autoshow.command.impl.car.EditCarCommand;
import by.epam.autoshow.command.impl.car.GetAllCarsCommand;
import by.epam.autoshow.command.impl.carservice.*;
import by.epam.autoshow.command.impl.order.GetAllOrdersCommand;
import by.epam.autoshow.command.impl.user.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    REGAIN_PASSWORD(new RegistrationCommand()),
    CHANGE_LOCALE(new SelectLocaleCommand()),

    GET_USER_PAGE(new GetAllUsersCommand()),
    GET_CAR_PAGE(new GetAllCarsCommand()),
    GET_SERVICE_PAGE(new GetAllServicesCommand()),
    GET_ORDER_PAGE(new GetAllOrdersCommand()),
    GET_HOME_PAGE(new GetHomePageCommand()),
    GET_PROFILE_PAGE(new GetProfilePageCommand()),
    GET_LOGIN_PAGE(new GetLoginPageCommand()),
    GET_REGISTRATION_PAGE(new GetRegistrationPageCommand()),
    GET_ABOUT_COMPANY_PAGE(new GetAboutCompanyPageCommand()),

    GET_USER_ADD_PAGE(new GetUserAddPageCommand()),
    GET_USER_EDIT_PAGE(new GetUserEditPageCommand()),

    GET_SERVICE_ADD_PAGE(new GetServiceAddPageCommand()),
    GET_SERVICE_EDIT_PAGE(new GetServiceEditPageCommand()),

    ADD_USER(new EditUserCommand()),
    EDIT_USER(new EditUserCommand()),

    ADD_CAR(new EditCarCommand()),
    EDIT_CAR(new EditCarCommand()),

    ADD_SERVICE(new AddAutoShowServiceCommand()),
    EDIT_SERVICE(new EditAutoShowServiceCommand());

    ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}