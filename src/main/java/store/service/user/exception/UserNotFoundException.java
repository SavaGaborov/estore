package store.service.user.exception;

import store.configuration.exception.ApplicationMainException;

public class UserNotFoundException extends ApplicationMainException {

    public UserNotFoundException() {
        super("user.not.found");
    }
}
