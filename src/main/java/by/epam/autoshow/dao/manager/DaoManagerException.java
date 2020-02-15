package by.epam.autoshow.dao.manager;

public class DaoManagerException extends Exception {
    public DaoManagerException() {
        super();
    }

    public DaoManagerException(String message) {
        super(message);
    }

    public DaoManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoManagerException(Throwable cause) {
        super(cause);
    }
}