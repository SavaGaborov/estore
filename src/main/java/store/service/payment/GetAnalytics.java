package store.service.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.repository.PaymentRepository;
import store.web.rest.dto.response.GetAnalyticsResponse;

@Service
@RequiredArgsConstructor
public class GetAnalytics {

    private PaymentRepository paymentRepository;

    public GetAnalyticsResponse execute() {
        return GetAnalyticsResponse.builder().build();
        //TODO implement logic
    }
}
