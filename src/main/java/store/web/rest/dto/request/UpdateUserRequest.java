package store.web.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.domain.enumeration.LanguageCode;
import store.service.user.value.UserInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static store.util.ConstantsUtil.EMAIL_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = EMAIL_REGEXP)
    private String email;

    @NotNull
    private LanguageCode languageCode;

    @NotBlank
    private String country;

    @NotBlank
    private String[] subscriptions;

    public UserInfo toUserInfo() {
        return UserInfo.builder().firstName(firstName).lastName(lastName).email(email).languageCode(languageCode).country(country).subscriptions(subscriptions)
                .build();
    }
}
