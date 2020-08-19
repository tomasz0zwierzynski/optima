package pl.tomzwi.optima.security.exception;

public class UserEmailAlreadyExistsException extends RuntimeException {
    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }

    public UserEmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
