package store.component.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import store.component.PasswordEncoder;
import store.configuration.CustomProperties;

@Component
@RequiredArgsConstructor
public class BCryptPasswordEncoder implements PasswordEncoder {

    private final CustomProperties customProperties;

    @Override
    public String generateSalt() {
        return BCrypt.gensalt();
    }

    @Override
    public String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, customProperties.getSalt());
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.hashpw(rawPassword, customProperties.getSalt()).equals(encodedPassword);
    }
}