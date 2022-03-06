package store.service.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.web.rest.dto.request.PaymentRequest;

@Service
@RequiredArgsConstructor
public class Pay {

    public void execute(PaymentRequest data) {

        checkPaymentSource(data);
    }

    private void checkPaymentSource(PaymentRequest data) {

    }
}
