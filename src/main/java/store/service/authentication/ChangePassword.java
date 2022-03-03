package store.service.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import store.component.PasswordEncoder;
import store.domain.User;
import store.repository.UserRepository;
import store.service.authentication.exception.CredentialsInvalidException;
import store.service.authentication.value.ChangePasswordInfo;
import store.service.user.GetUser;

@Service
@RequiredArgsConstructor
public class ChangePassword {

    private final GetUser getUser;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void setNewPassword(final ChangePasswordInfo info) {
        final User user = getUser.byEmail(info.getEmail());
        if (passwordEncoder.matches(info.getOldPassword(), user.getHashPassword())) {
            user.setHashPassword(passwordEncoder.encode(info.getNewPassword()));
            userRepository.save(user);
        }
        throw new CredentialsInvalidException();
    }
}
