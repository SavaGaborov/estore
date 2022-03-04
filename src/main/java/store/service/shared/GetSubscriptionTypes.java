package store.service.shared;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.domain.enumeration.SubscriptionType;

@Service
@RequiredArgsConstructor
public class GetSubscriptionTypes {

    public SubscriptionType[] execute() {
        return SubscriptionType.values();
    }
}
