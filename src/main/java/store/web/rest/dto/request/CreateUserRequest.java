package store.web.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import store.domain.enumeration.LanguageCode;
import store.service.user.value.UserInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static store.util.ConstantsUtil.EMAIL_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class CreateUserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = EMAIL_REGEXP)
    private String email;

    @NotNull
    @Size(min = 6, max = 32)
    private String password;

    @NotNull
    private LanguageCode languageCode;

    @NotBlank
    private String country;

    public UserInfo toUserInfo() {
        return UserInfo.builder().firstName(firstName).lastName(lastName).email(email).password(password).languageCode(languageCode).country(country)
                .build();
    }
}
