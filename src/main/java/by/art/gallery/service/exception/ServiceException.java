package by.art.gallery.service.exception;

public class ServiceException extends Exception{
    private static final long serialVersionUID = 8342223494634340941L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
