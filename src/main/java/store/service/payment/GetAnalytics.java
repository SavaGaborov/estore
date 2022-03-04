package store.service.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.domain.Payment;
import store.domain.enumeration.SubscriptionType;
import store.repository.PaymentRepository;
import store.web.rest.dto.response.GetAnalyticsResponse;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAnalytics {

    private PaymentRepository paymentRepository;

    public GetAnalyticsResponse execute() {

        List<Payment> allPayments = paymentRepository.findAll();
        List<Payment> currentMonthPayments = getPaymentsForCurrentMonth(allPayments);

        return GetAnalyticsResponse.builder()
                .totalEarnings(getEarningsForPayments(allPayments))
                .totalEarningsFromEbook(getEarningsForPayments(getEbookPayments(allPayments)))
                .totalEarningsFromCourses(getEarningsForPayments(getCoursesPayments(allPayments)))
                .totalSoldEbooks(getEbookPayments(allPayments).size())
                .totalSoldCourses(getCoursesPayments(allPayments).size())
                .currentMonthEarnings(getEarningsForPayments(currentMonthPayments))
                .currentMonthEarningsFromEbook(getEarningsForPayments(getEbookPayments(currentMonthPayments)))
                .currentMonthEarningsFromCourses(getEarningsForPayments(getCoursesPayments(currentMonthPayments)))
                .currentMonthSoldEbooks(getEbookPayments(currentMonthPayments).size())
                .currentMonthSoldCourses(getCoursesPayments(currentMonthPayments).size())
                .build();
    }

    private BigDecimal getEarningsForPayments(List<Payment> allPayments) {
        BigDecimal totalEarnings = BigDecimal.ZERO;
        for (Payment payment : allPayments)
        {
            totalEarnings = totalEarnings.add(payment.getPrice());
        }
        return totalEarnings;
    }

    private List<Payment> getEbookPayments(List<Payment> payments){
        return payments
                .stream()
                .filter(payment -> payment.getSubscriptionType().equals(SubscriptionType.EBOOK))
                .collect(Collectors.toList());
    }

    private List<Payment> getCoursesPayments(List<Payment> payments){
        return payments
                .stream()
                .filter(payment -> !payment.getSubscriptionType().equals(SubscriptionType.EBOOK))
                .collect(Collectors.toList());
    }

    private List<Payment> getPaymentsForCurrentMonth(List<Payment> allPayments) {
        return allPayments
                .stream()
                .filter(payment -> payment.getPaymentDate().getMonth().equals(ZonedDateTime.now().getMonth()))
                .collect(Collectors.toList());
    }

}
