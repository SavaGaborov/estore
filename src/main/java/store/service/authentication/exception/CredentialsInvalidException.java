package store.service.authentication.exception;

import store.configuration.exception.ApplicationMainException;

public class CredentialsInvalidException extends ApplicationMainException {

    public CredentialsInvalidException() {
        super("credentials.invalid");
    }
}

