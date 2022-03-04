package store.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.domain.enumeration.SubscriptionType;
import store.service.shared.GetCountries;
import store.service.shared.GetSubscriptionTypes;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/shared")
@RequiredArgsConstructor
public class SharedController {

    private final GetCountries getCountries;
    private final GetSubscriptionTypes getSubscriptionTypes;

    @GetMapping("/countries")
    public ResponseEntity<Set<String>> getCountries() {
        log.info("GET /shared/countries");
        final Set<String> countries = getCountries.execute();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/subscriptionTypes")
    public ResponseEntity<SubscriptionType[]> getSubTypes() {
        log.info("GET /shared/subscriptionTypes");
        final SubscriptionType[] subTypes = getSubscriptionTypes.execute() ;
        return ResponseEntity.ok(subTypes);
    }
}
