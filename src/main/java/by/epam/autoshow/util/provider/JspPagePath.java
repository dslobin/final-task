package by.epam.autoshow.util.provider;

public final class JspPagePath {
    public static final String INDEX_PAGE_PROPERTY = "path.page.index";
    public static final String FILE_UPLOAD_PAGE_PROPERTY = "path.page.fileUpload";
    public static final String HOME_PAGE_PROPERTY = "path.page.home";
    public static final String LOGIN_PAGE_PROPERTY = "path.page.login";
    public static final String REGISTRATION_PAGE_PROPERTY = "path.page.registration";
    public static final String ABOUT_COMPANY_PAGE_PROPERTY = "path.page.aboutCompany";
    public static final String ERROR_PAGE_PROPERTY = "path.page.error";

    public static final String PROFILE_PAGE_PROPERTY = "path.page.profile";

    public static final String CAR_EDIT_PAGE_PROPERTY = "path.page.carEditForm";
    public static final String ADMIN_CAR_OVERVIEW_PAGE_PROPERTY = "path.page.adminCarOverview";
    public static final String CLIENT_CAR_OVERVIEW_PAGE_PROPERTY = "path.page.clientCarOverview";

    public static final String SERVICE_EDIT_PAGE_PROPERTY = "path.page.serviceEditForm";
    public static final String ADMIN_SERVICE_OVERVIEW_PAGE_PROPERTY = "path.page.adminServiceOverview";
    public static final String CLIENT_SERVICE_OVERVIEW_PAGE_PROPERTY = "path.page.clientServiceOverview";

    public static final String USER_OVERVIEW_PAGE_PROPERTY = "path.page.userOverview";
    public static final String USER_EDIT_PAGE_PROPERTY = "path.page.userEditForm";

    public static final String CUSTOMER_OVERVIEW_PAGE_PROPERTY = "path.page.customerOverview";
    public static final String CUSTOMER_EDIT_PAGE_PROPERTY = "path.page.customerEditForm";

    public static final String ORDER_OVERVIEW_PAGE_PROPERTY = "path.page.orderOverview";
    public static final String ORDER_EDIT_PAGE_PROPERTY = "path.page.serviceOrder";

    public static final String CUSTOMERS_PAGE_URL = "/controller?command=get_all_customers";
    public static final String SERVICES_PAGE_URL = "/controller?command=get_all_services";
    public static final String CARS_PAGE_URL = "/controller?command=get_all_cars";
    public static final String USERS_PAGE_URL = "/controller?command=get_all_users";
    public static final String PROFILE_PAGE_URL = "/controller?command=get_profile_page";
}