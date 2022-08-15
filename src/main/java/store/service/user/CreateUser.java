package store.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.domain.User;
import store.domain.enumeration.Role;
import store.repository.UserRepository;
import store.service.user.value.UserInfo;

import java.time.LocalDateTime;

import static store.util.GeneratorUtil.getRandomStringWithNumbers;

@Service
@RequiredArgsConstructor
public class CreateUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public User execute(final UserInfo userInfo) {
        final User user = User.builder()
                .createdAt(LocalDateTime.now())
                .deleted(false)
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .email(userInfo.getEmail().trim().toLowerCase())
                .hashPassword(passwordEncoder.encode(userInfo.getPassword()))
                .languageCode(userInfo.getLanguageCode())
                .country(userInfo.getCountry())
                .role(Role.ROLE_CUSTOMER)
                .subscriptions(userInfo.getSubscriptions() != null ? String.join(",", userInfo.getSubscriptions()) : "")
                .sessionId(getRandomStringWithNumbers())
                .lastLogin(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }
}
