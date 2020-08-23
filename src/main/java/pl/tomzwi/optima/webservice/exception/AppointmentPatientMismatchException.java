package pl.tomzwi.optima.webservice.exception;

public class AppointmentPatientMismatchException extends RuntimeException {
    public AppointmentPatientMismatchException(String message) {
        super(message);
    }

    public AppointmentPatientMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppointmentPatientMismatchException(Throwable cause) {
        super(cause);
    }
}
