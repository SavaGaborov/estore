package store.service.authentication.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class ChangePasswordInfo {

    private String email;

    private String oldPassword;

    private String newPassword;
}
