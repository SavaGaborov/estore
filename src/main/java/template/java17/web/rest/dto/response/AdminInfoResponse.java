package template.java17.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import template.java17.domain.User;
import template.java17.domain.enumeration.LanguageCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class AdminInfoResponse {

    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String email;

    private final LanguageCode languageCode;

    public static AdminInfoResponse from(final User user) {
        if (user == null)
            return null;
        return AdminInfoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .languageCode(user.getLanguageCode())
                .build();
    }
}
