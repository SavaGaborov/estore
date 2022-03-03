package template.java17.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import template.java17.domain.enumeration.LanguageCode;
import template.java17.domain.enumeration.Role;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String accessToken;

    private final Role role;

    private final LanguageCode languageCode;

    private final CustomerInfoResponse customer;

    private final AdminInfoResponse admin;
}
