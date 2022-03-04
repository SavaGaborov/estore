package store.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class GetAnalyticsResponse {

    final BigDecimal totalEarnings;
    final BigDecimal totalEarningsFromEbook;
    final BigDecimal totalEarningsFromCourses;
    final Integer totalSoldEbooks;
    final Integer totalSoldCourses;
    final BigDecimal currentMonthEarnings;
    final BigDecimal currentMonthEarningsFromEbook;
    final BigDecimal currentMonthEarningsFromCourses;
    final Integer currentMonthSoldEbooks;
    final Integer currentMonthSoldCourses;
}
