package store.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import store.domain.enumeration.LanguageCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class UserInfoResponse {

    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String email;

    private final String name;

    private final String country;

    private final LanguageCode languageCode;
}
