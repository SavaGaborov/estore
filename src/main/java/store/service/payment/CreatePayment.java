package store.service.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.domain.Payment;
import store.domain.User;
import store.repository.PaymentRepository;
import store.repository.UserRepository;
import store.service.payment.value.PaymentInfo;
import store.service.user.GetUser;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatePayment {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final GetUser getUser;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void execute(final PaymentInfo paymentInfo) {
        User user = getUser.byId(paymentInfo.getUserId());

        final Payment payment = Payment.builder()
                .user(user)
                .paymentDate(ZonedDateTime.now())
                .subscriptionType(paymentInfo.getSubscriptionType())
                .price(paymentInfo.getPrice()).build();
        paymentRepository.save(payment);

        String[] stringArray = setSubscriptionToUser(paymentInfo, user);
        user.setSubscriptions(String.join(",", stringArray));
        userRepository.save(user);
    }

    private String[] setSubscriptionToUser(PaymentInfo paymentInfo, User user) {
        String[] subscription = user.getSubscriptions().split(",");
        List<String> subscriptionList = Arrays.asList(subscription);
        subscriptionList.add(paymentInfo.getSubscriptionType().toString());
        String[] stringArray = subscriptionList.toArray(new String[subscriptionList.size()]);
        return stringArray;
    }
}
