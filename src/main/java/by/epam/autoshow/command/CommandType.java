package by.epam.autoshow.command;

import by.epam.autoshow.command.impl.*;
import by.epam.autoshow.command.impl.car.*;
import by.epam.autoshow.command.impl.carservice.*;
import by.epam.autoshow.command.impl.customer.*;
import by.epam.autoshow.command.impl.order.*;
import by.epam.autoshow.command.impl.user.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    REGAIN_PASSWORD(new RegistrationCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),

    GET_ALL_USERS(new GetAllUsersCommand()),
    GET_ALL_CARS(new GetAllCarsCommand()),
    GET_ALL_SERVICES(new GetAllServicesCommand()),
    GET_ALL_ORDERS(new GetAllOrdersCommand()),
    GET_ALL_CUSTOMERS(new GetAllCustomersCommand()),

    GET_HOME_PAGE(new GetHomePageCommand()),
    GET_PROFILE_PAGE(new GetProfilePageCommand()),
    GET_LOGIN_PAGE(new GetLoginPageCommand()),
    GET_REGISTRATION_PAGE(new GetRegistrationPageCommand()),
    GET_ABOUT_COMPANY_PAGE(new GetAboutCompanyPageCommand()),

    GET_USER_ADD_PAGE(new GetUserAddPageCommand()),
    GET_USER_EDIT_PAGE(new GetUserEditPageCommand()),

    GET_SERVICE_ADD_PAGE(new GetServiceAddPageCommand()),
    GET_SERVICE_EDIT_PAGE(new GetServiceEditPageCommand()),

    GET_CUSTOMER_ADD_PAGE(new GetCustomerAddPageCommand()),
    GET_CUSTOMER_EDIT_PAGE(new GetCustomerEditPageCommand()),

    GET_CAR_ADD_PAGE(new GetCarAddPageCommand()),
    GET_CAR_EDIT_PAGE(new GetCarEditPageCommand()),

    GET_ORDER_ADD_PAGE(new GetOrderAddPageCommand()),

    ADD_USER(new AddUserCommand()),
    EDIT_USER(new EditUserCommand()),

    ADD_SERVICE(new AddAutoShowServiceCommand()),
    EDIT_SERVICE(new EditAutoShowServiceCommand()),

    ADD_CUSTOMER(new AddCustomerCommand()),
    EDIT_CUSTOMER(new EditCustomerCommand()),

    ADD_CAR(new AddCarCommand()),
    EDIT_CAR(new EditCarCommand()),

    ACCEPT_ORDER(new AcceptOrderCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    REJECT_ORDER(new RejectOrderCommand());

    ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}