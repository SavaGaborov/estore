package store.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.domain.User;
import store.domain.enumeration.Role;
import store.repository.UserRepository;
import store.service.user.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUser {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User byId(final Long id) {
        final Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
        checkUser(user);
        return user.get();
    }

    @Transactional(readOnly = true)
    public List<User> byRole(final Role role) {
        final List<User> users = userRepository.findAllByRole(role);
        return users;
    }

    @Transactional(readOnly = true)
    public User byEmail(final String email) {
        final Optional<User> user = Optional.ofNullable(userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new));
        checkUser(user);
        return user.get();
    }

    private void checkUser(final Optional<User> user) {
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
    }
}
