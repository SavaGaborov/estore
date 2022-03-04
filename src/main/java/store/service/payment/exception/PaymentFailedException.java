package store.service.payment.exception;

import store.configuration.exception.ApplicationMainException;

public class PaymentFailedException extends ApplicationMainException {

    public PaymentFailedException() {
        super("payment.failed");
    }

}
