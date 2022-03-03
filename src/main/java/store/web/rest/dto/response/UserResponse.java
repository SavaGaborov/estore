package store.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import store.domain.User;
import store.domain.enumeration.LanguageCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class UserResponse {

    private final Long id;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final LanguageCode languageCode;

    private final String country;

    private String[] subscriptions;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .languageCode(user.getLanguageCode())
                .country(user.getCountry())
                .subscriptions(null)
                .build();
    }
}
