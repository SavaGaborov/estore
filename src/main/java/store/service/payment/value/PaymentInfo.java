package store.service.payment.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import store.domain.enumeration.SubscriptionType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class PaymentInfo {

    private Long userId;

    private ZonedDateTime paymentDate;

    private SubscriptionType subscriptionType;

    private BigDecimal price;
}
