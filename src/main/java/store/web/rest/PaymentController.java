package store.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import store.service.payment.GetAnalytics;
import store.service.payment.Pay;
import store.web.rest.dto.request.PaymentRequest;
import store.web.rest.dto.response.GetAnalyticsResponse;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private GetAnalytics getAnalytics;
    private Pay pay;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/payments/analytics")
    public ResponseEntity<GetAnalyticsResponse> getAnalytics() {
        log.info("GET /api/payments/analytics");
        final GetAnalyticsResponse analytics = getAnalytics.execute();
        return ResponseEntity.ok(analytics);
    }

    @PostMapping("/pay")
    public ResponseEntity<Void> pay(@RequestBody @Valid PaymentRequest data) {
        log.info("POST /payments/pay - {}", data.toString());
        pay.execute(data);
        return ResponseEntity.ok().build();
    }
}
