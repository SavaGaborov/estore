package store.service.authentication;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import store.component.TokenEncoder;
import store.domain.User;
import store.repository.UserRepository;
import store.service.authentication.exception.CredentialsInvalidException;
import store.web.rest.dto.request.SignInRequest;

import java.time.LocalDateTime;
import java.util.Optional;

import static store.util.ConstantsUtil.SESSION_ID_LENGTH;
import static store.util.GeneratorUtil.getRandomStringWithNumbers;

@Service
@RequiredArgsConstructor
public class SignIn {

    private final TokenEncoder tokenEncoder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public Pair<User, String> signIn(final SignInRequest signInInfo) {
        try {
            Optional<User> user = userRepository.findUserByEmail(signInInfo.getEmail().trim().toLowerCase());
            if (user.isPresent() && !user.get().isDeleted()) {
                if (passwordEncoder.matches(signInInfo.getPassword(), user.get().getHashPassword())) {
                    updateLastLoginAndSessionId(user.get());
                    return Pair.of(user.get(), tokenEncoder.generate(user.get()));
                }
            }
        } catch (Exception ignored) {
        }
        throw new CredentialsInvalidException();
    }

    private void updateLastLoginAndSessionId(User user) {
        user.setLastLogin(LocalDateTime.now());
        user.setSessionId(getRandomStringWithNumbers());
        //TODO webscoket?
        userRepository.save(user);
    }
}
