package store.web.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.service.authentication.value.ChangePasswordInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static store.util.ConstantsUtil.EMAIL_REGEXP;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest implements Serializable {

    @NotBlank
    @Pattern(regexp = EMAIL_REGEXP)
    private String email;

    @NotNull
    @Size(min = 6, max = 32)
    private String oldPassword;

    @NotNull
    @Size(min = 6, max = 32)
    private String newPassword;

    public ChangePasswordInfo toChangePasswordInfo() {
        return ChangePasswordInfo.builder().email(email).oldPassword(oldPassword).newPassword(newPassword).build();
    }
}
