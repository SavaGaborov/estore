package store.service.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.domain.User;
import store.repository.UserRepository;
import store.service.user.GetUser;

@Service
@RequiredArgsConstructor
public class SignOut {

    private UserRepository userRepository;
    private GetUser getUser;

    @Transactional(rollbackFor = Exception.class)
    public void withCustomer(String customerEmail) {
        try {
            final User user = getUser.byEmail(customerEmail);
            user.setSessionId(null);
            userRepository.save(user);
        } catch (Exception ignored) {
        }
    }
}
