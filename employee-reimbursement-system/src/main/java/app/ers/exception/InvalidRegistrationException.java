package app.ers.exception;

public class InvalidRegistrationException extends RuntimeException {
    public InvalidRegistrationException(String message) { super(message); }
}