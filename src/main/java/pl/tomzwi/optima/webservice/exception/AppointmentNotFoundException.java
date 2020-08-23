package pl.tomzwi.optima.webservice.exception;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }

    public AppointmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppointmentNotFoundException(Throwable cause) {
        super(cause);
    }
}
