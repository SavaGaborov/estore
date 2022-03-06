package store.web.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.domain.enumeration.SubscriptionType;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    @NotBlank
    private Long customerId;

    @NotBlank
    private SubscriptionType subscriptionType;


}
