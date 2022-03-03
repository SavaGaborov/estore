package store.service.authentication.exception;

import store.configuration.exception.ApplicationMainException;

public class EmailExistsException extends ApplicationMainException {

    public EmailExistsException() {
        super("email.exists");
    }
}
