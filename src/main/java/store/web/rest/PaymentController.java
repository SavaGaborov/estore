package store.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.service.payment.GetAnalytics;
import store.web.rest.dto.response.GetAnalyticsResponse;

@Slf4j
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private GetAnalytics getAnalytics;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/analytics")
    public ResponseEntity<GetAnalyticsResponse> getAnalytics() {
        log.info("GET /api/payments/analytics");
        final GetAnalyticsResponse analytics = getAnalytics.execute();
        return ResponseEntity.ok(analytics);
    }
}
