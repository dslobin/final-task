package by.epam.autoshow.command;

import by.epam.autoshow.command.impl.*;
import by.epam.autoshow.command.impl.car.EditCarCommand;
import by.epam.autoshow.command.impl.car.GetAllCarsCommand;
import by.epam.autoshow.command.impl.carservice.EditAutoShowServiceCommand;
import by.epam.autoshow.command.impl.carservice.GetAllServicesCommand;
import by.epam.autoshow.command.impl.order.GetAllOrdersCommand;
import by.epam.autoshow.command.impl.user.EditUserCommand;
import by.epam.autoshow.command.impl.user.GetUserAddPageCommand;
import by.epam.autoshow.command.impl.user.GetAllUsersCommand;

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
    GET_REGISTRATION_PAGE(new GetRegistrationPageCommand()),
    GET_ABOUT_COMPANY_PAGE(new GetAboutCompanyPageCommand()),

    GET_USER_ADD_PAGE(new GetUserAddPageCommand()),

    EDIT_USER_COMMAND(new EditUserCommand()),
    EDIT_CAR_COMMAND(new EditCarCommand()),
    EDIT_SERVICE_COMMAND(new EditAutoShowServiceCommand());

    ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}