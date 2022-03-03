package store.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import store.domain.enumeration.LanguageCode;
import store.domain.enumeration.Role;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String accessToken;

    private final Role role;

    private final LanguageCode languageCode;

    private final UserInfoResponse customer;
}
