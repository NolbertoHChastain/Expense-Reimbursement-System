package app.ers.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

/**
 * registers & handles unexpected events by using custom exceptions
 */
@RestControllerAdvice
public class ExceptionAndErrorController {

    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateUsernames(RepositoryException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidRegistration(InvalidRegistrationException e) {
        return e.getMessage();
    }

}