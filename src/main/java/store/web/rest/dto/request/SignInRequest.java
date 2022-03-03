package store.web.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static store.util.ConstantsUtil.EMAIL_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class SignInRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Pattern(regexp = EMAIL_REGEXP)
    private String email;

    @NotBlank
    private String password;
}
