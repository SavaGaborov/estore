package store.service.user.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import store.domain.enumeration.LanguageCode;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class UserInfo {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LanguageCode languageCode;

    private String country;

    private String[] subscriptions;
}
